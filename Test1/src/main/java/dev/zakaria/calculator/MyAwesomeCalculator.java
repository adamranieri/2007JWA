package dev.zakaria.calculator;

public class MyAwesomeCalculator implements ScienceCalculator{

	public MyAwesomeCalculator() {
		// TODO Auto-generated constructor stub
	}
	
	public double fToC(double temperature) {
		//(xF − 32) × 5/9 = resultC
		double res = (temperature-32) * (5/9);
		return res;
	}
	public double cToF(double temperature) {
		// (xC × 9/5) + 32 = resultF
		double res = (temperature * (9/5)) + 32;
		return res;
	}

}
