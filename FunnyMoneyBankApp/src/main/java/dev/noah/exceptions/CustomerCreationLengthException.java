package dev.noah.exceptions;

public class CustomerCreationLengthException extends Exception{

	public CustomerCreationLengthException() {
		super("The username or password is less than 4 characters");
	}
}
