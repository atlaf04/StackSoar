package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertFlightData {

    public static void main(String[] args) {
        try {
            // Call the method to insert sample flights
            insertSampleFlights();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertSampleFlights() throws Exception {
        try {
            Connection con = Insert.getConnection(); // Reuse the getConnection method from Insert class
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO Flight_Table (FlightID, DepartureDate, DepartureTime, ArrivalDate, ArrivalTime, Origin, Destination, Capacity, SeatsAvailable) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            // Sample flights from different major U.S. cities
            insertFlight(statement, "F001", "2023-02-01", "08:00", "2023-02-01", "11:00", "JFK", "LAX", 200);
            insertFlight(statement, "F002", "2023-02-02", "10:30", "2023-02-02", "15:00", "LAX", "ORD", 180);
            insertFlight(statement, "F003", "2023-02-03", "13:15", "2023-02-03", "17:45", "ORD", "ATL", 220);
            insertFlight(statement, "F004", "2023-02-04", "09:45", "2023-02-04", "12:30", "ATL", "SFO", 190);
            insertFlight(statement, "F005", "2023-02-05", "11:00", "2023-02-05", "15:30", "SFO", "DFW", 210);
            insertFlight(statement, "F006", "2023-02-06", "14:20", "2023-02-06", "18:45", "DFW", "MIA", 170);
            insertFlight(statement, "F007", "2023-02-07", "12:30", "2023-02-07", "15:15", "MIA", "DEN", 200);
            insertFlight(statement, "F008", "2023-02-08", "10:00", "2023-02-08", "13:45", "DEN", "SEA", 180);
            insertFlight(statement, "F009", "2023-02-09", "13:45", "2023-02-09", "17:15", "SEA", "IAH", 190);
            insertFlight(statement, "F010", "2023-02-10", "15:30", "2023-02-10", "18:15", "IAH", "JFK", 210);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();  // Print the stack trace
            System.out.println("Error inserting sample flights: " + e.getMessage());
        }
    }

    private static void insertFlight(PreparedStatement statement, String flightID, String departureDate, String departureTime,
            String arrivalDate, String arrivalTime, String origin, String destination, int capacity) {
        try {
            statement.setString(1, flightID);
            statement.setString(2, departureDate);
            statement.setString(3, departureTime);
            statement.setString(4, arrivalDate);
            statement.setString(5, arrivalTime);
            statement.setString(6, origin);
            statement.setString(7, destination);
            statement.setInt(8, capacity);
            statement.setInt(9, capacity); // Initially, all seats are available

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  // Print the stack trace
            System.out.println("Error inserting flight: " + e.getMessage());
        }
    }
}