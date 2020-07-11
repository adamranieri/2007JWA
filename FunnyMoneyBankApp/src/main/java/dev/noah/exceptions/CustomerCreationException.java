package dev.noah.exceptions;

public class CustomerCreationException extends Exception{

	public CustomerCreationException() {
		super("The username or password is less than 4 characters");
	}
}
