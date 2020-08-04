package dev.kurt.daotests;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.kurt.daos.EmployeeDAO;
import dev.kurt.daos.EmployeeDAOHibernate;
import dev.kurt.daos.ManagerDAO;
import dev.kurt.daos.ManagerDAOHibernate;
import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;


@TestMethodOrder(OrderAnnotation.class)
class ManagerDAOTests {
	
	private static ManagerDAO manDao = new ManagerDAOHibernate();
	
	
	@Test
	@Order(1)
	void createManager() {
		Manager kurt = new Manager(0,"kd@email.com","password","Kurt","Martinez");
		manDao.createManager(kurt);
		Assertions.assertNotEquals(0, kurt.getManagerId());
	}
	
	
	@Test
	@Order(2)
	void getManagerById() {
		Manager manager = manDao.getManagerById(1);
		Assertions.assertEquals(1, manager.getManagerId());
	}
	
	@Test
	@Order(3)
	void getManagerByLogin() {
		Manager manager = manDao.getManagerByLogin("kd@email.com", "password");
		Assertions.assertEquals(1, manager.getManagerId());
	}
	
	@Test
	@Order(4) 
	void getAllManagers(){
		Manager bobby = new Manager(0,"bob@email.com","bobspassword","Bob","Bobson");
		manDao.createManager(bobby);
		List<Manager> managers = manDao.getAllManagers();
		Assertions.assertEquals(2,managers.size());
	}
	
	@Test
	@Order(5) 
	void updateManager() {
		Manager manager = manDao.getManagerById(1);
		manager.setManPassword("MoreSecurePassword123");
		manDao.updateManager(manager);
		Assertions.assertNotEquals("password",manager.getManPassword());
	}
	
	
	@Test
	@Order(7) 
	void deleteManager() {
		Manager manager = manDao.getManagerById(1);
		boolean result = manDao.deleteManager(manager);
		Assertions.assertEquals(true,result);
	}
}
