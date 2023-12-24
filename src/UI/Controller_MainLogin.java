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
		}

		public void setEmail(String email) {
			this.email = email;
			try {
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
		
		public void handleBookflightbutton (ActionEvent e) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("FindFlight.fxml"));
				root = loader.load();
				Controller_FindFlights findflightcontroller = loader.getController();
				findflightcontroller.setEmail(this.getEmail());

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
				FXMLLoader loader = new FXMLLoader(getClass().getResource("FlightReservations.fxml"));
				root = loader.load();
				Controller_Reservation reservationcontroller = loader.getController();
				reservationcontroller.setEmail(this.getEmail());
				reservationcontroller.initialize();
				
				
				primarystage = (Stage)((Node)e.getSource()).getScene().getWindow();
				scene = new Scene(root,900,600);
				primarystage.setScene(scene);
				primarystage.show();
				}
				catch (Exception ex) {
					
				}
		}

}