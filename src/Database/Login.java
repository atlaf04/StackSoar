package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Login {
	static final String db_url = "jdbc:mysql://127.0.0.1:3306/StackSoar";
	static final String db_username = "root";
	static final String db_pw = "August13";

	
	public static boolean LogIn(String email, String password) throws Exception {
		boolean b = false;
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("Select COUNT(*) from Customer_Table where CustomerEmail ='" + email + "' and CustomerPassword ='" + password + "'");
			ResultSet exist = statement.executeQuery();
			exist.next(); 
			if (exist.getInt(1) == 1) {
				statement.close();
				System.out.println("You are logged in as Customer, go to the next scene");
				b = true;
			}
			else {
				System.out.println("Incorrect username/password. Please try again");
				b = false;
			}
			
		} catch (Exception ex) {
			System.out.println(ex);
			
		}
		return b;
		
	}
	
	public static boolean RetrievePassword(String email, String answerfromuser) {
		boolean b = false;
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("Select COUNT(*) from Customer_Table where CustomerEmail = '" + email + "'");
			ResultSet exist = statement.executeQuery();
			exist.next();
			if (exist.getInt(1) == 1) { // checking if email is in the database system
				statement.close();
				PreparedStatement statement2 = con.prepareStatement("Select * from Customer_Table where CustomerEmail = '" + email + "'");
				ResultSet info = statement2.executeQuery();
				info.next();
				String password = info.getString("CustomerPassword");
				String answer = info.getString("CustomerSecurityAnswer");
				
				
				info.close();
				
				if (answer.equalsIgnoreCase(answerfromuser)) {
					b = true;
					System.out.println("Your password is " + password);
				}
				else {
					b = false;
					System.out.println("Incorrect answer");
				}
				
			}
			else {
				System.out.println("Account does not exist in system");
				b = false;
			}
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
		finally {
			
		}
		return b;
		
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