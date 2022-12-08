package test;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import main.Borrowing;

import java.time.LocalDate;

public class HistoryController {
	@FXML
	private TableView<Borrowing> tbBorrowing;
	@FXML
	private TableColumn<Borrowing, String> colBook;
	@FXML
	private TableColumn<Borrowing, LocalDate> colBorrowingDate;
	@FXML
	private TableColumn<Borrowing, LocalDate> colDueReturnDate;
	@FXML
	private TableColumn<Borrowing, LocalDate> colReturnDate;
	@FXML
	private TableColumn<Borrowing, Boolean> colAccepted;
	
	ObservableList<Borrowing> listBorrowing;
	
	public void initialisation(ActionEvent event) throws IOException {
		//put attributes of BorrowingBook
		colBook.setCellValueFactory(new PropertyValueFactory<Borrowing,String>("book"));
		colBorrowingDate.setCellValueFactory(new PropertyValueFactory<Borrowing,LocalDate>("borrowingDate"));
		colDueReturnDate.setCellValueFactory(new PropertyValueFactory<Borrowing,LocalDate>("dueReturnDate"));
		colReturnDate.setCellValueFactory(new PropertyValueFactory<Borrowing,LocalDate>("returnDate"));
		colAccepted.setCellValueFactory(new PropertyValueFactory<Borrowing,Boolean>("accepted"));
		
		listBorrowing = History.getData();
		tbBorrowing.setItems(listBorrowing);
	}
}
