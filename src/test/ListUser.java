package test;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListUser {
	public static ObservableList<SeeUser> getData() {
		try {
			// 1. get a connection to db
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
				                                   "user=root&password=root");
			
			ObservableList<SeeUser> list = FXCollections.observableArrayList();
			
			try {
				PreparedStatement preparedStatement = conn.prepareStatement("select iduser, email, firstName, lastName, phone from user where role='user'");
				ResultSet result = preparedStatement.executeQuery();
				
				while (result.next()) {
					list.add(new SeeUser(result.getInt("iduser"), result.getInt("phone"), 
							result.getString("email"), result.getString("firstName"), 
							result.getString("lastName")));
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
