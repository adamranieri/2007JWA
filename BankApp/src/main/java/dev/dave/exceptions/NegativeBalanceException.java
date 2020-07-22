package dev.dave.exceptions;

public class NegativeBalanceException extends Exception {
	
	public NegativeBalanceException() {
		super("Insufficient funds / Balance cannot be less than zero.");
	}

}
