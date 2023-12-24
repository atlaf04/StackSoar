package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Exceptions.ExistingBooking;
import Exceptions.FullException;
import Exceptions.TimeConflict;
import project.Flights;
import project.Customer;

public class Test {
    public static void main(String[] args) {
        try {
            // Create a customer object
            Customer customer = new Customer("John", "Doe", "john@example.com", "password", "123 Main St", "CA", 12345, 123456789, "Answer");

            // Insert the customer into the database
            boolean customerInserted = Insert.insertCustomer(customer);
            System.out.println("Customer insertion status: " + customerInserted);

            // Create a flight object
            Flights flight = new Flights(1, "2023-01-01", "12:00", "2023-01-01", "15:00", "Airport A", "Airport B", 100);

            // Insert the flight into the database
            boolean flightInserted = Insert.addFlight(flight);
            System.out.println("Flight insertion status: " + flightInserted);

            // Insert a booking
            boolean bookingInserted = Insert.addBooking(1, "john@example.com", 1);
            System.out.println("Booking insertion status: " + bookingInserted);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        	
        }
    }
