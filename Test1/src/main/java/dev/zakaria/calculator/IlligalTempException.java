package dev.zakaria.calculator;

public class IlligalTempException extends RuntimeException{

	public IlligalTempException() {
		// TODO Auto-generated constructor stub
		super("the temp you entered is way below absolute 0");
	}

}
