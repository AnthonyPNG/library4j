package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.Books;
import main.BooksApi;
import main.Borrowing;
import main.Librarian;
import main.User;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.JSONException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 * SceneController class 
 * Control the different functions of graphical interface
 */
public class SceneController {
	private Main m = new Main();
	
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Change Scene when click on a button
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void switchToHomepage(ActionEvent event) throws IOException {
		m.changeScene("Homepage.fxml");
	}
	
	@FXML
	public void switchToHomepageConnected(ActionEvent event) throws IOException {
		m.changeScene("HomepageConnected.fxml");
	}
	
	@FXML
	public void switchToHomepageAdmin(ActionEvent event) throws IOException {
		m.changeScene("HomepageAdmin.fxml");
	}
	
	@FXML
	public void switchToLogin(ActionEvent event) throws IOException {
		m.changeScene("Login.fxml");
	}
	
	@FXML
	public void switchToSearchBook(ActionEvent event) throws IOException {
		m.changeScene("SearchBook.fxml");
	}
	
	@FXML
	public void switchToSearchBookUser(ActionEvent event) throws IOException {
		m.changeScene("SearchBookUser.fxml");
	}
	
	@FXML
	public void switchToSearchBookAdmin(ActionEvent event) throws IOException {
		m.changeScene("SearchBookLibrarian.fxml");
	}
	
	@FXML
	public void switchToBorrow(ActionEvent event) throws IOException {
		m.changeScene("AskBorrow.fxml");
	}
	
	@FXML
	public void switchToSeeHistory(ActionEvent event) throws IOException {
		m.changeScene("History.fxml");
	}
	
	@FXML
	public void switchToSeeUser(ActionEvent event) throws IOException {
		m.changeScene("SeeUser.fxml");
	}
	
	@FXML
	public void switchToRegister(ActionEvent event) throws IOException {
		m.changeScene("Register.fxml");
	}
	
	@FXML
	public void switchToUpdate(ActionEvent event) throws IOException {
		m.changeScene("Update.fxml");
	}
	
	@FXML
	public void switchToSeeBorrowingHistory(ActionEvent event) throws IOException {
		m.changeScene("BorrowingHistoryLibrarian.fxml");
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Login Controller
	@FXML
	private TextField tfEmailLogin;
	@FXML
	private PasswordField tfPasswordLogin;
	@FXML
	private Label lblWrong;

	/**
	 * User wants to login 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void userLogin(ActionEvent event) throws IOException {
		checkLogin();
	}
	
	public void checkLogin() throws IOException {
		if (tfEmailLogin.getText().isEmpty() || tfPasswordLogin.getText().isEmpty()) {
			lblWrong.setText("Data is empty");
		}
		else {
			// Check if email and password are in the database
			boolean check = User.login(tfEmailLogin, tfPasswordLogin);
			if (check) {
				// Check if it is the admin
				if (tfEmailLogin.getText().toString().equals("admin.admin")) {
					m.changeScene("HomepageAdmin.fxml");
				}
				else {
					m.changeScene("HomepageConnected.fxml");
				}	
			}
			else {
				lblWrong.setText("Wrong email or password!");
			}
		}
	}
	
	
	// Search a Book and Display results
	 @FXML
	 private Label lblMess;
	 @FXML
	 private TextField tfAuthorSearch;
	 @FXML
	 private TextField tfCategorySearch;
	 @FXML
	 private TextField tfTitleSearch;
	 
	 @FXML
	 private TableColumn<Books, String> cl_author;	  
	 @FXML	  
	 private TableColumn<Books, Boolean> cl_available;	  	 
	 @FXML	  
	 private TableColumn<Books, String> cl_category;	  
	 @FXML	  
	 private TableColumn<Books, String> cl_date;	 
	 @FXML	  
	 private TableColumn<Books, String> cl_title;	  
	 @FXML	  
	 private TableView<Books> tb_BookResult;	 
	 
	 /**
	  * User searches books 
	  * @param event
	  * @throws ProtocolException
	  * @throws JSONException
	  * @throws URISyntaxException
	  * @throws ParseException
	  */
	 public void searchBooks(ActionEvent event) throws ProtocolException, JSONException, URISyntaxException, ParseException {
		 BooksApi apiBook = new BooksApi();
		 if (!tfTitleSearch.getText().isEmpty()) {
			 Books book = apiBook.getBookByTitle(tfTitleSearch.getText().toString());
			 ObservableList<Books> data = FXCollections.<Books>observableArrayList(book);
			 
			 cl_title.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
			 cl_author.setCellValueFactory(new PropertyValueFactory<Books,String>("authorName"));
			 cl_date.setCellValueFactory(new PropertyValueFactory<Books,String>("creationDate"));
			 cl_category.setCellValueFactory(new PropertyValueFactory<Books,String>("category"));
			 cl_available.setCellValueFactory(new PropertyValueFactory<Books,Boolean>("available"));
			 
			tb_BookResult.setItems(data);
		 }
		 else if (!tfAuthorSearch.getText().isEmpty()) {		 
			 ArrayList<Books> book = apiBook.getBookByAuthor(tfAuthorSearch.getText().toString());
			 ObservableList<Books> data = FXCollections.<Books>observableArrayList();
             data.addAll(book);
             
			 cl_title.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
			 cl_author.setCellValueFactory(new PropertyValueFactory<Books,String>("authorName"));
			 cl_date.setCellValueFactory(new PropertyValueFactory<Books,String>("creationDate"));
			 cl_category.setCellValueFactory(new PropertyValueFactory<Books,String>("category"));
			 cl_available.setCellValueFactory(new PropertyValueFactory<Books,Boolean>("available"));
			 
			tb_BookResult.setItems(data);
		 }
		 else if (!tfCategorySearch.getText().isEmpty()) {
			 ArrayList<Books> book = apiBook.getBookByCateg(tfCategorySearch.getText().toString());
			 ObservableList<Books> data = FXCollections.<Books>observableArrayList();
             data.addAll(book);
             
			 cl_title.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
			 cl_author.setCellValueFactory(new PropertyValueFactory<Books,String>("authorName"));
			 cl_date.setCellValueFactory(new PropertyValueFactory<Books,String>("creationDate"));
			 cl_category.setCellValueFactory(new PropertyValueFactory<Books,String>("category"));
			 cl_available.setCellValueFactory(new PropertyValueFactory<Books,Boolean>("available"));
			 
			tb_BookResult.setItems(data);
		 }
		 else {
			 lblMess.setText("Data is empty!");
		 }
	 }
	 
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// UserController
	
	/**
	 * The user double clicks on a book 
	 * If he wants to borrow
	 * The book will be add to the database 'book'
	 * @param event
	 */
	public void clickItem(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				// Get informations of Book for the database
				boolean available;
				try {
					 available = tb_BookResult.getSelectionModel().getSelectedItem().isAvailable();

				}catch (NullPointerException e) {
					available = true;
				}
				String author = tb_BookResult.getSelectionModel().getSelectedItem().getAuthorName();
				String title = tb_BookResult.getSelectionModel().getSelectedItem().getTitle();
				String date = tb_BookResult.getSelectionModel().getSelectedItem().getCreationDate();
				String category = tb_BookResult.getSelectionModel().getSelectedItem().getCategory();
				
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
						                                   "user=root&password=root");
				
				// Check if the book is already in the database
				PreparedStatement check = conn.prepareStatement("select * from book where title = ?");
				check.setString(1, title);
				ResultSet result = check.executeQuery();
				
				if (!result.next()) {
					PreparedStatement preparedStatement = conn.prepareStatement("insert into book values" 
							+ "(default, ?, ?, ?, ?, ?)");
					preparedStatement.setBoolean(1, available);
					preparedStatement.setString(2, author);
					preparedStatement.setString(3, title);
					preparedStatement.setString(4, category);
					preparedStatement.setString(5, date);
					preparedStatement.executeUpdate();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	 }
	 
	
	// See history of borrowings
	@FXML
	private TextField tfMail;
	@FXML
	private TableView<Borrowing> tbBorrowing;
	@FXML
	private TableColumn<Borrowing, String> colBook;
	@FXML
	private TableColumn<Borrowing, Date> colBorrowingDate;
	@FXML
	private TableColumn<Borrowing, Date> colDueReturnDate;
	@FXML
	private TableColumn<Borrowing, Date> colReturnDate;
		
	ObservableList<Borrowing> listBorrowing;
	
	/**
	 * Get informations of borrowing in the database 'borrowing' in a list
	 * And display them to the tableview
	 * @param event
	 * @throws IOException
	 */
	public void initializeHistoryBorrowing(ActionEvent event) throws IOException {
		seeHistory();
	}
	
	public void seeHistory() {
		// Put attributes of Borrowing
		colBook.setCellValueFactory(new PropertyValueFactory<Borrowing,String>("book"));
		colBorrowingDate.setCellValueFactory(new PropertyValueFactory<Borrowing,Date>("borrowingDate"));
		colDueReturnDate.setCellValueFactory(new PropertyValueFactory<Borrowing,Date>("dueReturnDate"));
		colReturnDate.setCellValueFactory(new PropertyValueFactory<Borrowing,Date>("returnDate"));
		
		listBorrowing = Borrowing.getData(tfMail);
		tbBorrowing.setItems(listBorrowing);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// LibrarianController
	
	// Create an User	
	@FXML
	private TextField tfFirstName;
	@FXML
	private TextField tfLastName;
	@FXML
	private TextField tfPhone;
	@FXML
	private TextField tfEmailRegister;
	@FXML
	private PasswordField tfPasswordRegister;			
	@FXML
	private Label wrongEmail;
		
	/**
	 * Librarian register a User
	 * @param event
	 * @throws IOException
	 */
	public void userRegister(ActionEvent event) throws IOException {
		registerUser();
	}
	
	public void registerUser() {
		if (tfFirstName.getText().isEmpty() || (tfLastName.getText().isEmpty()) || (tfPhone.getText().isEmpty())
				|| (tfEmailRegister.getText().isEmpty()) || (tfPasswordRegister.getText().isEmpty())) {
			wrongEmail.setText("Data is empty");
		}
		else {
			boolean verif = Librarian.register(tfFirstName, tfLastName, tfPhone, tfEmailRegister, tfPasswordRegister);
			if (!verif) {
				wrongEmail.setText("Error");
			}
			else {
				wrongEmail.setText("Register done!");
			}
		}
	}
	
	
	// Update an User
	 @FXML
	 private Label lblUpdateMessage;
	 @FXML
	 private TextField tfIdUpdate;
	 @FXML
	 private PasswordField tfPasswordUpdate;
	 @FXML
	 private TextField tfPhoneUpdate;
	 
	 public void modifyUser(ActionEvent event) {
		 update();
	 }
	 
	 /**
	  * Librarian modify a User
	  * He modifies the phone number and the password
	  */
	 public void update() {
		 boolean checkUpdate = Librarian.updateUser(tfIdUpdate, tfPhoneUpdate, tfPasswordUpdate);
		 if (checkUpdate) {
			 lblUpdateMessage.setText("User updated!");
		 }
		 else {
			 lblUpdateMessage.setText("Error");
		 }
	 }
	
	
	// Delete an User
	@FXML
	private TextField tfIdDelete;
	@FXML
	private Label lblDeleteMess;
	
	public void deleteUser(ActionEvent event) {
		delete();
	}
	
	/**
	 * Librarian deletes a User
	 */
	public void delete() {
		boolean check = Librarian.removeUser(tfIdDelete);
		if (check) {
			lblDeleteMess.setText("Done!");
		}
		else {
			lblDeleteMess.setText("Error");
		}
	}
	
	
	// See the list of User
	@FXML
	private TableView<User> tbSeeUser;
	@FXML
	private TableColumn<User, Integer> colId;
	@FXML
	private TableColumn<User, String> colFName;
	@FXML
	private TableColumn<User, String> colLName;
	@FXML
	private TableColumn<User, String> colEmail;
	@FXML
	private TableColumn<User, Integer> colPhone;
	
	ObservableList<User> listUser;
	
	/**
	 * Get informations of User in the database 'user' in a list
	 * And display them to the tableview
	 * @param event
	 * @throws IOException
	 */
	public void initializeSeeUser(ActionEvent event) {
		// Put attributes of User
		colId.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
		colFName.setCellValueFactory(new PropertyValueFactory<User,String>("firstName"));
		colLName.setCellValueFactory(new PropertyValueFactory<User,String>("lastName"));
		colEmail.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
		colPhone.setCellValueFactory(new PropertyValueFactory<User,Integer>("phone"));
		
		listUser = User.getData();
		tbSeeUser.setItems(listUser);
	}
	
	
	// See the list of Borrowing
	@FXML
    private TableView<Borrowing> tbBorrow;
	
    @FXML
    private TableColumn<Borrowing, Boolean> col_delayed;

    @FXML
    private TableColumn<Borrowing, String> col_book;

    @FXML
    private TableColumn<Borrowing, Date> col_borrowDate;

    @FXML
    private TableColumn<Borrowing, Date> col_dueDate;

    @FXML
    private TableColumn<Borrowing, Integer> col_id;

    @FXML
    private TableColumn<Borrowing, Date> col_returnDate;

    @FXML
    private TableColumn<Borrowing, String> col_user; 
    
	ObservableList<Borrowing> listBorrow;    
    
	/**
	 * Get informations of Borrowing in the database 'borrowing' in a list
	 * And display them to the tableview
	 * @param event
	 * @throws IOException
	 */
	public void initializeBorrowingHistory(ActionEvent event) throws IOException {
		//put attributes of Borrowing
		col_id.setCellValueFactory(new PropertyValueFactory<Borrowing,Integer>("id"));
		col_user.setCellValueFactory(new PropertyValueFactory<Borrowing,String>("mail"));
		col_book.setCellValueFactory(new PropertyValueFactory<Borrowing,String>("book"));
		col_borrowDate.setCellValueFactory(new PropertyValueFactory<Borrowing,Date>("borrowingDate"));
		col_returnDate.setCellValueFactory(new PropertyValueFactory<Borrowing,Date>("returnDate"));
		col_dueDate.setCellValueFactory(new PropertyValueFactory<Borrowing,Date>("dueReturnDate"));		
		col_delayed.setCellValueFactory(new PropertyValueFactory<Borrowing,Boolean>("delayed"));
		
		listBorrow = Borrowing.getData();
		tbBorrow.setItems(listBorrow);
	}
	
	
	// Accept a Borrowing
	@FXML
    private TextField tfTitleBorrowing;
	@FXML
    private TextField tfEmailBorrowing;
	@FXML
	private Label lblMessage;
	
	/**
	 * Librarian registers a Borrowing
	 * @param event
	 * @throws IOException
	 */
	public void acceptBorrowing(ActionEvent event) throws IOException {
		acceptBorrow();
	}
	
	public void acceptBorrow() {
		boolean acceptDone = Librarian.accept(tfTitleBorrowing, tfEmailBorrowing);
		if (acceptDone) {
			lblMessage.setText("You have accepted a borrow");
		}
		else {
			lblMessage.setText("Error!");
		}
	}
	
	
	// Register a return
	@FXML
	private TextField tfIdBorrowing;
	/**
	 * Librarian registers a return
	 * @param event
	 * @throws IOException
	 */
	public void registerReturn(ActionEvent event) throws IOException {
		registration();
	}
	
	public void registration() {
		boolean registerDone = Librarian.returnRegister(tfIdBorrowing);
		if (registerDone) {
			lblMessage.setText("Return is registered");
		}
		else {
			lblMessage.setText("Error!");
		}
	}
	
}
