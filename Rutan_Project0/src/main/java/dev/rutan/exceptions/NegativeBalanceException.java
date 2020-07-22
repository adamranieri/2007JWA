package dev.rutan.exceptions;

public class NegativeBalanceException extends Exception {

	public NegativeBalanceException() {
		super("The current request would cause a negative balance.");
	}
	
}
