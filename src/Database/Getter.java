package Database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.Customer;
import project.Flights;
import project.FlightReservation;
import java.util.ArrayList;
import java.util.List;
public class Getter {

	static final String db_url = "jdbc:mysql://127.0.0.1:3306/?user=root";
	static final String db_username = "root";
	static final String db_pw = "August13";

	public static Customer getCustomer(String email) throws Exception {
		Customer cust = new Customer();
		try {
			Connection con = getConnection();
			PreparedStatement check = con.prepareStatement("Select COUNT(*) from Customer_Table where CustomerEmail = '" + email + "'");
			ResultSet exist = check.executeQuery();
			exist.next(); 
			if (exist.getInt(1) == 0) {//checking if customer exists in the database
				System.out.println("Customer does not exist");
            }
            else {

			PreparedStatement statement = con.prepareStatement("Select * from Customer_Table where CustomerEmail = '" + email + "'");
			ResultSet result = statement.executeQuery();
			while (result.next()) { // try getting the customer information and set
				
				cust.setEmail(result.getString(1));
				cust.setPassword(result.getString(2));
				cust.setFirstname(result.getString(3));
				cust.setLastname(result.getString(4));
				cust.setAddress(result.getString(5));
				cust.setState(result.getString(6));
				cust.setZip(result.getInt(7));
				cust.setSsn(result.getInt(8));
				cust.setSecurityanswer(result.getString(9));
}
			statement.close();
			result.close();
            }
            
		} catch (Exception e) {
			System.out.println("Could not find the customer information in the system. Please try again.");
		}
		return cust; // return the customer that has all the informations
        
    }

	
	public static Flights getFlight(int id) throws Exception { // get flight using flightid
		Flights flight = new Flights(); 
		try { 
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("Select * from Flight_Table where FlightID = '" + id + "'");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				flight.setFlightid(result.getInt("FlightID"));
				flight.setDeparturedate(result.getString("DepartureDate"));
				flight.setDeparturetime(result.getString("DepartureTime"));
				flight.setArrivaldate(result.getString("ArrivalDate"));
				flight.setArrivaltime(result.getString("ArrivalTime"));
				flight.setOriginalairport(result.getString("Origin"));
				flight.setDestinationairport(result.getString("Destination"));
				flight.setCapacity(result.getInt("Capacity"));
				flight.setSeatsavailable(result.getInt("SeatsAvailable"));
				
			}
			statement.close();
			result.close();
		}
		
		catch (Exception ex) {
			System.out.println("Could not find flight information in the system. Please try again.");
		}
		return flight;
    }
	public static Flights getFlight(String origin, String destination, String departuredate, String arrivaldate) throws Exception { //get flight using origin, destination, departuredate, and arrivaldate
		Flights flight = new Flights(); 
		try { 
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("Select * from Flight_Table where Origin = '" + origin + "' and Destination = '" + destination +"' and DepartureDate = '"+ departuredate + "' and ArrivalDate = '"+ arrivaldate + "';");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				flight.setFlightid(result.getInt("FlightID"));
				flight.setDeparturedate(result.getString("DepartureDate"));
				flight.setDeparturetime(result.getString("DepartureTime"));
				flight.setArrivaldate(result.getString("ArrivalDate"));
				flight.setArrivaltime(result.getString("ArrivalTime"));
				flight.setOriginalairport(result.getString("Origin"));
				flight.setDestinationairport(result.getString("Destination"));
				flight.setCapacity(result.getInt("Capacity"));
				flight.setSeatsavailable(result.getInt("SeatsAvailable"));

			}
			statement.close();
			result.close();

		}
		catch (Exception ex) {
			System.out.println("Could not find flight information in the system. Please try again.");
		}
		return flight;
    }

	public static FlightReservation getReservation(int reservationid, String customeremeail) throws Exception {//get reservation using reservation id and customeremail
		FlightReservation reservation = new FlightReservation();
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("Select * from Reservation_T where ReservationId = '" + reservationid+ "' and CustomerEmail = '"+ customeremeail + "'");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				reservation.setReservationId(result.getInt("ReservationID"));
				reservation.setCustomerEmail(result.getString("CustomerEmail"));;
				reservation.setFlightId(result.getInt("FlightID"));
			}
			statement.close();
			result.close();

		} catch (Exception ex) {
			System.out.println("Can't get reservation");
		}
		return reservation;
    
    }
	public static ArrayList<FlightReservation> getReservations(String customerEmail) throws Exception {// get lists of reservations of a customer
		ArrayList<FlightReservation> reservations = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM Reservation_T WHERE CustomerEmail = ?");

			statement.setString(1, customerEmail);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				FlightReservation reservation = new FlightReservation();
				reservation.setReservationId(result.getInt("ReservationID"));
				reservation.setCustomerEmail(result.getString("CustomerEmail"));
				reservation.setFlightId(result.getInt("FlightID"));

					reservations.add(reservation);
            }
        } catch (Exception ex) {
			System.out.println("Error retrieving reservation information: " + ex.getMessage());
		}

		return reservations;
    }

	public static ArrayList<Flights> getFlights(String origin, String destination, String departureDate, String arrivalDate) throws Exception { // get lists of flight
		ArrayList<Flights> flights = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM Flight_T WHERE Origin = ? AND Destination = ? AND DepartureDate = ? AND ArrivalDate = ?");
			statement.setString(1, origin);
			statement.setString(2, destination);
			statement.setString(3, departureDate);
			statement.setString(4, arrivalDate);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Flights flight = new Flights();
				flight.setFlightid(result.getInt("FlightID"));
				flight.setDeparturedate(result.getString("DepartureDate"));
				flight.setDeparturetime(result.getString("DepartureTime"));
				flight.setArrivaldate(result.getString("ArrivalDate"));
				flight.setArrivaltime(result.getString("ArrivalTime"));
				flight.setOriginalairport(result.getString("Origin"));
				flight.setDestinationairport(result.getString("Destination"));
				flight.setCapacity(result.getInt("Capacity"));
				flight.setSeatsavailable(result.getInt("SeatsAvailable"));
				
				
				flights.add(flight);
            }
        } 
			catch (SQLException e) {
				e.printStackTrace();
        }
        catch (Exception ex) {
			System.out.println("Error retrieving flight information: " + ex.getMessage());
		} 

		return flights;
    }

	public static ArrayList<Flights> getAllFlightsfromAccount(String email) throws Exception { // get all flights from one account
		ArrayList<Flights> flights = new ArrayList<Flights>();
		ArrayList<FlightReservation> reservations = Getter.getReservations(email); //first get the list of reservations
		for (int i = 0; i< reservations.size();i++) {
			flights.add(Getter.getFlight(reservations.get(i).getFlightId())); // then go through that list of reservations, get the flight id, then use the flight id to get the flights -> then add the flights into the array
		}
		return flights;
        
    }
	
	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(db_url,db_username,db_pw);
			return conn;
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
		return null;
    }

}