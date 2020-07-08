package dev.ranieri.exceptions;

// custom exception
public class InvalidLengthException extends Exception {
	
	public InvalidLengthException() {
		super("Username or password was less than 7 characters");
	}
	
	public InvalidLengthException(String message) {
		super(message);
	}

}
