package UI;

import java.util.ArrayList;

// Import necessary JavaFX and database classes
import javax.print.attribute.standard.Destination;
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

// The main controller class for the Reservation UI
public class Controller_Reservation {

    // JavaFX Annotations for various UI components
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
    private TableColumn<Flights, Integer> flightidview;
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

    // Additional private variables
    private String email;
    private ObservableList<FlightReservation> reservationlist;
    private ObservableList<Flights> flightlist;
    private Stage primarystage;
    private Scene scene;
    private Parent root;

    // Getter and setter methods for the email property
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Method for handling the back button action
    public void back(ActionEvent e) {
        try {
            // Load the MainLogin.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainLogin.fxml"));
            root = loader.load();

            // Access the MainLogin controller and set the email
            Controller_MainLogin mainlogincontroller = loader.getController();
            mainlogincontroller.setEmail(this.getEmail());

            // Get the current stage and set the scene
            primarystage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root, 900, 600);
            primarystage.setScene(scene);
            primarystage.show();
        } catch (Exception ex) {
            // Handle any exceptions that may occur during the process
        }
    }

    // Initialization method for the controller
    @FXML
    public void initialize() {
        try {
            // Retrieve reservations and flights for the current user
            ArrayList<FlightReservation> reservations = Getter.getReservations(this.getEmail());
            ArrayList<Flights> flights = Getter.getAllFlightsfromAccount(this.getEmail());

            // Create observable lists for flights and reservations
            flightlist = FXCollections.observableArrayList(flights);
            reservationlist = FXCollections.observableArrayList(reservations);

            // Set up cell value factories for flight table columns
            flightidview.setCellValueFactory(new PropertyValueFactory<Flights,Integer>("flightid"));
            originview.setCellValueFactory(new PropertyValueFactory<Flights,String>("originalairport"));
            departuredview.setCellValueFactory(new PropertyValueFactory<Flights,String>("departuredate"));
            departuretview.setCellValueFactory(new PropertyValueFactory<Flights,String>("departuretime"));
            destinationview.setCellValueFactory(new PropertyValueFactory<Flights,String>("destinationairport"));
            arrivaldview.setCellValueFactory(new PropertyValueFactory<Flights,String>("arrivaldate"));
            arrivaltview.setCellValueFactory(new PropertyValueFactory<Flights,String>("arrivaltime"));

            // Set the items for the flight table view
            flighttableview.setItems(flightlist);

            // Set up cell value factory for reservation table column
            reservationidview.setCellValueFactory(new PropertyValueFactory<FlightReservation,Integer>("reservationid"));

            // Set the items for the reservation table view
            reservationidtable.setItems(reservationlist);

        } catch (Exception ex) {
            // Display an error message in case of any exceptions
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error! Alert dialog!");
            alert.setContentText("Something went wrong, please try again");
            alert.showAndWait();
        }
    }

    // Method for handling the delete booking action
    public void deleteBooking(ActionEvent e) {
        // Get the selected reservation from the table
        FlightReservation selectedreservation = reservationidtable.getSelectionModel().getSelectedItem();

        try {
            // Delete the selected reservation using the Booking class
            Booking.deleteBooking(selectedreservation.getReservationId());

            // Display a success message in a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Delete reservation " + selectedreservation.getReservationId() + " successfully");
            alert.showAndWait();

            // Reinitialize the controller to show the updated reservations
            this.initialize();

        } catch (Exception ex) {
            // Handle any exceptions that may occur during the process
        }
    }
}
