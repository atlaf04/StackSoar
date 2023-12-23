package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class StackSoarDatabase {
    // Main method
    public static void main(String[] args) {
        try {
            // Call the 'get' method to retrieve data
            get();
        } catch (Exception e) {
            System.out.println("Select failed");
        }
    }

    // Method to create the Customer_T table
    public static void createCustomerTable() throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS Customer_T(" +
                    "CustomerEmail VARCHAR(100) NOT NULL," +
                    "CustomerPassword VARCHAR(30)," +
                    "CustomerFName VARCHAR(25)," +
                    "CustomerLName VARCHAR(25)," +
                    "CustomerAddress VARCHAR(30)," +
                    "CustomerState CHAR(2)," +
                    "CustomerZip VARCHAR(20)," +
                    "CustomerSSN VARCHAR(9)," +
                    "CustomerSecurityAnswer VARCHAR(100)," +
                    "PRIMARY KEY(CustomerEmail));");
            create.executeUpdate();
            create.close();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            System.out.println("Function createCustomerTable completed");
            getConnection().close();
        }
    }

    // Method to create the Flight_T table
    public static void createFlightTable() throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS Flight_T(" +
                    "FlightID VARCHAR(6) NOT NULL," +
                    "DepartureDate DATE," +
                    "DepartureTime TIME," +
                    "ArrivalDate DATE," +
                    "ArrivalTime TIME," +
                    "Origin VARCHAR(35)," +
                    "Destination VARCHAR(35)," +
                    "Capacity INT," +
                    "PRIMARY KEY(FlightId));");
            create.executeUpdate();
            create.close();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            System.out.println("Function createFlightTable completed");
            getConnection().close();
        }
    }

    // Method to insert values into the Customer_T table
    public static void post() throws Exception {
        final String first_test = "John";
        final String last_test = "Miller";
        final String email_test = "johnmiller@gmail.com";
        final String username_test = "johnmiller";
        final String password_test = "password";
        final String address_test = "123 Street";
        final String state_test = "GA";
        final String zip_test = "12345";
        final String ssn_test = "123456789";
        final String question_test = "Hi";
        final String answer_test = "Bye";

        try {
            Connection con = getConnection();
            PreparedStatement posted = con.prepareStatement("INSERT INTO Customer_T VALUES " +
                    "('" + email_test + "','" + username_test + "','" + password_test + "','" + first_test + "','" +
                    last_test + "','" + address_test + "','" + state_test + "','" + zip_test + "','" + ssn_test + "','" +
                    question_test + "','" + answer_test + "')");
            posted.executeUpdate();
            posted.close();

        } catch (Exception ex) {
            System.out.println("Insert unsuccessfully");
        } finally {
            System.out.println("Insert completed");
            getConnection().close();
        }
    }

    // Method to retrieve data from the Customer_T table
    public static ArrayList<String> get() throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Customer_T");
            ResultSet result = statement.executeQuery();
            ArrayList<String> list = new ArrayList<>();
            while (result.next()) {
                // Output each field for the selected record
                System.out.println(result.getString("CustomerEmail"));
                System.out.println(result.getString("CustomerUsername"));
                System.out.println(result.getString("CustomerPassword"));
                System.out.println(result.getString("CustomerFName"));
                System.out.println(result.getString("CustomerLName"));
                System.out.println(result.getString("CustomerAddress"));
                System.out.println(result.getString("CustomerState"));
                System.out.println(result.getString("CustomerZip"));
                System.out.println(result.getString("CustomerSSN"));
                System.out.println(result.getString("CustomerSecurityQuestion"));
                System.out.println(result.getString("CustomerSecurityAnswer"));

                // Add each field to the list
                list.add(result.getString("CustomerEmail"));
                list.add(result.getString("CustomerUsername"));
                list.add(result.getString("CustomerPassword"));
                list.add(result.getString("CustomerFName"));
                list.add(result.getString("CustomerLName"));
                list.add(result.getString("CustomerAddress"));
                list.add(result.getString("CustomerState"));
                list.add(result.getString("CustomerZip"));
                list.add(result.getString("CustomerSSN"));
                list.add(result.getString("CustomerSecurityQuestion"));
                list.add(result.getString("CustomerSecurityAnswer"));
            }
            System.out.println("ALL records have been selected");
            statement.close();
            result.close();
            return list;

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            getConnection().close();
        }
        return null;
    }

    // Method to establish a database connection
    public static Connection getConnection() throws Exception {
        Scanner input = new Scanner(System.in);
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/?user=root"; // Enter YOUR url for localhost
            String username = "root";
            String password = "August13";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
            return conn;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
}
