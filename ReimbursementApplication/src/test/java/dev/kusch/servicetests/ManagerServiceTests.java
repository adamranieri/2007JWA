package dev.kusch.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.kusch.entities.Manager;
import dev.kusch.services.ManagerService;
import dev.kusch.services.ManagerServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
class ManagerServiceTests {

private static ManagerService mserv = new ManagerServiceImpl();
	
	@Test
	@Order(1)
	void getManagerById() {
		Manager manager = mserv.getManagerById(1);
		Assertions.assertEquals("Waternoose", manager.getLastName());
	}
	
	@Test
	@Order(2)
	void getManagerByIdNegative() {
		Manager manager = mserv.getManagerById(30000);
		Assertions.assertNull(manager);
	}
	
	@Test
	@Order(3)
	void getManagerByUser() {
		List<Manager> manager = mserv.getManagerByUser("Waternoose");
		Assertions.assertEquals(1, manager.size());
		Assertions.assertEquals("Waternoose", manager.get(0).getLastName());
	}
	
	@Test
	@Order(4)
	void getManagerByNegativeUser() {
		List<Manager> manager = mserv.getManagerByUser("FakeUser");
		Assertions.assertEquals(0, manager.size());
	}
	
	@Test
	@Order(5)
	void updatePassword() {
		Manager manager = mserv.getManagerById(1);
		manager.setPassword("NewPass");
		manager = mserv.updateManager(manager);
		Assertions.assertEquals("NewPass", mserv.getManagerById(1).getPassword());
		
		manager.setPassword("TotallyNotEvilAtAll");
		manager = mserv.updateManager(manager);
	}
	
	@Test
	@Order(6)
	void updateBadPassword() {
		Manager manager = new Manager(10, "Boo", "hugs", "Boo", "Boo");
		Manager man = mserv.updateManager(manager);
		Assertions.assertNull(man);
	}
	
	@Test
	@Order(7)
	void testLoginSuccess() {
		boolean result = mserv.loginManager("Waternoose", "TotallyNotEvilAtAll");
		Assertions.assertTrue(result);
	}
	
	@Test
	@Order(8)
	void testLoginBadPass() {
		boolean result = mserv.loginManager("Waternoose", "FakePassword");
		Assertions.assertFalse(result);
	}
}
