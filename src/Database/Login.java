package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Database connection details
public class Login {
	static final String db_url = "jdbc:mysql://127.0.0.1:3306/StackSoar";
	static final String db_username = "root";
	static final String db_pw = "August13";

    // Method for user login
	public static boolean LogIn(String email, String password) throws Exception {
		boolean b = false;
		try {
            // Establish a database connection
			Connection con = getConnection();
            // Prepare a SQL statement to check login credentials
			PreparedStatement statement = con.prepareStatement("Select COUNT(*) from Customer_Table where CustomerEmail ='" + email + "' and CustomerPassword ='" + password + "'");
            // Execute the query
			ResultSet exist = statement.executeQuery();  //This method is used to execute a SQL SELECT query and returns a ResultSet object containing the result of the query.
			exist.next(); //exist - This is the ResultSet object obtained from executing the query, next - This method is used to move the cursor of the ResultSet to the next row.
         // ^This line is typically used to advance the cursor to the first row of the result set.
			
			// Check if there is exactly one matching user
			if (exist.getInt(1) == 1) {
				statement.close();
				System.out.println("You are logged in as Customer, go to the next scene");
				b = true;
			// ^ for this statement we are looking to see if the customer already exist or not meaning if they are in the database or not  
			}
			else {
				System.out.println("Incorrect username/password. Please try again");
				b = false;
			
			}
			
		} catch (Exception ex) {
            // Handle exceptions by printing the exception details
			System.out.println(ex);
			
		}
		return b;
		
	}
    // Method to retrieve a password based on email and security answer
	public static boolean RetrievePassword(String email, String answerfromuser) {
		boolean b = false;
		try {
            // Establish a database connection
			Connection con = getConnection();
            // Check if the email exists in the database
			PreparedStatement statement = con.prepareStatement("Select COUNT(*) from Customer_Table where CustomerEmail = '" + email + "'");
			ResultSet exist = statement.executeQuery();
			exist.next();
            // If the email exists
			if (exist.getInt(1) == 1) { // checking if email is in the database system
				statement.close();
                // Retrieve user information
				PreparedStatement statement2 = con.prepareStatement("Select * from Customer_Table where CustomerEmail = '" + email + "'");
				ResultSet info = statement2.executeQuery();
				info.next();
                // Get the password and security answer from the database
				String password = info.getString("CustomerPassword");
				String answer = info.getString("CustomerSecurityAnswer");
				
				
				info.close();   // statement closing 
                // Check if the provided answer matches the stored answer
				if (answer.equalsIgnoreCase(answerfromuser)) {
					b = true;
					System.out.println("Your password is " + password);
				}
				else {
					b = false;
					System.out.println("Incorrect answer");
				}
				// this if statement is figuring out if the password the customer inserts is either correct or not 
			}
			else {
				System.out.println("Account does not exist in system");
				b = false;
			}
		}
		catch (Exception ex) {
            // Handle exceptions by printing the exception details
			System.out.println(ex);
		}
		finally {  // Code in this block will be executed whether an exception occurs or not

			
		}
		return b;    // Return the result
		
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