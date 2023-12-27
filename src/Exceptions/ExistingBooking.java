package Exceptions;

// handling existing bookings
//extends the base exception class, making it a checked exception

public class ExistingBooking extends Exception {
 

// constructor takes a message parameter for a descriptive message
 // using 'super(message)' to invoke the base exception class constructor
 public ExistingBooking(String message) {
     super(message); // Calls the constructor in the superclass (Parent) with the provided message
 }
 
 // ready to be used to indicate an existing booking situation
}

