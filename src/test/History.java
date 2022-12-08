package test;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Borrowing;

public class History {
	
	public static ObservableList<Borrowing> getData() {
		try {
			// 1. get a connection to db
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
				                                   "user=root&password=root");
			
			ObservableList<Borrowing> list = FXCollections.observableArrayList();
			
			try {
				PreparedStatement preparedStatement = conn.prepareStatement("select bk.title, b.borrowingDate, "
						+ "b.returnDate, b.dueReturnDate, b.accepted from book as bk join borrowing as b "
						+ "on bk.idBook = b.idBook join user as u on b.idUser = u.iduser");
				ResultSet result = preparedStatement.executeQuery();
				
				while (result.next()) {
					list.add(new Borrowing(result.getString("bk.title"), result.getDate("b.borrowingDate"), 
							result.getDate("b.dueReturnDate"), result.getDate("b.returnDate"), true, 
							result.getBoolean("b.accepted")));
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
