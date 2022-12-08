package test;

import java.sql.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Register {
	public static boolean register(TextField fname,TextField lname, TextField phoneNumber, TextField mail, PasswordField pwd) {
		try {
			// 1. get a connection to db
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
				                                   "user=root&password=root");
			
			// 2. create a statement
			PreparedStatement preparedStatement = conn.prepareStatement("select * from user where email = ?");
			preparedStatement.setString(1, mail.getText().toString());
			
			ResultSet result = preparedStatement.executeQuery();			
			if (result.next()) {
				return false;
			}
			else {
				PreparedStatement insertion = conn.prepareStatement("insert into user values"
						+ "(default, ?, ?, ?, default, ?, ?)");
				insertion.setString(1, mail.getText().toString());
				insertion.setString(2, fname.getText().toString());
				insertion.setString(3, mail.getText().toString());
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
		
}
