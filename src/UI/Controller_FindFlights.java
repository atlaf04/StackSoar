package UI;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Database.Getter;
import Exceptions.ExistingBooking;
import Exceptions.FullException;
import Exceptions.TimeConflict;
import Database.Booking;
import project.Flights;
import project.FlightReservation;

public class Controller_FindFlights {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private TableColumn<Flights, String> arrivaldateview;

    @FXML
    private DatePicker arrivaldp;

    @FXML
    private TableColumn<Flights, String> arrivaltimeview;

    @FXML
    private TableColumn<Flights, String> departuredateview;

    @FXML
    private DatePicker departuredp;

    @FXML
    private TableColumn<Flights, String> departuretimeview;

    @FXML
    private TextField destinationtf;

    @FXML
    private TableColumn<Flights, String> destinationview;

    @FXML
    private TextField origintf;

    @FXML
    private TableColumn<Flights, String> originview;

    @FXML
    private TableColumn<Flights, String> idview;

    @FXML
    private TableView<Flights> tableview;

    private String email;

    private ObservableList<Flights> flightlist;

    private Stage primarystage;
    private Scene scene;
    private Parent root;

    public void back(ActionEvent e) { // back button
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainLogin.fxml"));
            root = loader.load();
            Controller_MainLogin mainlogincontroller = loader.getController();
            mainlogincontroller.setEmail(this.getEmail()); // set email of maincontroller using the email of this class

            primarystage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root, 900, 600);
            primarystage.setScene(scene);
            primarystage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getDepartureDate(ActionEvent e) { // get depart date from datetime
        return departuredp.getValue().toString();
    }

    public String getArrivalDate(ActionEvent e) { // get arrival date from datetime
        return arrivaldp.getValue().toString();
    }

    public void Search(ActionEvent e) { // handle search button
        String arrivaldate = this.getArrivalDate(e);
        String departuredate = this.getDepartureDate(e);
        String origin = origintf.getText().trim();
        String destination = destinationtf.getText().trim();

        try { // try get array of flights and convert it to observable list
            ArrayList<Flights> searchflights = Getter.getFlights(origin, destination, departuredate, arrivaldate);
            flightlist = FXCollections.observableArrayList(searchflights);
            this.initialize();

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error! Alert dialog!");
            alert.setContentText("Something went wrong, please try again");
            alert.showAndWait();
        }
    }
    @FXML
    public void initialize() { 

    	idview.setCellValueFactory(new PropertyValueFactory<>("flightid"));
        originview.setCellValueFactory(new PropertyValueFactory<>("originalairport"));
        departuredateview.setCellValueFactory(new PropertyValueFactory<>("departuredate"));
        departuretimeview.setCellValueFactory(new PropertyValueFactory<>("departuretime"));
        destinationview.setCellValueFactory(new PropertyValueFactory<>("destinationairport"));
        arrivaldateview.setCellValueFactory(new PropertyValueFactory<>("arrivaldate"));
        arrivaltimeview.setCellValueFactory(new PropertyValueFactory<>("arrivaltime"));

        tableview.setItems(flightlist);
    }

    @FXML
    public void Book(ActionEvent e) { // handle book button
        Flights selectedflight = tableview.getSelectionModel().getSelectedItem(); // get the information of the selected flight
        try {
            Booking.bookFlight(this.getEmail(), selectedflight.getFlightid()); // book the flight
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Booked flight successfully ");
            alert.showAndWait();

        } catch (ExistingBooking ex) { // handle duplicate booking
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("You cannot book the same flight");
            alert.showAndWait();
        } catch (TimeConflict ex) { // handle any time conflict
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("There is a time conflict, please choose another flight");
            alert.showAndWait();
        } catch (FullException ex) { // handle full flight
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Flight is full, please choose another flight");
            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Booking unsuccessful, please try again");
            alert.showAndWait();
        }
    }
}
