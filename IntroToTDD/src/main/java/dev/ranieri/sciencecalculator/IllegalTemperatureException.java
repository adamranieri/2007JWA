package dev.ranieri.sciencecalculator;

public class IllegalTemperatureException extends RuntimeException {

	IllegalTemperatureException() {
		super("The temperature you put in is below absolute 0");
	}

}
