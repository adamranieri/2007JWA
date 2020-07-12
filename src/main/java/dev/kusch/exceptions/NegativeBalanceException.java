package dev.kusch.exceptions;

public class NegativeBalanceException extends Exception{
	
	public NegativeBalanceException() {
		super("Transaction causes balance to become negative.");
	}
}
