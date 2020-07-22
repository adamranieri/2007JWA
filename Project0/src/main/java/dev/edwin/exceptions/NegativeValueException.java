package dev.edwin.exceptions;

public class NegativeValueException extends Exception{

	public NegativeValueException() {
		super("** EXCEPTION: Do not input negative values. **");
	}
}
