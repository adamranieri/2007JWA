package dev.schneider.exceptions;

public class NegativeBalanceException extends Exception {
	public NegativeBalanceException() {
		super("Balance cannot be negative");
	}
}
