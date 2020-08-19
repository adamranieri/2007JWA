package dev.nauman.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.nauman.daos.ManagerDAO;
import dev.nauman.daos.ManagerDAOImpl;
import dev.nauman.entities.Manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class ManagerDAOTests {

	ManagerDAO mdao = ManagerDAOImpl.getManagerDAOImpl();
	
	@Test
	@Order(1)
	void getManagerByIdPositiveTest() {
		Manager Manager = mdao.getManagerById(1);
		Assertions.assertEquals("Chris", Manager.getFirstName());
	}
	@Test
	@Order(2)
	void getManagerByIdNegativeTest() {
		Manager Manager = mdao.getManagerById(133);
		Assertions.assertEquals(null, Manager);
	}
	@Test
	@Order(3)
	void getAllManagersTest() {
		List<Manager> Managers = mdao.getAllManagers();
		Assertions.assertEquals(2, Managers.size());
	}
	@Test
	@Order(4)
	void updateManagerPositiveTest() {
		Manager Manager = new Manager(2, "Ron", "Swanson", "BACON", "TheGovernmentREALLYSucks");
		Manager = mdao.updateManager(Manager);
		Assertions.assertEquals("TheGovernmentREALLYSucks", Manager.getPassword());
	}
	@Test
	@Order(5)
	void updateManagerWrongManagerTest() {
		Manager Manager = new Manager(133, "Ron", "Swanson", "BACON", "TheGovernmentSucks");
		Manager = mdao.updateManager(Manager);
		Assertions.assertEquals(null, Manager);
	}

}
