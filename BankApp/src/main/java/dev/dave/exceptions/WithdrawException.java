package dev.dave.exceptions;

public class WithdrawException extends Exception {

	public WithdrawException() {
		super("Deposits cannot set balance to less than current amount.");
	}
}
