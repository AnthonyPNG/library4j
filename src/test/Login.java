package test;

import java.sql.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.User;

public class Login {
	
	public static boolean login(TextField mail, PasswordField pwd) {
		try {
			// 1. get a connection to db
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
				                                   "user=root&password=root");
			
			// 2. create a statement
			PreparedStatement preparedStatement = conn.prepareStatement("select * from user where email = ? and password = ?");
			preparedStatement.setString(1, mail.getText().toString());
			preparedStatement.setString(2, pwd.getText().toString());
			
			ResultSet result = preparedStatement.executeQuery();
			boolean status = false;
			
			if (result.next()) {
				status = true;
			}
			// 4. process the result set
			return status;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static User getUser(TextField mail, PasswordField pwd) {
		User user;
		try {
			// 1. get a connection to db
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
				                                   "user=root&password=root");
			
			// 2. create a statement
			PreparedStatement preparedStatement = conn.prepareStatement("select * from user where email = ? and password = ?");
			preparedStatement.setString(1, mail.getText().toString());
			preparedStatement.setString(2, pwd.getText().toString());
			
			ResultSet result = preparedStatement.executeQuery();

			
			if (result.next()) {
				user = new User(result.getInt("iduser"), result.getString("password"), result.getString("firstName"),
						result.getString("lastName"), result.getInt("phone"), "user", true, result.getString("email"));
				return user;
			}
			// 4. process the result set
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
