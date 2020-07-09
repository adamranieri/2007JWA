package dev.edwin.TDDPlayground;

public class MyAwesomeCalc implements ScienceCalculator
{

	@Override
	public double fToC(double temp) {
//		459.67 F 
		if(temp < -459.67)
		{
			throw new IllegalTemperatureException(); 
		}
		
		double result = (double)(((temp - 32)* 5)/9);
		return Double.parseDouble( String.format("%.2f", result));
	}

	@Override
	public double cToF(double temp) {
		if(temp < -273)
		{
			throw new IllegalTemperatureException(); 
		}
		
		double result = (double)(((temp/5) * 9) + 32);
		return Double.parseDouble(String.format("%.2f", result));
	}

}
