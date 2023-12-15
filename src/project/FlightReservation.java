package project;

public class FlightReservation {

	private int reservationId;
    private int flightId;
    private String customerEmail;

    // Default constructor
    public FlightReservation() {

    }

    //  constructor w parameters to initialize reservation details
    public FlightReservation(int reservationId, String customerEmail, int flightId) {
        // Assigning values passed as parameters to the respective class variables
        this.reservationId = reservationId;
        this.flightId = flightId;
        this.customerEmail = customerEmail;
    }

    // getter method to retrieve reservation ID
    public int getReservationId() {
        return reservationId;
    }

    // setter method to update reservation ID
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    // getter method to retrieve flight ID
    public int getFlightId() {
        return flightId;
    }

    // setter method for updating flight ID
    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    // getter method to retrieve customer email
    public String getCustomerEmail() {
        return customerEmail;
    }

    // Setter method for updating customer email
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

/*
 * (toString() is intended to override the method in the superclass Object class.
 * helps catch errors at compile time if the method does not actually override a method in the superclass
 */
    @Override
    public String toString() {
        // Creating a string representation of the reservation details
        return "Reservation ID #" + reservationId + " for Passenger with email: " + customerEmail + ", and Flight ID: " + flightId;
    }
}



