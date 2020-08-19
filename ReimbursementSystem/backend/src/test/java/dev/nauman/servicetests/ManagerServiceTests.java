package dev.nauman.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.nauman.entities.Manager;
import dev.nauman.services.ManagerService;
import dev.nauman.services.ManagerServiceImpl;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
class ManagerServiceTests {

	private static ManagerService mserv = ManagerServiceImpl.getManagerServiceImpl();
	
	@Test
	@Order(1)
	void getManagerByIdPositiveTest() {
		Manager manager = mserv.getManagerById(1);
		
	}

}
