package dev.ranieri.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
class Testing {

	@Test
	@Order(1)
	void test() {
		System.out.println(1);
		Assertions.assertTrue(false);
	}
	
	@Test
	@Order(2)
	void test2() {
		System.out.println(2);
		Assertions.assertTrue(true);
	}


}
