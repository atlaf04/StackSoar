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
    static final String db_url = Getter.db_url;
    static final String db_username = Getter.db_username;
    static final String db_pw = Getter.db_pw;

    /**
     * Polymorphism: This method books a flight for a customer. It throws various
     * exceptions, demonstrating polymorphism in exception handling.
     */
    public static void bookFlight(String customeremail, int flightid) throws Exception {
        try {
            // Abstraction: Database connection details are abstracted within the getConnection method
            Connection con = getConnection();

            // Polymorphism: PreparedStatement usage allows flexibility in handling different queries
            PreparedStatement s = con.prepareStatement("Select Count(*) from Reservation_T where CustomerEmail = ? and FlightId = ?");
            s.setString(1, customeremail);
            s.setInt(2, flightid);

            ResultSet r = s.executeQuery();
            r.next();

            if (r.getInt(1) == 0) { // If the customer has not booked the same flight
                PreparedStatement s1 = con.prepareStatement("Select Count(*) from Flight_T where FlightId = ? and SeatsAvailable = 0;");
                s1.setInt(1, flightid);

                ResultSet r1 = s1.executeQuery();
                r1.next();

                if (r1.getInt(1) == 0) { // If the flight is not full
                    Flights flight = Getter.getFlight(flightid);
                    ArrayList<Flights> flightlist = Getter.getAllFlightsfromAccount(customeremail);

                    int booktime = Integer.parseInt(flight.getDeparturetime().substring(0, 2));
                    String bookday = flight.getDeparturedate();
                    boolean b = true;

                    for (int i = 0; i < flightlist.size(); i++) {
                        int departuretime = Integer.parseInt(flightlist.get(i).getDeparturetime().substring(0, 2));
                        int arrivaltime = Integer.parseInt(flightlist.get(i).getArrivaltime().substring(0, 2));
                        String departureday = flightlist.get(i).getDeparturedate();

                        if (bookday.equals(departureday) && booktime > departuretime && booktime < arrivaltime) {
                            b = false;
                        }
                    }

                    if (b) {
                        Random rand = new Random();
                        int reservationid = rand.nextInt(10000);

                        PreparedStatement statement = con.prepareStatement("Select Count(*) from Reservation_T where ReservationID = ?");
                        statement.setInt(1, reservationid);

                        ResultSet result = statement.executeQuery();
                        result.next();
                        int exist = result.getInt(1);

                        while (exist == 1) {
                            reservationid = rand.nextInt(10000);
                            PreparedStatement statement2 = con.prepareStatement("Select Count(*) from Reservation_T where ReservationID = ?");
                            statement2.setInt(1, reservationid);

                            ResultSet result2 = statement2.executeQuery();
                            result2.next();
                            exist = result2.getInt(1);
                        }

                        try {
                            if (Insert.addBooking(reservationid, customeremail, flightid)) {
                                PreparedStatement update = con.prepareStatement("Update Flight_T set SeatsAvailable = SeatsAvailable - 1 where FlightID = ?");
                                update.setInt(1, flightid);

                                update.executeUpdate();
                            }
                        } catch (Exception ex) {
                            System.out.println("Can not insert booking");
                        }
                    } else {
                        throw new TimeConflict("Time conflict");
                    }

                } else {
                    throw new FullException("Flight is full");
                }
            } else {
                // Polymorphism: Different types of exceptions can be thrown
                throw new ExistingBooking("Booking error! You already have booked this flight.");
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

            PreparedStatement statement2 = con.prepareStatement("Select FlightID from Reservation_T where ReservationID = ?");
            statement2.setInt(1, reservationid);

            ResultSet result = statement2.executeQuery();
            result.next();

            String flightid = result.getString(1);
            System.out.println(flightid);

            PreparedStatement statement = con.prepareStatement("Delete from Reservation_T where ReservationID = ?");
            statement.setInt(1, reservationid);

            statement.executeUpdate();

            PreparedStatement update = con.prepareStatement("Update Flight_T set SeatsAvailable = SeatsAvailable + 1 where FlightID = ?");
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
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(db_url, db_username, db_pw);
            return conn;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
}
