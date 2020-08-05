package dev.kurt.exceptions;

public class InvalidLoginException extends Exception{
	
	public InvalidLoginException() {
		super("We don't recognize your login credentials. Please try again");
	}
	
}
