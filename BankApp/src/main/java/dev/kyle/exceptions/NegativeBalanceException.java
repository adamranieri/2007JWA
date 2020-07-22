package dev.kyle.exceptions;

public class NegativeBalanceException extends Exception {

	private static final long serialVersionUID = 1L;

	public NegativeBalanceException() {
		super("Balance cannot be below 0!");
	}
}
