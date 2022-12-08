package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

/**
 * The Borrowing class
 */
public class Borrowing {
	/**
	 * The id
	 */
	private int id;
	/**
	 * The mail of the User
	 */
	private String mail;
	/**
	 * The title of the Book
	 */
	private String book;
	/**
	 * The date of Borrowing
	 */
	private Date borrowingDate;
	/**
	 * The date of the User returns the Book
	 */
	private Date returnDate;
	/** 
	 * The deadline of the Borrowing
	 */
	private Date dueReturnDate;
	/**
	 * The Borrowing is delayed
	 */
	private boolean delayed;
	
	/**
	 * Initiates a new Borrowing
	 * @param id
	 * @param mail
	 * @param book
	 * @param borrowingDate
	 * @param returnDate
	 * @param dueReturnDate
	 * @param delayed
	 */
	public Borrowing(int id, String mail, String book, Date borrowingDate, Date returnDate, Date dueReturnDate, boolean delayed) {
		this.id = id;
		this.mail = mail;
		this.book = book;
		this.borrowingDate = borrowingDate;
		this.returnDate = returnDate;
		this.dueReturnDate = dueReturnDate;
		this.delayed = delayed;
	}


	/**
	 * Gets the id
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Sets the id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the mail of User
	 * @return the mail
	 */
	public String getMail() {
		return this.mail;
	}
	
	/**
	 * Sets the mail
	 * @param mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * Gets the title of Book
	 * @return book
	 */
	public String getBook() {
		return this.book;
	}
	
	/**
	 * Sets the book
	 * @param book
	 */
	public void setBook(String book) {
		this.book = book;
	}
	
	/**
	 * Gets the Borrowing Date
	 * @return the date
	 */
	public Date getBorrowingDate() {
		return borrowingDate;
	}

	/**
	 * Sets the Borrowing Date
	 * @param borrowingDate
	 */
	public void setBorrowingDate(Date borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	/**
	 * Gets the Date of return
	 * @return the date
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 * Sets the Date of return
	 * @param returnDate
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * Gets the deadline
	 * @return
	 */
	public Date getDueReturnDate() {
		return dueReturnDate;
	}

	/**
	 * Sets the deadline
	 * @param dueReturnDate
	 */
	public void setDueReturnDate(Date dueReturnDate) {
		this.dueReturnDate = dueReturnDate;
	}

	/**
	 * Gets if the Borrowing is delayed
	 * @return
	 */
	public boolean isDelayed() {
		if ((this.getReturnDate() != null) && (this.getDueReturnDate() != null)) {
			if (this.getReturnDate().after(this.getDueReturnDate())) {
				return true;
			}			
		}
		return false;
	}
	
	/**
	 * Sets the delay
	 * @param delayed
	 */
	public void setDelayed(boolean delayed) {
		this.delayed = delayed;
	}
	
	public String toString() {
		char newLine = '\n';
		return "Id: " + this.getId() + newLine +
				"User: " + this.getMail() + newLine +
				"Book: " + this.getBook() + newLine + 
				"Borrowing date: " + this.getBorrowingDate() + newLine +
				"Return date: " + this.getReturnDate() + newLine + 
				"Due return date: " + this.getDueReturnDate() + newLine +
				"Delayed:" + this.isDelayed() + newLine;
	}	
	
	
	/**
	 * Create a ObservableList for the history of Borrowing for User
	 * @param mail
	 * @return the list
	 */
	public static ObservableList<Borrowing> getData(TextField mail) {
		try {
			// Get a connection to db
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
				                                   "user=root&password=root");
			
			ObservableList<Borrowing> list = FXCollections.observableArrayList();
			
			try {
				// Create a statement
				PreparedStatement preparedStatement = conn.prepareStatement("select b.idBorrowing, u.email, bk.title, b.borrowingDate, "
						+ "b.returnDate, b.dueReturnDate from book as bk join borrowing as b "
						+ "on bk.idBook = b.idBook join user as u on b.idUser = u.iduser where u.email=?");
				preparedStatement.setString(1, mail.getText().toString());
				
				// Execute SQL query
				ResultSet result = preparedStatement.executeQuery();
				
				// Process the result set
				while (result.next()) {
					// Add all the Borrowing in the list
					list.add(new Borrowing(result.getInt("b.idBorrowing"), result.getString("u.email"), result.getString("bk.title"),
							result.getDate("b.borrowingDate"), result.getDate("b.returnDate"), result.getDate("b.dueReturnDate"), false));
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * Create a ObservableList for the history of Borrowing for Librarian
	 * @return
	 */
	public static ObservableList<Borrowing> getData() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
				                                   "user=root&password=root");
			
			ObservableList<Borrowing> list = FXCollections.observableArrayList();
			
			try {
				PreparedStatement preparedStatement = conn.prepareStatement("select b.idBorrowing, u.email, bk.title, b.borrowingDate, "
						+ "b.returnDate, b.dueReturnDate from book as bk join borrowing as b "
						+ "on bk.idBook = b.idBook join user as u on b.idUser = u.iduser");
				ResultSet result = preparedStatement.executeQuery();
				
				while (result.next()) {
					list.add(new Borrowing(result.getInt("b.idBorrowing"), result.getString("u.email"), result.getString("bk.title"),
							result.getDate("b.borrowingDate"), result.getDate("b.returnDate"), result.getDate("b.dueReturnDate"), false));
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
