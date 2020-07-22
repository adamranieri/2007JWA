package dev.nauman.exceptions;

public class NegativeBalanceException extends Exception {

	public NegativeBalanceException() {
		super("The balance cannot be less than 0.");
	}
}