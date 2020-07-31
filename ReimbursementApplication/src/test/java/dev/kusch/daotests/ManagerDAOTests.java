package dev.kusch.daotests;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.kusch.daos.ManagerDAO;
import dev.kusch.daos.ManagerDAOHibernate;
import dev.kusch.entities.Manager;

@TestMethodOrder(OrderAnnotation.class)
public class ManagerDAOTests {
	
	private static ManagerDAO mdao= ManagerDAOHibernate.getManagerDAOHibernate();


	@Test
	@Order(1)
	void getManagerById() {
		Manager manager = mdao.getManagerById(2);
		Assertions.assertEquals("Roz", manager.getUsername());
	}
	
	@Test
	@Order(2)
	void getManagerByBadId() {
		Manager manager = mdao.getManagerById(500);
		Assertions.assertNull(manager);
	}
	
	@Test
	@Order(3)
	void getManagerByUser() {
		List<Manager> manager = mdao.getManagerByUser("Waternoose");
		Assertions.assertEquals(1, manager.get(0).getMid());
	}
	
	@Test
	@Order(4)
	void getManagerByBadUser() {
		List<Manager> manager = mdao.getManagerByUser("BADUSER");
		Assertions.assertEquals(0, manager.size());
	}
	
	@Test
	@Order(5)
	void getManagerByPass() {
		List<Manager> manager = mdao.getManagerByPass("TotallyNotEvilAtAll");
		System.out.println(manager);
		Assertions.assertEquals(1, manager.size());
		Assertions.assertEquals("Henry", manager.get(0).getFirstName());
	}
	
	@Test
	@Order(6)
	void getManagerByBadPass() {
		List<Manager> manager = mdao.getManagerByPass("BADPASS");
		Assertions.assertEquals(0, manager.size());
	}
	
	@Test
	@Order(7)
	void updateManager() {
		Manager manager = mdao.getManagerById(1);
		manager.setPassword("WazowskiWuzHere");
		manager = mdao.updateManager(manager);
		Assertions.assertEquals("WazowskiWuzHere", manager.getPassword());
		
		manager.setPassword("TotallyNotEvilAtAll");
		manager = mdao.updateManager(manager);
	}
	
	@Test
	@Order(8)
	void updateBadManager() {
		Manager manager = new Manager(130, "BAD", "BAD", "BAD", "BAD");
		manager = mdao.updateManager(manager);
		Assertions.assertNull(manager);	
	}


}
