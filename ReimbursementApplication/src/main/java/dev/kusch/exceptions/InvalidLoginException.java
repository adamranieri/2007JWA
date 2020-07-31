package dev.kusch.exceptions;

public class InvalidLoginException extends Exception {

	public InvalidLoginException() {
		super("The username or password is incorrect");
	}

}
