package Exceptions;

//Custom exception class for signaling a full condition
public class FullException extends Exception {

 // Constructor for FullException
 public FullException(String s) { // The constructor takes a String parameter s, which represents a message describing the exception super(s); invokes the constructor of the superclass (Exception), passing the provided message to handle the initialization of the exception message.
     super(s);  // Invoke the constructor of the superclass (Exception) with the provided message
 }
}
