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
            e.printStackTrace();    //e is an reference to an exception object , printstacktrace is a throwable method
        }
    }

    public static void insertSampleFlights() throws Exception {  
        try {
            Connection con = Insert.getConnection(); // Reuse the getConnection method from Insert class
            PreparedStatement statement = con.prepareStatement(   //This line creates a PreparedStatement object, which is a precompiled SQL statement that can be executed multiple times. It is used to insert data into a database table.
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
         // ATL to LAX flights in 2024
            insertFlight(statement, "F101", "2024-01-01", "08:30", "2024-01-01", "11:15", "ATL", "LAX", 220);
            insertFlight(statement, "F102", "2024-01-02", "11:45", "2024-01-02", "15:30", "ATL", "LAX", 230);
            insertFlight(statement, "F103", "2024-01-03", "14:15", "2024-01-03", "17:45", "ATL", "LAX", 210);
            // ... (repeat for more dates)

            // ATL to ORD flights in 2024
            insertFlight(statement, "F111", "2024-01-01", "09:00", "2024-01-01", "12:00", "ATL", "ORD", 210);
            insertFlight(statement, "F112", "2024-01-02", "12:30", "2024-01-02", "15:45", "ATL", "ORD", 200);
            insertFlight(statement, "F113", "2024-01-03", "15:00", "2024-01-03", "18:30", "ATL", "ORD", 220);
            // ... (repeat for more dates)

            // ATL to SFO flights in 2024
            insertFlight(statement, "F121", "2024-01-01", "10:15", "2024-01-01", "13:30", "ATL", "SFO", 240);
            insertFlight(statement, "F122", "2024-01-02", "13:45", "2024-01-02", "17:00", "ATL", "SFO", 230);
            insertFlight(statement, "F123", "2024-01-03", "16:30", "2024-01-03", "19:45", "ATL", "SFO", 250);
            // ... (repeat for more dates)

            // ATL to DFW flights in 2024
            insertFlight(statement, "F131", "2024-01-01", "11:30", "2024-01-01", "14:45", "ATL", "DFW", 200);
            insertFlight(statement, "F132", "2024-01-02", "14:00", "2024-01-02", "17:15", "ATL", "DFW", 210);
            insertFlight(statement, "F133", "2024-01-03", "17:45", "2024-01-03", "21:00", "ATL", "DFW", 190);
            // ... (repeat for more dates)

            // ATL to MIA flights in 2024
            insertFlight(statement, "F141", "2024-01-01", "12:15", "2024-01-01", "15:30", "ATL", "MIA", 220);
            insertFlight(statement, "F142", "2024-01-02", "15:45", "2024-01-02", "19:00", "ATL", "MIA", 230);
            insertFlight(statement, "F143", "2024-01-03", "18:30", "2024-01-03", "21:45", "ATL", "MIA", 210);
            // ... (repeat for more dates)

            // ATL to DEN flights in 2024
            insertFlight(statement, "F151", "2024-01-01", "13:00", "2024-01-01", "16:15", "ATL", "DEN", 240);
            insertFlight(statement, "F152", "2024-01-02", "16:30", "2024-01-02", "19:45", "ATL", "DEN", 230);
            insertFlight(statement, "F153", "2024-01-03", "19:00", "2024-01-03", "22:15", "ATL", "DEN", 250);
            // ... (repeat for more dates)

            // ATL to SEA flights in 2024
            insertFlight(statement, "F161", "2024-01-01", "14:45", "2024-01-01", "18:00", "ATL", "SEA", 200);
            insertFlight(statement, "F162", "2024-01-02", "18:15", "2024-01-02", "21:30", "ATL", "SEA", 210);
            insertFlight(statement, "F163", "2024-01-03", "21:45", "2024-01-03", "01:00", "ATL", "SEA", 190);
            // ... (repeat for more dates)

            // ATL to IAH flights in 2024
            insertFlight(statement, "F171", "2024-01-01", "15:30", "2024-01-01", "18:45", "ATL", "IAH", 220);
            insertFlight(statement, "F172", "2024-01-02", "18:00", "2024-01-02", "21:15", "ATL", "IAH", 230);
            insertFlight(statement, "F173", "2024-01-03", "21:30", "2024-01-03", "00:45", "ATL", "IAH", 210);
            // ... (repeat for more dates)


            statement.close();  // is used to close a PreparedStatement object in Java
        } catch (SQLException e) { //is part of a try-catch block in Java and is used for handling exceptions
            e.printStackTrace();  //  is used to print the stack trace of an exception to the standard error stream in Java
            System.out.println("Error inserting sample flights: " + e.getMessage()); 
        }
    }

    private static void insertFlight(PreparedStatement statement, String flightID, String departureDate, String departureTime,
            String arrivalDate, String arrivalTime, String origin, String destination, int capacity) {
        try {
        	// Set values for the prepared statement parameters
            statement.setString(1, flightID);
            statement.setString(2, departureDate);
            statement.setString(3, departureTime);
            statement.setString(4, arrivalDate);
            statement.setString(5, arrivalTime);
            statement.setString(6, origin);
            statement.setString(7, destination);
            statement.setInt(8, capacity);
            statement.setInt(9, capacity); // Initially, all seats are available
            
            // Execute the update to insert the flight into the database
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQLException
            e.printStackTrace();  // Print the stack trace
            System.out.println("Error inserting flight: " + e.getMessage());
        }
    }
}
