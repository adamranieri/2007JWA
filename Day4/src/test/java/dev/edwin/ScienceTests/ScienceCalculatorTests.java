package dev.edwin.ScienceTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.edwin.TDDPlayground.IllegalTemperatureException;
import dev.edwin.TDDPlayground.MyAwesomeCalc;
import dev.edwin.TDDPlayground.ScienceCalculator;

class ScienceCalculatorTests {
	
	public static ScienceCalculator calc = new MyAwesomeCalc();
	
//	POSITIVE TESTs

	@Test
	void fToCTest1() {
		double result = calc.fToC(100.7);
		
		Assertions.assertEquals(38.17, result);
	}
	
	
	@Test
	void fToCTest2() {
		double result = calc.fToC(-90);
		
		Assertions.assertEquals(-67.78, result);
	}

	
	@Test
	void cToFTest1() {
		double result = calc.cToF(100);
		
		Assertions.assertEquals(212, result);
	}
	
	
	@Test
	void cToFTest2() {
		double result = calc.cToF(54.5);
		
		Assertions.assertEquals(130.1, result);
	}
	
	
	
//	NEGATIVE TESTS
	@Test
	void fnumberAbsoluteZero()
	{
//		459.67 F 
		
//		The cool LAMBDA way
		Exception e = assertThrows(IllegalTemperatureException.class, () -> {
			calc.fToC(-500);
		});
		
		Assertions.assertEquals(e.getMessage(), "Warning: The Temperature you entered is below absolute 0");
	}
	
	
	@Test
	void cnumberAbsoluteZero()
	{
//		273 C 
		
		Exception e = assertThrows(IllegalTemperatureException.class, () -> {
			calc.cToF(-745);
		});
		
		Assertions.assertEquals(e.getMessage(), "Warning: The Temperature you entered is below absolute 0");
	}
	
}
