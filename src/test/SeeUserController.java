package test;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class SeeUserController  {
	@FXML
	private TableView<SeeUser> tbSeeUser;
	@FXML
	private TableColumn<SeeUser, Integer> colId;
	@FXML
	private TableColumn<SeeUser, String> colFName;
	@FXML
	private TableColumn<SeeUser, String> colLName;
	@FXML
	private TableColumn<SeeUser, String> colEmail;
	@FXML
	private TableColumn<SeeUser, Integer> colPhone;
	
	ObservableList<SeeUser> list;
	
	//@Override
	public void initialize(ActionEvent event) {
		colId.setCellValueFactory(new PropertyValueFactory<SeeUser,Integer>("iduser"));
		colFName.setCellValueFactory(new PropertyValueFactory<SeeUser,String>("firstName"));
		colLName.setCellValueFactory(new PropertyValueFactory<SeeUser,String>("lastName"));
		colEmail.setCellValueFactory(new PropertyValueFactory<SeeUser,String>("email"));
		colPhone.setCellValueFactory(new PropertyValueFactory<SeeUser,Integer>("phone"));
		
		list = ListUser.getData();
		tbSeeUser.setItems(list);
	}

}
