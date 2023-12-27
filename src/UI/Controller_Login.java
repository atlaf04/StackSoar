package UI;

import Database.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller_Login { //LEXI
	@FXML
    private Button backbutton;

    @FXML
    private Button createbutton;

    @FXML
    private TextField emailtf;

    @FXML
    private Button forgetbutton;

    @FXML
    private Button loginbutton;

    @FXML
    private TextField passwordtf;
   
    private Stage primarystage;
	private Scene scene;
	private Parent root;
    
	public void handleBackButtonAction (ActionEvent e) { // back button
		
			switchScene("SplashScreen.fxml",e);

	}
	
    public void handleLoginButtonAction(ActionEvent e) { // login button
        // Retrieving the entered email and password

    	String username = emailtf.getText().trim(); //trim to get rids of all the whitespace 
    	String password = passwordtf.getText().trim();
    	try {
            // Attempting to log in using the entered credentials

    		if (Login.LogIn(username, password)) {

    			try {
                    //Login successful, switch to the "MainLogin.fxml" scene

    				FXMLLoader loader = new FXMLLoader(getClass().getResource("MainLogin.fxml"));
    				root = loader.load();
    				Controller_MainLogin mainlogincontroller = loader.getController();
    				
    				mainlogincontroller.setEmail(username);//Set the Main Log In controller email using the entered email

    				
    				primarystage = (Stage)((Node)e.getSource()).getScene().getWindow();
    				scene = new Scene(root,900,600);
    				primarystage.setScene(scene);
    				primarystage.show();
    				}
    				catch (Exception ex) { //Handling exceptions
    					System.out.println("You are in the exception box");
    				}
    		}
    		else { 
                // Login failed, display an error message
    			
    			showAlert("Error!","Could not find information, please try again");
    		}
    	}
    	catch (Exception ex){
    		// Handling exceptions that occurred during login
    		ex.printStackTrace(); // Printing the exception details to the console for reference
    	}
    }
    
    public void handleCreateAccountButtonAction(ActionEvent e) { 
        // Handling the "Create Account" button click

    	//Switch to "AccountCreation.fxml" scene
    	switchScene("AccountCreation.fxml",e);
    		
    	
    }
    public void handleForgotPasswordButtonAction (ActionEvent e) {
        // Handling the "Forget Password" button click

    	//Switch to "ForgetPassword.fxml" scene
    	try {
        	
    		switchScene("ForgotPassword.fxml",e);
        	} catch (Exception ex) {
        		
        	}
    }
 // Method to show an alert with a specified title and content text
    public void showAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    
    //Method to switch to different scene
    public void switchScene(String fxmlFileName,ActionEvent e) {
        try {
            // Load the FXML file
            root = FXMLLoader.load(getClass().getResource(fxmlFileName));
            
            // Get the current stage
    		primarystage = (Stage)((Node)e.getSource()).getScene().getWindow();
            
            // Set the new scene
            primarystage.setScene(new Scene(root, 900, 600));
            primarystage.show();
        } catch (Exception ex) {
            // Handle exceptions related to loading the new scene
            ex.printStackTrace();
        }
    }
    
// 
}