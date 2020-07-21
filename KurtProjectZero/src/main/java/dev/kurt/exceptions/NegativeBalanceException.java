package dev.kurt.exceptions;

public class NegativeBalanceException extends Exception{
	
	public NegativeBalanceException() {
		super("Balance cannot be below Zero");
	}
}
