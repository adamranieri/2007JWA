package dev.ranieri.exceptions;

public class NegativeCapcityException extends Exception {

	public NegativeCapcityException(){
		super("School capcity cannot be less than 0");
	}
}
