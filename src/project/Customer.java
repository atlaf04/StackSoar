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
		this.email = email;
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
	public boolean equals(Object passenger1) {
		boolean b = false;
		try {
			Customer passenger = (Customer)passenger1;
			if (this.getSsn()== passenger.getSsn()) {
				b= true;
			}
			else {
				b = false;
			}
		}
		catch (ClassCastException ex) {
			System.out.println("Not valid for Passenger class");
		}
		catch (NullPointerException e) {
			
		}
		
		return b;
	}
	
	@Override
	public String toString() {
		return "Passenger " + firstname + " " + lastname + " with email: " + email + "\nAddress: " + address + " "+ state + " "+ zip + "\nSSN: "+ ssn + "\nSecurity Answer: " + securityanswer;
	}
	
	


}
