package test;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;

public class SearchBookController {
	@FXML
	private TextField tfTitle;
	@FXML
	private TextField tfAuthorFist;
	@FXML
	private TextField tfAuthorSecond;
	@FXML
	private TextField tfCreationDate;
	@FXML
	private TextField tfCategory;
	
	private Main m = new Main();

	@FXML
	public void switchToHomepage(ActionEvent event) throws IOException {
		m.changeScene("Homepage.fxml");
	}
}
