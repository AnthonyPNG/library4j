package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * The User class
 */
public class User {
	/**
     * The id
     */
    private int id;
    /**
     * The password
     */
    private String password;
    /**
     * The first name
     */
    private String firstName;
    /**
     * The last name
     */
    private String lastName;
    /**
     * The phone number
     */
    private int phone;
    /**
     * The role
     */
    private String role;
    /**
     * User is logged in or not
     */
    private boolean isLoggedIn;
    /**
     * The email
     */
    private String email;
    
    /**
     * Instantiates a new User
     * @param id              the id
     * @param password        the password
     * @param firstName  	  the first name of User
     * @param lastName 		  the second name of User
     * @param phone           the phone number
     * @param role     		  the role User
     * @param isLoggedIn      the login condition
     * @param email        	  the email
     */
    public User(int id, String password, String firstName, String lastName, int phone, String role, boolean isLoggedIn, String email) {
        super();
        this.id         = id;
        this.password   = password;
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.phone      = phone;
        this.role       = role;
        this.isLoggedIn = isLoggedIn;
        this.email      = email;
    }

    public User() {
        super();
    }

    /**
     * Gets id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets firstName
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets firstName
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets lastName
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets lastName
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets phone
     * @return phone
     */
    public int getPhone() {
        return phone;
    }

    /**
     * Sets phone
     * @param phone the phone number
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    /**
     * Gets role
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role
     * @param role the role user
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets isLoggedIn
     * @return isLoggedIn
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * Sets isLoggedIn
     * @param isLoggedIn the isLoggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    /**
     * Gets email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets suitable String out of User
     * @return String out of User
     */
    @Override
    public String toString() {
    	String newLine = "\n";
    	return "id:" + this.getId() + newLine +
    			"password: " + this.getPassword() + newLine + 
    			"firstName: " + this.getFirstName() + newLine + 
    			"lastName: " + this.getLastName() + newLine + 
    			"phone: " +  this.getPhone() + newLine + 
    			"role: " + this.getRole() + newLine +
    			"isLoggedIn: " + this.isLoggedIn + newLine + 
    			"email: " + this.getEmail() + newLine;
    }
    
    
    /**
     * Check the information of email and password for the login
     * @param mail the email of User
     * @param pwd the password of User
     * @return true if email and password are in database 'user'
     * @return false else
     */
	public static boolean login(TextField mail, PasswordField pwd) {
		try {
			// Get a connection to db
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
				                                   "user=root&password=root");
			
			// Create a statement
			PreparedStatement preparedStatement = conn.prepareStatement("select * from user where email = ? and password = ?");
			preparedStatement.setString(1, mail.getText().toString());
			preparedStatement.setString(2, pwd.getText().toString());
			
			// Execute SQL query
			ResultSet result = preparedStatement.executeQuery();
			boolean status = false;
			
			// Process the result set
			if (result.next()) {
				status = true;
			}
			return status;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
    
 

    
    
    /**
     * Get a list of all User from the database 'user'
     * @return the list
     */
    public static ObservableList<User> getData() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
				                                   "user=root&password=root");
			
			ObservableList<User> list = FXCollections.observableArrayList();
			
			try {
				PreparedStatement preparedStatement = conn.prepareStatement("select * from user where role='user'");
				ResultSet result = preparedStatement.executeQuery();
				
				while (result.next()) {
					list.add(new User(result.getInt("iduser"), result.getString("password"), result.getString("firstName"),
							result.getString("lastName"), result.getInt("phone"), "user", true, result.getString("email")));
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
