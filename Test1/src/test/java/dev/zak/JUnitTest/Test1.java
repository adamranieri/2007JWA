package dev.zak.JUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Test1 {

	// if no errors are thrown the test passes
	// asset is a method throwing an assertion error causing the test to fail
	// All tests should pass
	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	void test2() {
		System.out.println("I pass !!!");
		// 
		//Assertions.assertArrayEquals(expected, actual);
		//if the expected is different from the actual AssertionError is thrown
		//Try catch assertions is not recommended but it can be done 
	}
}
