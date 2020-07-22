package dev.lee.exceptions;

public class NegativeBalanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NegativeBalanceException() {
		super("Account balance cannot be negative.");
	}
}
