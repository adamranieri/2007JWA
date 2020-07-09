package dev.zak.calculatortests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.zakaria.calculator.Calculator;
import dev.zakaria.calculator.CalculatorInterface;
import dev.zakaria.calculator.IlligalTempException;

class CalculatorTests {

//	public static CalculatorInterface c = null;
	public static CalculatorInterface c = new Calculator();
	//Positive tests
		@Test // makes JUnit know this is a test to be run
		void fToCTest1() {
			double res = c.fToC(100.7); // should be 38.17
			Assertions.assertEquals(38.17, res);
			//fail("Not yet implemented");
		}
		
		@Test // makes JUnit know this is a test to be run
		void fToCTest2() {
			double res = c.fToC(-100.7); // should be -73.72
			Assertions.assertEquals(-73.72, res);
			//fail("Not yet implemented");
		}
		
		@Test // makes JUnit know this is a test to be run
		void CToFTest1() {
			double res = c.fToC(38.17); // should be 100.7
			Assertions.assertEquals(100.7, res);
			//fail("Not yet implemented");
		}
		
		@Test // makes JUnit know this is a test to be run
		void CToFTest2() {
			double res = c.fToC(-73.72); // should be -100.7
			Assertions.assertEquals(-100.7, res);
			//fail("Not yet implemented");
		}
		
		
	// Negative Test
		void fNumberBellowZero() {
			//-459.67
			
			Exception e = assertThrows(IlligalTempException.class, ()->{
				c.fToC(-500);
			});
			
			Assertions.assertEquals(e.getMessage(), "the temp you entered is way below absolute 0");
		}
		
		void CNumberBellowZero() {
			try {
				c.cToF(-800);
				fail();
			}
			catch (IlligalTempException e) {
				// TODO: handle exception
				e.getMessage();
			}
			
			Exception e = assertThrows(IlligalTempException.class, ()->{
				c.fToC(-800);
			});
			
			Assertions.assertEquals(e.getMessage(), "the temp you entered is way below absolute 0");
		}

}
