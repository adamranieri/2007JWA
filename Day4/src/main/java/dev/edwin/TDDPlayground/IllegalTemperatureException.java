package dev.edwin.TDDPlayground;

public class IllegalTemperatureException extends RuntimeException{

	IllegalTemperatureException()
	{
		super("Warning: The Temperature you entered is below absolute 0");
	}
}
