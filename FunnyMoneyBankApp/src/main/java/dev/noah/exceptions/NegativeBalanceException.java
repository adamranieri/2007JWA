package dev.noah.exceptions;

public class NegativeBalanceException extends Exception {

	public NegativeBalanceException() {
		super("You cannot have a negative balance");
	}
}
