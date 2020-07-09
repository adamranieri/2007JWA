package dev.edwin.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntroTests {

	@Test
	void test() {
//		Compares EXPECTED values vs. ACTUAL value. 
//		IF they do NOT match you will get an assertion error known.
		Assertions.assertEquals(true, true);
	}
	
	@Test
	void test2()
	{
		System.out.println("I pass!");
	}

}
