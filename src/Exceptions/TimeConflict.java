package Exceptions;


//Custom exception class for signaling a time conflict
public class TimeConflict extends Exception {

 // Constructor for TimeConflict exception
 public TimeConflict(String s) {
     super(s);  // Invoke the constructor of the superclass (Exception) with the provided message
 }
}
