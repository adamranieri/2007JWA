package dev.ranieri.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntroTests {
	
	// 1. If a test finishes without throwing an error it gets a pass
	// 2. An assert is just a method that throws an assertion error causing the test to fail 
	// 3. All you tests should pass. Positive and Negative tests should pass
	
	// 

	@Test
	void test() {
	// assertions compare expected values vs the actual value
	// if they do not match an AssertionError is thrown
		
		try {
			Assertions.assertEquals(false, true);
		}catch(Error e) {
			System.out.println("I caught an error which is an awful idea");
		}
	 
	}

	@Test
	void test2() {
	
	}
	
}
