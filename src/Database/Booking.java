package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import Exceptions.ExistingBooking;
import Exceptions.FullException;
import Exceptions.TimeConflict;
import project.Flights;

public class Booking {

    // Abstraction: Database connection details are abstracted from the calling code
    static final String db_url = Getter.db_url;  // final because this is not intended to be changed. 
    static final String db_username = Getter.db_username;
    static final String db_pw = Getter.db_pw;

    /**
     * Polymorphism: This method books a flight for a customer. It throws various
     * exceptions, demonstrating polymorphism in exception handling.
     */
    public static void bookFlight(String customeremail, String flightid) throws Exception {
        try {
            // Abstraction: Database connection details are abstracted within the getConnection method
            Connection con = getConnection();

            // Polymorphism: PreparedStatement usage allows flexibility in handling different queries
            PreparedStatement s = con.prepareStatement("Select Count(*) from Reservation_Table where CustomerEmail = ? and FlightId = ?"); // the question marks serve as a placeholder for the parameters. the question mark is a way of telling the database that there will be values provided for these positions when the statement is executed, and those values will be safe and properly formatted
            s.setString(1, customeremail);   // 1: This is the index of the parameter in the SQL query. It starts from 1.
            s.setString(2, flightid); // we are setting the string with its respective paramters

            ResultSet r = s.executeQuery(); // This method executes the SQL query and returns a ResultSet, which contains the count of reservations for the given customer and flight combination.
            r.next(); // This method moves the cursor to the first row of the result set. In this context, it's used to check the count of reservations.


            if (r.getInt(1) == 0) {//  checks if the count of reservations for the given customer and flight is zero. If it is, it means the customer has not booked the same flight.
                PreparedStatement s1 = con.prepareStatement("Select Count(*) from Flight_Table where FlightId = ? and SeatsAvailable = 0;"); 
                s1.setString(1, flightid); // sets the value for the placeholder in the prepared statement to the given flightid. //

                ResultSet r1 = s1.executeQuery();
                r1.next(); // This method moves the cursor to the first row of the result set. In this context, it's used to check the count of reservations.


                if (r1.getInt(1) == 0) { // If the flight is not full
                    Flights flight = Getter.getFlight(flightid);
                    ArrayList<Flights> flightlist = Getter.getAllFlightsfromAccount(customeremail); // we use an array list to have a table with all of the flitght data 

                    int booktime = Integer.parseInt(flight.getDeparturetime().substring(0, 2)); // use parseInt to convert string to number 
                    String bookday = flight.getDeparturedate();
                    boolean b = true;

                    for (int i = 0; i < flightlist.size(); i++) {
                        int departuretime = Integer.parseInt(flightlist.get(i).getDeparturetime().substring(0, 2)); // 
                        int arrivaltime = Integer.parseInt(flightlist.get(i).getArrivaltime().substring(0, 2));
                        String departureday = flightlist.get(i).getDeparturedate();
                        
                        /**
                         * flight.getDeparturetime(): This retrieves the departure time from the Flight object represented by the variable flight. The departure time is assumed to be a string, such as "09:30" (HH:mm format).
.							substring(0, 2): The substring(0, 2) method is applied to the departure time string. This method extracts a portion of the string starting from index 0 (inclusive) to index 2 (exclusive). In our example ("09:30"), it takes the characters at positions 0 and 1, resulting in the substring "09".
							Integer.parseInt(...): The parseInt method is used to convert the substring into an integer. "09" as a string is converted to the integer value 9.
                         */

                        if (bookday.equals(departureday) && booktime > departuretime && booktime < arrivaltime) {
                            b = false; // / set the boolean flag 'b' to false, indicating a time conflict.
                        }
                    }

                    if (b) { // if there are no time conflicts 
                        Random rand = new Random();
                        int reservationid = rand.nextInt(10000); // Generate a random reservation ID generates a random reservation ID

                        PreparedStatement statement = con.prepareStatement("Select Count(*) from Reservation_Table where ReservationID = ?");
                        statement.setInt(1, reservationid);

                        ResultSet result = statement.executeQuery();
                        result.next();
                        int exist = result.getInt(1);

                        while (exist == 1) { // this ensures the generated reservation ID is unique
                            reservationid = rand.nextInt(10000);
                            PreparedStatement statement2 = con.prepareStatement("Select Count(*) from Reservation_Table where ReservationID = ?");
                            statement2.setInt(1, reservationid);

                            ResultSet result2 = statement2.executeQuery();
                            result2.next();
                            exist = result2.getInt(1);
                        }

                        try {
                            if (Insert.addBooking(reservationid, customeremail, flightid)) { // // Attempt to insert the booking details into the database
                                PreparedStatement update = con.prepareStatement("Update Flight_Table set SeatsAvailable = SeatsAvailable - 1 where FlightID = ?");
                                update.setString(1, flightid);

                                update.executeUpdate();
                            }
                        } catch (Exception ex) {
                            System.out.println("Can not insert booking");
                        }
                    } else {
                        throw new TimeConflict("Time conflict"); // Throw a TimeConflict exception if a time conflict is detected
                    }

                } else {
                    throw new FullException("Flight is full"); // throws exception if the flight is full 
                }
            } else {
                // Polymorphism: Different types of exceptions can be thrown
                throw new ExistingBooking("Booking error! You already have booked this flight."); // throws exception if flight is already booked
            }
        } catch (Exception e) {
            // Polymorphism: Different types of exceptions can be thrown
            throw e;
        }
    }

    /**
     * This method deletes a booking based on reservation ID.
     *
     * @param reservationid Reservation ID to be deleted.
     * @throws Exception Various exceptions can be thrown.
     */
    public static void deleteBooking(int reservationid) throws Exception {
        try {
            // Abstraction: Database connection details are abstracted within the getConnection method
            Connection con = getConnection();

            PreparedStatement statement2 = con.prepareStatement("Select FlightID from Reservation_Table where ReservationID = ?");
            statement2.setInt(1, reservationid); //1 represents index of the parameter for reservationid. statement 2 uses set int to set 1 to be the paramter index for reservationid . 1 represents the index of the parameter in the SQL query, and it starts from 1.

            ResultSet result = statement2.executeQuery();
            result.next();

            String flightid = result.getString(1);
            System.out.println(flightid);

            PreparedStatement statement = con.prepareStatement("Delete from Reservation_Table where ReservationID = ?");
            statement.setInt(1, reservationid); //1 represents index of the parameter for reservationid

            statement.executeUpdate();

            PreparedStatement update = con.prepareStatement("Update Flight_Table set SeatsAvailable = SeatsAvailable + 1 where FlightID = ?");
            update.setString(1, flightid);

            update.executeUpdate();

            System.out.println("Delete flight successfully");

        } catch (Exception ex) {
            System.out.println("Unable to delete flight");
        }
    }

    /**
     * This method establishes a database connection.
     *
     * @return Connection object.
     * @throws Exception Various exceptions can be thrown.
     */
    public static Connection getConnection() throws Exception {
        try {
            // Abstraction: JDBC driver loading and connection establishment details are hidden
            String driver = "com.mysql.cj.jdbc.Driver"; //  specifies the JDBC driver class for MySQL. drivers are platform specific 
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(db_url, db_username, db_pw); // DriverManager class is used to establish a connection to the database.  takes three parameters: the database URL, the username, and the password.
            return conn; // if it is successful, the connection will be returned.
        } catch (Exception ex) {
            System.out.println(ex); // prints exception details to the console 
        }
        return null; // helps prevent a nullpointer exception
    }

	public static void bookFlight1(String email, String flightid) {
		// TODO Auto-generated method stub
		
	}
}
