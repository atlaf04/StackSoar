package UI;

import Database.Getter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller_MainLogin {
	  @FXML
	  private AnchorPane anchorpane;

	   @FXML
	   private Label welcomebacklabel;
	   
	   private String email;
	   
	   private Stage primarystage;
	   private Scene scene;
	   private Parent root;
	   

		public String getEmail() {
			return email;
		    // Getter and setter for the email property
		}

		public void setEmail(String email) {
			this.email = email;
			try {
	            // Update the welcome label with the customer's first name, using the getter method to extract information from the database
				welcomebacklabel.setText("Welcome back " + Getter.getCustomer(email).getFirstname());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
				
		public void handleBackbutton (ActionEvent e) { // back button
			
			 // Show confirmation dialog
	        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
	        confirmationAlert.setTitle("Confirmation");
	        confirmationAlert.setHeaderText(null);
	        confirmationAlert.setContentText("Are you sure you want to log out?");

	        // Get the user's response from the dialog
	        confirmationAlert.showAndWait().ifPresent(response -> {
	            if (response == ButtonType.OK) {
	                // Use the buttontypke.ok to make the scene switch if the user clicks okay. 
	                try {
	                	root = FXMLLoader.load(getClass().getResource("Login.fxml"));
	        			
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
		
		public void handleBookflightbutton (ActionEvent e) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("FindFlights.fxml"));
				root = loader.load();
				Controller_FindFlights findflightcontroller = loader.getController();
				findflightcontroller.setEmail(this.getEmail()); // 

				/**
				 * setting the email property of the Controller_FindFlights 
				 * controller with the email value obtained from the current instance of Controller_MainLogin using this.getEmail().
				 */
				
				primarystage = (Stage)((Node)e.getSource()).getScene().getWindow();
				scene = new Scene(root,900,600);
				primarystage.setScene(scene);
				primarystage.show();
				}
				catch (Exception ex) {
					
				}
		}
		
		public void handleManageReservationsbutton(ActionEvent e) {
		    try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("FlightReservation.fxml"));
		        Parent root = loader.load();

		        // Use the correct controller type
		        Controller_Reservation reservationController = loader.getController();
		        reservationController.setEmail(this.getEmail());
		        reservationController.initialize();

		        Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		        Scene scene = new Scene(root, 900, 600);
		        primaryStage.setScene(scene);
		        primaryStage.show();
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		}

		}