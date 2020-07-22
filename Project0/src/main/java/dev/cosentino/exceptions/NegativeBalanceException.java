package dev.cosentino.exceptions;

@SuppressWarnings("serial")
public class NegativeBalanceException extends Exception{

	public NegativeBalanceException() {
		super("Negative balance not allowed");
	}
}
