package dev.edwin.exceptions;

public class NegativeBalanceException extends Exception {

	public NegativeBalanceException() {
		super("** EXCEPTION: The resulting action will cause an account to be negative. **");
	}

}
