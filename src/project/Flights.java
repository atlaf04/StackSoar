package project;

public class Flights{
	private int flightid;
	private String departuredate;
	private String departuretime;
	private String arrivaldate;
	private String arrivaltime;
	private String originalairport;
	private int capacity;
	private String destinationairport;
	private int seatsavailable;
	
	public Flights() {
		
	}
	public Flights (int flightId, String departuredate, String departuretime, String arrivaldate, String arrivalime, String originalairport , String destinationairport,int seatsavailable ) {
		this.flightid = flightid;
		this.departuredate = departuredate;
		this.departuretime = departuretime;
		this.arrivaldate = arrivaldate;
		this.arrivaltime = arrivaltime;
		this.originalairport = originalairport;
		this.destinationairport = destinationairport;
		this.capacity = capacity;
		this.seatsavailable = seatsavailable;
		
	}
	
	public int getFlightid() {
		return flightid;
	
	}
	public void setFlightid(int flightid) {
		this.flightid = flightid;
	}
	
	public String getDeparturedate() {
		return departuredate;
	}
	public void setDeparturedate(String departuredate) {
		this.departuredate = departuredate;
	}
	
	
	public String getDeparturetime() {
		return departuretime;
	}
	public void setDeparturetime (String departuretime) {
		this.departuretime = departuretime;
	}
	
	
	public String getArrivaldate() {
		return arrivaldate;
	}
	public void setArrivaldate(String arrivaldate) {
		this.arrivaldate = arrivaldate;
	}
	
	
	public String getArrivaltime() {
		return arrivaltime;
	}
	public void setArrivaltime(String arrivaltime) {
		this.arrivaltime = arrivaltime;
	}
	
	
	public String getOriginalairport() {
		return originalairport;
	}
	public void setOriginalairport(String originalairport) {
		this.originalairport = originalairport;
	}
	
	
	public String getDestinationairport() {
		return destinationairport;
	}
	public void setDestinationairport(String destinationairport) {
		this.destinationairport = destinationairport;	
	}
	
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity( int capacity) {
		this.capacity = capacity;
	}
	
	
	public int getSeatsavailable() {
		return seatsavailable;
	}

	public void setSeatsavailable(int seatsavailable) {
		this.seatsavailable = seatsavailable;
	}
	
	@Override 
	public String toString() {
		return "Flight with id " + flightid + "\nDeparture date: " + departuredate + ", Time: "  + departuretime + "\nArrival date: " +arrivaldate + ",Time: " + arrivaltime + "\nOrigin: " + originalairport + "\nDestination: " + destinationairport + "\nCapacity: " + capacity + "\nSeatsAvailable: " + seatsavailable;
	}
	

}
	
	
