package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Importing custom exception for handling existing customer scenario.
import Exceptions.ExistingCustomer;

// Importing classes from the 'project' package.
import project.Customer;
import project.Flights;

// The class responsible for inserting data into the database.
public class Insert {

    // Database connection details.
    static final String db_url = "jdbc:mysql://127.0.0.1:3306/?user=root";
    static final String db_username = "root";
    static final String db_pw = "August13";

    // Method to insert a new customer into the database.
    public static boolean insertCustomer(Customer cust) throws Exception {
        // Flag to track the success of the operation.
        boolean success = true;

        // Extracting customer details from the provided object.
        final String firstname = cust.getFirstname();
        final String lastname = cust.getLastname();
        final String email = cust.getEmail();
        final String password = cust.getPassword();
        final String address = cust.getAddress();
        final String state = cust.getState();
        final int zip = cust.getZip();
        final int ssn = cust.getSsn();
        final String answer = cust.getSecurityanswer();

        try {
            // Establish a database connection.
            Connection con = getConnection();

            // Check if the customer with the same email already exists.
            PreparedStatement check = con.prepareStatement(
                    "Select COUNT(*) from Customer_Table where CustomerEmail = '" + email + "'");
            ResultSet exist = check.executeQuery();
            exist.next();
            if (exist.getInt(1) == 1) {
                // If the customer already exists, set success flag to false and throw an exception.
                success = false;
                throw new ExistingCustomer("Customer already exists");
            } else {
                // If the customer does not exist, proceed with insertion.
                PreparedStatement insert = con.prepareStatement("Insert into Customer_Table VALUES ('" + email + "','"
                        + password + "','" + firstname + "','" + lastname + "','" + address + "','" + state + "','"
                        + zip + "','" + ssn + "','" + answer + "')");
                success = true;
                // Execute the insertion query.
                insert.executeUpdate();
                System.out.println("Insert SUCCESS");
            }
        } catch (Exception ex) {
            // If an exception occurs, throw it to the caller.
            throw ex;
        } finally {
            // Any cleanup code can go here.
        }
        // Return the success flag.
        return success;
    }

    // Method to add a new flight to the database.
    public static boolean addFlight(Flights flight) {
        // Flag to track the success of the operation.
        boolean success = false;

        // Extracting flight details from the provided object.
        final int flightid = flight.getFlightid();
        final String departuredate = flight.getDeparturedate();
        final String departuretime = flight.getDeparturetime();
        final String arrivaldate = flight.getArrivaldate();
        final String arrivaltime = flight.getArrivaltime();
        final String originalairport = flight.getOriginalairport();
        final String destinationairport = flight.getDestinationairport();
        final int capacity = flight.getCapacity();
        final int seatsavailable = flight.getSeatsavailable();

        try {
            // Establish a database connection.
            Connection con = getConnection();

            // Prepare and execute the insertion query for the flight.
            PreparedStatement statement = con.prepareStatement("Insert into Flight_Table VALUES ('" + flightid + "','"
                    + departuredate + "','" + departuretime + "','" + arrivaldate + "','" + arrivaltime + "','"
                    + originalairport + "','" + destinationairport + "','" + capacity + "','" + seatsavailable + "')");
            // Execute the insertion query.
            statement.executeUpdate();
            // Set success flag to true.
            success = true;
        } catch (Exception ex) {
            // If an exception occurs, print an error message.
            System.out.println("Insert flights unsuccessfully");
        }
        // Return the success flag.
        return success;
    }

    // Method to add a new booking to the database.
    public static boolean addBooking(int reservationid, String customeremail, int flightid) throws Exception {
        // Flag to track the success of the operation.
        boolean b = false;

        try {
            // Establish a database connection.
            Connection con = getConnection();

            // Prepare and execute the insertion query for the booking.
            PreparedStatement statement = con.prepareStatement("Insert into Reservation_Table VALUES ('" + reservationid
                    + "','" + customeremail + "','" + flightid + "');");
            // Execute the insertion query.
            statement.executeUpdate();
            // Set success flag to true.
            b = true;
            System.out.println("Insert success");

        } catch (Exception ex) {
            // If an exception occurs, print an error message.
            System.out.println("Insert booking unsuccessfully");

        }
        // Return the success flag.
        return b;
    }

    // Method to establish a database connection.
    public static Connection getConnection() throws Exception {
        try {
            // Load the MySQL JDBC driver and establish a connection.
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(db_url, db_username, db_pw);
            return conn;
        } catch (Exception ex) {
            // If an exception occurs, print the exception details.
            System.out.println(ex);
        }
        // Return null if the connection could not be established.
        return null;
    }
}