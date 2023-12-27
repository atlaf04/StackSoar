package project;

public class Customer {

	private String email;
	private String password; 
	private String firstname;
	private String lastname;
	private String address; 
	private String state; 
	private int zip;
	private int ssn; 
	private String securityanswer;
	public static final boolean isAdmin = false;
	
	public Customer () {
		
	}
	
	public Customer(String email, String password, String firstname, String lastname, String address, String state, int zip, int ssn,  String securityanswer ) {
		this.email = email; // this.email refers to the instance variable email of the current object, and email (without this) refers to the parameter passed to the constructor. 
		this.password = password; 
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address; 
		this.state = state; 
		this.zip = zip;
		this.ssn = ssn;
		this.securityanswer = securityanswer;
	}
	
	public Customer (String email, String password) {
		this.email = email;
		this.password = password;
		
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}


	public String getSecurityanswer() {
		return securityanswer;
	}

	public void setSecurityanswer(String securityanswer) {
		this.securityanswer = securityanswer;
	}

	
	@Override
	public boolean equals(Object passenger1) { // this block is important to see if there are duplicates
	    boolean b = false; // variable b is  used as a flag to indicate whether the two instances being compared are considered equal or not.
	    try {
	        // Attempt to cast the passed object to a Customer
	        Customer passenger = (Customer) passenger1;

	        // Compare Social Security Numbers
	        if (this.getSsn() == passenger.getSsn()) {
	            b = true; // Set the flag to true if SSNs are equal
	        } else {
	            b = false; // Set the flag to false if SSNs are not equal
	        }
	    } catch (ClassCastException ex) {
	        System.out.println("Not valid for Passenger class");
	    } catch (NullPointerException e) {
	        // Handle a case where the passed object is null
	    }

	    return b;
	}
	
	@Override
	public String toString() {
		return "Passenger " + firstname + " " + lastname + " with email: " + email + "\nAddress: " + address + " "+ state + " "+ zip + "\nSSN: "+ ssn + "\nSecurity Answer: " + securityanswer; // converts all to string
	} // \n means a newline
	
	


}
