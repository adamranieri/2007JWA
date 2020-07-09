package dev.ranieri.sciencetests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.ranieri.sciencecalculator.IllegalTemperatureException;
import dev.ranieri.sciencecalculator.ScienceCalculator;

class ScienceCalculatorTests {
	
	public static ScienceCalculator calc = null;

	// Positive tests
	
	@Test
	void fToCTest1() {
		double result = calc.farenheitToCelcius(100.7);
		Assertions.assertEquals(38.17, result);
	}
	
	@Test
	void fToCTest2() {
		double result = calc.farenheitToCelcius(-90);
		Assertions.assertEquals(-67.78, result);
	}
	
	
	@Test
	void cToFTest1() {
		double result = calc.celciusToFarenheit(100);
		Assertions.assertEquals(212, result);
	}
	
	@Test
	void cToFTest2() {
		double result = calc.celciusToFarenheit(54.5);
		Assertions.assertEquals(130.1, result);
	}
	
	
	// Negative tests
	@Test
	void farenheitNumberBelowAbsoluteZero() {
		//-459.67
		
		// the cool lambda way that JUnit recommends
		Exception e = assertThrows(IllegalTemperatureException.class, ()->{		
			calc.farenheitToCelcius(-500);
		});
		
		Assertions.assertEquals(e.getMessage(), "The temperature you put in is below absolute 0");		
	}
	
	@Test
	void celciusNumberBelowAbsoluteZero() {
		
		// clumsier way to test for an exception
		try {
			calc.celciusToFarenheit(-800);
			fail();
		}catch(IllegalTemperatureException e) {
			
		}
	}
	
	

}
