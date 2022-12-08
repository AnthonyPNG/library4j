package test;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

public class LoginController {
	@FXML
	private TextField tfEMail;
	@FXML
	private TextField tfFirstName;
	@FXML
	private TextField tfLastName;
	@FXML
	private TextField tfPhone;
	@FXML
	private TextField tfEmail;
	@FXML
	private PasswordField tfPassword;
	
	@FXML
	private Button btnLogin;
	@FXML
	private Label lblWrong;
	@FXML
	private Label wrongEmail;
	
	private Main m = new Main();
	
	@FXML
	public void userLogin(ActionEvent event) throws IOException {
		checkLogin();
	}
	
	private void checkLogin() throws IOException {
		boolean check = Login.login(tfEmail, tfPassword);
		if (check) {
			m.changeScene("HomepageConnected.fxml");
		}
		else if (tfEmail.getText().isEmpty() || tfPassword.getText().isEmpty()) {
			lblWrong.setText("Data is empty");
		}
		else {
			lblWrong.setText("Wrong email or password!");
		}
	}
	
	public void userRegister(ActionEvent event) throws IOException {
		registerUser();
	}
	
	public void registerUser() {
		boolean verif = Register.register(tfFirstName, tfLastName, tfPhone, tfEmail, tfPassword);
		if (!verif) {
			wrongEmail.setText("Email is already used");
		}
		else if (tfFirstName.getText().isEmpty() || (tfLastName.getText().isEmpty()) || (tfPhone.getText().isEmpty())
				|| (tfEmail.getText().isEmpty()) || (tfPassword.getText().isEmpty())) {
			wrongEmail.setText("Data is empty");
		}
		else {
			wrongEmail.setText("Register done!");
		}
	}
	
	@FXML
	public void switchToRegister(ActionEvent event) throws IOException {
		m.changeScene("Register.fxml");
	}
	
	@FXML
	public void switchToLogin(ActionEvent event) throws IOException {
		m.changeScene("Login.fxml");
	}
	
	@FXML
	public void switchToHomepage(ActionEvent event) throws IOException {
		m.changeScene("Homepage.fxml");
	}
}
