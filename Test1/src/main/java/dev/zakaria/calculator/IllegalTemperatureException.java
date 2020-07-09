package dev.zakaria.calculator;

public class IllegalTemperatureException extends RuntimeException{

	public IllegalTemperatureException() {
		// TODO Auto-generated constructor stub
		super("the temp you entered is way below absolute 0");
	}

}
