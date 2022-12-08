package main;

import java.sql.*;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * The Librarian class
 */
public class Librarian extends User {
	/**
	 * Initiates a new Librarian
	 * @param id
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param phone
	 * @param role
	 * @param isLoggedIn
	 * @param email
	 */
	public Librarian(int id, String password, String firstName, String lastName, int phone, String role, boolean isLoggedIn, String email) {
		super(id,password,firstName,lastName,phone,role,isLoggedIn,email);		
	}
	
	public Librarian() {
		super();
	}
	
	
	/**
	 * Register a new User in the database 'user'
	 * @param fname
	 * @param lname
	 * @param phoneNumber
	 * @param mail
	 * @param pwd
	 * @return true if the register is correctly done
	 */
    public static boolean register(TextField fname,TextField lname, TextField phoneNumber, TextField mail, PasswordField pwd) {
		try {
			// Get a connection to db
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
				                                   "user=root&password=root");
			
			// Check if the email already exits
			// Create a statement
			PreparedStatement preparedStatement = conn.prepareStatement("select * from user where email = ?");
			preparedStatement.setString(1, mail.getText().toString());
			
			// Execute SQL query
			ResultSet result = preparedStatement.executeQuery();
			
			// Process the result set
			if (result.next()) {
				return false;
			}
			else {
				// Create a statement
				PreparedStatement insertion = conn.prepareStatement("insert into user values"
						+ "(default, ?, ?, ?, default, ?, ?)");
				insertion.setString(1, mail.getText().toString());
				insertion.setString(2, fname.getText().toString());
				insertion.setString(3, lname.getText().toString());
				insertion.setString(4, pwd.getText().toString());
				insertion.setInt(5, Integer.parseInt(phoneNumber.getText().toString()));								
				insertion.executeUpdate();
				return true;
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
    
    
    /**
     * Update phone number and password of User in the database 'user'
     * @param id
     * @param phone
     * @param pwd
     * @return true if the update is correctly done
     */
    public static boolean updateUser(TextField id,TextField phone, PasswordField pwd) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
				                                   "user=root&password=root");
			
			PreparedStatement preparedStatement = conn.prepareStatement("update user set phone=?, password=? where iduser=?");
			preparedStatement.setInt(1, Integer.parseInt(phone.getText().toString()));
			preparedStatement.setString(2, pwd.getText().toString());
			preparedStatement.setInt(3, Integer.parseInt(id.getText().toString()));
			preparedStatement.executeUpdate();
			
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
    
    
    /**
     * Delete a User from the database 'user'
     * @param id
     * @return true if the delete is correctly done
     */
    public static boolean removeUser(TextField id) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
				                                   "user=root&password=root");
			
			PreparedStatement preparedStatement = conn.prepareStatement("delete from user where iduser=?");
			preparedStatement.setInt(1, Integer.parseInt(id.getText().toString()));
			preparedStatement.executeUpdate();
			
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
    
    /**
     * Register a Borrowing 
     * And add it to the database 'borrowing'
     * @param title
     * @param mail
     * @return if the register is correctly done
     */
	@SuppressWarnings("deprecation") 
	public static boolean accept(TextField title, TextField mail) {
		try {
			// Current Date (date of borrowing)
			long millis=System.currentTimeMillis();
			Date bDate = new Date(millis);
			// Due Return Date
			Date drDate = new Date(millis);
			drDate.setMonth(drDate.getMonth()+1);
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
				                                   "user=root&password=root");
			
			// Allow to get the id of the Book
			PreparedStatement statementBook = conn.prepareStatement("select * from book where title = ?");
			statementBook.setString(1, title.getText().toString());
			
			// Allow to get the id of User
			PreparedStatement statementUser = conn.prepareStatement("select * from user where email = ?");
			statementUser.setString(1, mail.getText().toString());
			
			ResultSet resultBook = statementBook.executeQuery();
			ResultSet resultUser = statementUser.executeQuery();
			
			if (resultBook.next() && resultBook.getBoolean("available") == true) {
				if (resultUser.next()) {
					
					// When accepting a Borrowing, the Book is not available
					PreparedStatement updateBook = conn.prepareStatement("update book set available = 0 where title = ?");
					updateBook.setString(1, resultBook.getString("title"));
					updateBook.executeUpdate();
					
					// Put the borrowing in the database
					PreparedStatement insertBorrowing = conn.prepareStatement("insert into borrowing values "
							+ "(default, ?, ?, ?, default, ?, default)");
					insertBorrowing.setInt(1, resultUser.getInt("iduser"));
					insertBorrowing.setInt(2, resultBook.getInt("idBook"));
					insertBorrowing.setDate(3, bDate);
					insertBorrowing.setDate(4, drDate);
					insertBorrowing.executeUpdate();
					
					return true;
				}					
			}			
			return false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	/**
	 * Register a return
	 * See if the return is delayed
	 * @param id
	 * @return true if the register is correctly done
	 */
	public static boolean returnRegister(TextField id) {
		try {
			// Current Date
			long millis=System.currentTimeMillis();
			Date date = new Date(millis);
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
				                                   "user=root&password=root");
					
			PreparedStatement statementBorrow = conn.prepareStatement("select b.idBorrowing, u.email, bk.title, b.borrowingDate, "
					+ "b.returnDate, b.dueReturnDate, b.isDelayed from book as bk join borrowing as b "
					+ "on bk.idBook = b.idBook join user as u on b.idUser = u.iduser where b.idBorrowing = ?");
			statementBorrow.setInt(1, Integer.parseInt(id.getText().toString()));;
			ResultSet resultBorrow = statementBorrow.executeQuery();
			
			if (resultBorrow.next()) {
				// When returning a Book, it becomes available
				PreparedStatement updateBook = conn.prepareStatement("update book set available = 1 where title =?");
				updateBook.setString(1, resultBorrow.getString("bk.title"));
				updateBook.executeUpdate();
				
				// Create a borrow for getting the delay
				Borrowing borrow = new Borrowing(resultBorrow.getInt("b.idBorrowing"), resultBorrow.getString("u.email"), resultBorrow.getString("bk.title"),
						resultBorrow.getDate("b.borrowingDate"), date, resultBorrow.getDate("b.dueReturnDate"), true);
				boolean late = borrow.isDelayed();
				
				PreparedStatement updateBorrowing = conn.prepareStatement("update borrowing set returnDate=?, isDelayed=? where idBorrowing=?");
				updateBorrowing.setDate(1, date);
				updateBorrowing.setBoolean(2, late);
				updateBorrowing.setInt(3, Integer.parseInt(id.getText().toString()));
				updateBorrowing.executeUpdate();
				
			}
			
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
	


