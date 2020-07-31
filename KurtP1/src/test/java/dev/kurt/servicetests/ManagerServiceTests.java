package dev.kurt.servicetests;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.kurt.daos.ManagerDAO;
import dev.kurt.daos.ManagerDAOHibernate;
import dev.kurt.entities.Manager;
import dev.kurt.services.ManagerService;
import dev.kurt.services.ManagerServiceImpl;


@TestMethodOrder(OrderAnnotation.class)
public class ManagerServiceTests {

private static ManagerService manServ = new ManagerServiceImpl();
	
	@Test
	@Order(1)
	void createManager() {
		Manager kurt = new Manager(0,"kd@email.com","password","Kurt","Martinez");
		manServ.createManager(kurt);
		Assertions.assertNotEquals(0, kurt.getManagerId());
	}
	
	
	@Test
	@Order(2)
	void getManagerById() {
		Manager manager = manServ.getManagerById(1);
		Assertions.assertEquals(1, manager.getManagerId());
	}
	
	@Test
	@Order(3)
	void getManagerByLogin() {
		Manager manager = manServ.getManagerByLogin("kd@email.com", "password");
		Assertions.assertEquals(1, manager.getManagerId());
	}
	
	@Test
	@Order(4) 
	void getAllManagers(){
		Manager bobby = new Manager(0,"bob@email.com","bobspassword","Bob","Bobson");
		manServ.createManager(bobby);
		List<Manager> managers = manServ.getAllManagers();
		Assertions.assertEquals(2,managers.size());
	}
	
	@Test
	@Order(5) 
	void updateManager() {
		Manager manager = manServ.getManagerById(1);
		manager.setManPassword("MoreSecurePassword123");
		manServ.updateManager(manager);
		Assertions.assertNotEquals("password",manager.getManPassword());
	}
	
	@Test
	@Order(6) 
	void deleteManager() {
		Manager manager = manServ.getManagerById(1);
		boolean result = manServ.deleteManager(manager);
		Assertions.assertEquals(true,result);
	}

}
