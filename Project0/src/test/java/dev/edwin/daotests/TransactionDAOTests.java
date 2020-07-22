package dev.edwin.daotests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


@TestMethodOrder(OrderAnnotation.class)
class TransactionDAOTests {

	@Test
	@Order(1)
	void test() {
		fail("Not yet implemented");
	}

}
