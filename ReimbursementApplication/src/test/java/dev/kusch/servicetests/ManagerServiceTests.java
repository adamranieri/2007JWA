package dev.kusch.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mockito;

import dev.kusch.daos.ManagerDAO;
import dev.kusch.dtos.LoginDTO;
import dev.kusch.entities.Manager;
import dev.kusch.entities.Manager;
import dev.kusch.services.ManagerServiceImpl;
import dev.kusch.services.ManagerService;

@TestMethodOrder(OrderAnnotation.class)
class ManagerServiceTests {

	private static ManagerService mserv = null;

	private static Manager man;
	private static Manager badMan;
	private static Manager upMan;
	private static List<Manager> manList;

	@BeforeAll
	static void setupMocks() {
		man = new Manager(1, "Waternoose", "TotallyNotEvilAtAll", "Henry", "Waternoose");
		badMan = new Manager(90, "CDA", "2319", "Yellow", "Suit");
		upMan = new Manager(1, "Waternoose", "TotallyNotEvil", "Henry", "Waternoose");
		manList = new ArrayList<Manager>();
		manList.add(man);
		ManagerDAO mdao = Mockito.mock(ManagerDAO.class);
		
		Mockito.when(mdao.getManagerById(1)).thenReturn(man);
		Mockito.when(mdao.getManagerById(70)).thenReturn(null);
		Mockito.when(mdao.getManagerByUser("Waternoose")).thenReturn(manList);
		Mockito.when(mdao.getManagerByUser("CDA")).thenReturn(new ArrayList<Manager>());
		Mockito.when(mdao.getManagerByPass("TotallyNotEvilAtAll")).thenReturn(manList);
		Mockito.when(mdao.getManagerByPass("ShutItDown")).thenReturn(new ArrayList<Manager>());
		Mockito.when(mdao.updateManager(upMan)).thenReturn(upMan);
		Mockito.when(mdao.updateManager(badMan)).thenReturn(null);
		
		mserv = new ManagerServiceImpl(mdao);
	}
	
	@Test
	@Order(1)
	void getManagerById() {
		Manager manager = mserv.getManagerById(1);
		Assertions.assertEquals("Waternoose", manager.getLastName());
	}
	
	@Test
	@Order(2)
	void getManagerByIdNegative() {
		Manager manager = mserv.getManagerById(70);
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
		List<Manager> manager = mserv.getManagerByUser("CDA");
		Assertions.assertEquals(0, manager.size());
	}
	
	@Test
	@Order(5)
	void updatePassword() {
		Manager manager = upMan;
		manager = mserv.updateManager(manager);
		Assertions.assertEquals("TotallyNotEvil", manager.getPassword());
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
		Manager result = mserv.loginManager(new LoginDTO("Waternoose", "TotallyNotEvilAtAll"));
		Assertions.assertNotNull(result);
	}
	
	@Test
	@Order(8)
	void testLoginBadPass() {
		Manager result = mserv.loginManager(new LoginDTO("Waternoose", "FakePassword"));
		Assertions.assertNull(result);
	}

	@Test
	@Order(9)
	void testLoginBadUser() {
		Manager result = mserv.loginManager(new LoginDTO("FakeManager", "TotallyNotEvilAtAll"));
		Assertions.assertNull(result);
	}

	@Test
	@Order(10)
	void testLoginWrongUserPass() {
		Manager result = mserv.loginManager(new LoginDTO("Roz", "TotallyNotEvilAtAll"));
		Assertions.assertNull(result);
	}
}
