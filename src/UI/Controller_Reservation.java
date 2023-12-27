package UI;

import java.util.ArrayList;

import Database.Booking;
import Database.Getter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.Flights;
import project.FlightReservation;

public class Controller_Reservation {
	@FXML
    private AnchorPane anchorpane;

    @FXML
    private TableColumn<Flights, String> arrivaldview;

    @FXML
    private TableColumn<Flights, String> arrivaltview;

    @FXML
    private Button backbutton;

    @FXML
    private Button deletebutton;

    @FXML
    private TableColumn<Flights, String> departuredview;

    @FXML
    private TableColumn<Flights, String> departuretview;

    @FXML
    private TableColumn<Flights, String> destinationview;

    @FXML
    private TableColumn<Flights, String> flightidview;

    @FXML
    private TableColumn<Flights, String> originview;

    @FXML
    private TableView<Flights> flighttableview;
    @FXML
    private TableView<FlightReservation> reservationidtable;
    @FXML
    private TableColumn<FlightReservation, Integer> reservationidview;

    @FXML
    private Label welcomelabel;
    private String email;


	private ObservableList<FlightReservation> reservationlist;
	private ObservableList<Flights> flightlist;

	
    private Stage primarystage;
	private Scene scene;
	private Parent root;
	

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void back (ActionEvent e) { // back button
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainLogin.fxml"));
			root = loader.load();
			Controller_MainLogin mainlogincontroller = loader.getController();
			mainlogincontroller.setEmail(this.getEmail()); //switch back to main login but keep information of the email			
			
			primarystage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root,900,600);
			primarystage.setScene(scene);
			primarystage.show();
			}
			catch (Exception ex) {
				
			}
	}
	
	@FXML
	public void initialize() {  // initialize() method is used for initializing the controller's state when the associated FXML file is loaded. 
		try {
			ArrayList<FlightReservation> reservations = Getter.getReservations(this.getEmail());	 // get reservations list of customers from the flight reservation class
		
			ArrayList<Flights> flights = Getter.getAllFlightsfromAccount(this.getEmail()); // get flights list of customersGetter.getAllFlightsfromAccount(this.getEmail()): This method call retrieves a list of flights associated with the customer whose email is obtained using this.getEmail(). The result is stored in the flights ArrayList.

			flightlist = FXCollections.observableArrayList(flights); // The retrieved flights are then stored in an ObservableList to be used in the user interface.
			reservationlist = FXCollections.observableArrayList(reservations);


			flightidview.setCellValueFactory(new PropertyValueFactory<Flights,String>("flightid"));
			originview.setCellValueFactory(new PropertyValueFactory<Flights,String>("originalairport"));
			departuredview.setCellValueFactory(new PropertyValueFactory<Flights,String>("departuredate"));
			departuretview.setCellValueFactory(new PropertyValueFactory<Flights,String>("departuretime"));
			destinationview.setCellValueFactory(new PropertyValueFactory<Flights,String>("destinationairport"));
			arrivaldview.setCellValueFactory(new PropertyValueFactory<Flights,String>("arrivaldate"));
			arrivaltview.setCellValueFactory(new PropertyValueFactory<Flights,String>("arrivaltime"));
			
			flighttableview.setItems(flightlist);
			
			reservationidview.setCellValueFactory(new PropertyValueFactory<FlightReservation,Integer>("reservationid"));
			reservationidtable.setItems(reservationlist);
		
			
		} catch (Exception ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error! Alert dialog!");
			alert.setContentText("Something went wrong, please try again");
			alert.showAndWait();
		}
		
	}
	
	public void deleteBooking(ActionEvent e) { // handle delete booking
		FlightReservation selectedreservation = new FlightReservation();
		selectedreservation = reservationidtable.getSelectionModel().getSelectedItem(); // get the selected reservationid
		try {
			Booking.deleteBooking(selectedreservation.getReservationId()); // delete the selected reserbationid
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Success");
			alert.setContentText("Delete resveration "+ selectedreservation.getReservationId() + " successfully");
			alert.showAndWait();
			
			this.initialize(); // initialize again so it will show the updated reservations
		} catch (Exception ex) { // handle any exceptions
			
		}
	}
	
}