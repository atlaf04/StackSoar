package UI;

import Database.Getter;
import Database.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.Customer;

public class Controller_ForgetPassword {

	@FXML
    private TextField emailtf; // Text field to input email
    @FXML
    private TextField securityanswertf; // Text field to input security answer

    private Stage primarystage;
	private Scene scene;
	private Parent root;
	public void back (ActionEvent e) { // back button
		
		 // Show confirmation dialog
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to go back to LogIn?");

        // Get the user's response from the dialog
        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Use the buttontypke.ok to make the scene switch if the user clicks okay. 
                try {
                	root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        			
        			primarystage = (Stage)((Node)e.getSource()).getScene().getWindow();
        			scene = new Scene(root,900,600);
        			primarystage.setScene(scene);
        			primarystage.show();

   
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
	}
	
	public void retrievepassword (ActionEvent e){
        // Retrieve Password button click

		String email = emailtf.getText().trim(); // Get entered email
		String securityanswer = securityanswertf.getText().trim(); // Get entered security answer
		
		
		if (Login.RetrievePassword(email, securityanswer)) { // Attempt password retrieval using email and security answer

			Customer customer = new Customer();
			try {
				customer = Getter.getCustomer(email);
                // Password retrieval successful, show password in alert
                showAlert("Retrieve Password", "Your password is: " + customer.getPassword(), Alert.AlertType.INFORMATION);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
	            // Handle exceptions during password retrieval, show error in alert

			}
		}
		else {
            // Password retrieval failed, show error message in alert
            showAlert("Error", "Invalid email or security answer. Password retrieval failed.", Alert.AlertType.ERROR);

		
		}
	}
	public void showAlert(String string, String string2, AlertType error) {
		// TODO Auto-generated method stub
		
			Alert alert = new Alert(error);
	        alert.setTitle(string2);
	        alert.setHeaderText(null);
	        alert.setContentText(string2);
	        alert.showAndWait();

	}

}

