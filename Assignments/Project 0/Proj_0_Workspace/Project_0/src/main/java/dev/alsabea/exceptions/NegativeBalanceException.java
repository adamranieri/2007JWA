package dev.alsabea.exceptions;

public class NegativeBalanceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NegativeBalanceException() {
		super("Balance cannot be negative!");
	}

}
