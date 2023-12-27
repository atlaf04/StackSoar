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

	@FXML // Annotation indicating that this class is a controller for JavaFX FXML.
	private AnchorPane anchorpane; 
	@FXML 
	private TextField firstnametf;
	@FXML 
	private TextField lastnametf; // ENCAPSULATION private fields like firstnametf, lastnametf, etc., encapsulate the internal state of the Controller_CreateAccount class.  can only be accessed within the class.
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
			scene = new Scene(root,900,600); // // Creates a new scene with the loaded root node.
			primarystage.setScene(scene);  // Sets the scene to the primary stage.
			primarystage.show(); // Shows the primary stage.
			}
			catch (Exception ex) { // Handles exceptions that may occur during the loading of the "Login.fxml" file.
				
			}
	}
	
	public void createAccount(ActionEvent e) {
		
		/**
		 *   Abstraction is achieved through the use of methods like back and createAccount because 
		 *   they  provide a higher-level interface to perform actions without exposing the internal details of how those actions are implemented.
		 *   Users of this class don't need to know the intricacies of how the back button or account creation works; they only interact with the abstracted methods.
		 */
		boolean b= true;  //  indicating whether all conditions are met.
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
			b = false; // Set the flag to false if the SSN length is not 9.
			Alert alert1 = new Alert(Alert.AlertType.ERROR);
			alert1.setTitle("Error! Alert dialog!");
			alert1.setContentText("You need to enter 9 digits for SSN");
			alert1.showAndWait();		
		}
		
		if (state.length()!=2) { //check if state length is only 2
			b = false; // Set the flag to false if the state length is not 2.
			Alert statealert = new Alert(Alert.AlertType.ERROR);
			statealert.setTitle("Error! Alert dialog!");
			statealert.setContentText("Only enter 2 characters for state");
			statealert.showAndWait();
			state = statetf.getText();	
		}
		
		
		if (!email.matches(".+@.+\\..+")) {//check the format of the email, make sure that it's proper format. // the exclamation point means not 
			b = false; // Set the flag to false if the email format is invalid.
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
		
		
		if (b) {// If all conditions are met, create the account.
			Customer cust = new Customer(email,password,firstname,lastname,address,state,zip,ssn,securityanswer);
			try {
				Insert.insertCustomer(cust); // attempts to insert the new customer into the database.
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
				alert.showAndWait(); // show(): Displays the alert to the user. AndWait(): Indicates that the method will not return until the alert is closed by the user.
				}
			}
	
		
		}
		catch (NumberFormatException ex) { // Catch exception if SSN and ZIP code can't be converted to int

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error! Alert dialog!");
			alert.setContentText("Enter only number for SSN/zip");
			alert.showAndWait();
			b = false; // Show an alert for invalid SSN or ZIP code format.

			
		}
	}
}
