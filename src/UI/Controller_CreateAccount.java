package UI;
import Database.Insert;
import Exceptions.ExistingCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.Customer;

public class Controller_CreateAccount {

	@FXML 
	private AnchorPane anchorpane;
	@FXML 
	private TextField firstnametf;
	@FXML 
	private TextField lastnametf;
	@FXML 
	private TextField ssntf;
	@FXML 
	private TextField addresstf;
	@FXML 
	private TextField statetf;
	@FXML 
	private TextField ziptf;
	@FXML 
	private TextField emailtf;
	@FXML 
	private TextField passwordtf;
	@FXML 
	private TextField securityanswertf;
	@FXML
	private Button createButtontf;
	
	
	private Stage primarystage;
	private Scene scene;
	private Parent root;
	public void back(ActionEvent e) { // back button
		try {
			root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			
			primarystage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root,900,600);
			primarystage.setScene(scene);
			primarystage.show();
			}
			catch (Exception ex) {
				
			}
	}
	
	public void createAccount(ActionEvent e) {
		boolean b= true;
		try {//get users input from users textfield
		String firstname = firstnametf.getText().trim();
		String lastname = lastnametf.getText().trim();
		String address = addresstf.getText().trim();
		String state = statetf.getText().trim();
		String email = emailtf.getText().trim();
		String password = passwordtf.getText().trim();
		String securityanswer = securityanswertf.getText().trim();
		String ssntext = ssntf.getText().trim();
		
		if (ssntext.length() !=9) {	//check if ssn is 9 numbers
			b = false;
			Alert alert1 = new Alert(Alert.AlertType.ERROR);
			alert1.setTitle("Error! Alert dialog!");
			alert1.setContentText("You need to enter 9 digits for SSN");
			alert1.showAndWait();		
		}
		
		if (state.length()!=2) { //check if state length is only 2
			b = false;
			Alert statealert = new Alert(Alert.AlertType.ERROR);
			statealert.setTitle("Error! Alert dialog!");
			statealert.setContentText("Only enter 2 characters for state");
			statealert.showAndWait();
			state = statetf.getText();	
		}
		
		
		if (!email.matches(".+@.+\\..+")) {//check the format of the email, make sure that it's proper format
			b = false;
			Alert emailalert = new Alert(Alert.AlertType.ERROR);
			emailalert.setTitle("Error! Alert dialog!");
			emailalert.setContentText("Invalid input for email!");
			emailalert.showAndWait();
			email = emailtf.getText();	
		}
		
		int ssn = 0;
		ssn = Integer.parseInt(ssntext); //convert string to int
		int zip = 0;
		zip = Integer.parseInt(ziptf.getText()); // convert string to int
		
		
		if (b) { //if met all conditions, create account
			Customer cust = new Customer(email,password,firstname,lastname,address,state,zip,ssn,securityanswer);
			try {
				Insert.insertCustomer(cust);
				Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
				alert2.setTitle("Success");
				alert2.setContentText("Account is created");
				alert2.showAndWait();
			} catch (ExistingCustomer ex) { //check if customer already exists
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Failed");
				alert.setContentText("Email provided is already in use, try again");
				alert.showAndWait();
			}
			catch (Exception e1) { //handle all other exceptions
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Failed");
				alert.setContentText("Something went wrong, try again");
				alert.showAndWait();
				}
			}
	
		
		}
		catch (NumberFormatException ex) { // catch exception if ssn and zipcode can't be convereted to int
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error! Alert dialog!");
			alert.setContentText("Enter only number for SSN/zip");
			alert.showAndWait();
			b = false;
			
		}
		
	}
	

}
