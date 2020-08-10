package dev.kurt.servicetests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.kurt.daos.EmployeeDAO;
import dev.kurt.daos.ManagerDAO;
import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;
import dev.kurt.services.ManagerService;
import dev.kurt.services.ManagerServiceImpl;


@TestMethodOrder(OrderAnnotation.class)
public class ManagerServiceTests {

	@Test
	@Order(1)
	void createManager() {
		Manager kurt = new Manager(1,"kd@email.com","password","Kurt","Martinez");
		ManagerDAO mDao = Mockito.mock(ManagerDAO.class);
		EmployeeDAO eDao = Mockito.mock(EmployeeDAO.class);
		
		Mockito.when(mDao.createManager(kurt)).thenReturn(kurt);
		ManagerService manServ = new ManagerServiceImpl(mDao,eDao);
		
		manServ.createManager(kurt);
		Assertions.assertNotEquals(0, kurt.getManagerId());
	}
	
	
	@Test
	@Order(2)
	void getManagerById() {
		Manager kurt = new Manager(1,"kd@email.com","password","Kurt","Martinez");
		ManagerDAO mDao = Mockito.mock(ManagerDAO.class);
		EmployeeDAO eDao = Mockito.mock(EmployeeDAO.class);
		
		Mockito.when(mDao.getManagerById(1)).thenReturn(kurt);
		ManagerService manServ = new ManagerServiceImpl(mDao,eDao);
		
		Manager manager = manServ.getManagerById(1);
		Assertions.assertEquals(1, manager.getManagerId());
	}
	
	@Test
	@Order(3)
	void getManagerByLogin() {
		Manager kurt = new Manager(1,"kd@email.com","password","Kurt","Martinez");
		ManagerDAO mDao = Mockito.mock(ManagerDAO.class);
		EmployeeDAO eDao = Mockito.mock(EmployeeDAO.class);
		
		Mockito.when(mDao.getManagerByLogin("kd@email.com", "password")).thenReturn(kurt);
		ManagerService manServ = new ManagerServiceImpl(mDao,eDao);
		
		Manager manager = manServ.getManagerByLogin("kd@email.com", "password");
		Assertions.assertEquals(1, manager.getManagerId());
	}
	
	@Test
	@Order(4) 
	void getAllManagers(){
		Manager bobby = new Manager(0,"bob@email.com","bobspassword","Bob","Bobson");
		Manager kurt = new Manager(0,"kd@email.com","password","Kurt","Martinez");
		
		List<Manager> fakeManagers = new ArrayList<Manager>();
		fakeManagers.add(kurt);
		fakeManagers.add(bobby);
		
		ManagerDAO mDao = Mockito.mock(ManagerDAO.class);
		EmployeeDAO eDao = Mockito.mock(EmployeeDAO.class);
		
		Mockito.when(mDao.getAllManagers()).thenReturn(fakeManagers);
		ManagerService manServ = new ManagerServiceImpl(mDao,eDao);
		
		List<Manager> managers = manServ.getAllManagers();
		Assertions.assertEquals(2,managers.size());
	}
	
	@Test
	@Order(5) 
	void updateManager() {
		Manager bobby = new Manager(0,"bob@email.com","bobspassword","Bob","Bobson");
		
		Manager bobby2 = new Manager(0,"bob@email.com","bobspassword","Keith","Bobson");
		
		ManagerDAO mDao = Mockito.mock(ManagerDAO.class);
		EmployeeDAO eDao = Mockito.mock(EmployeeDAO.class);
		
		Mockito.when(mDao.updateManager(bobby)).thenReturn(bobby2);
		ManagerService manServ = new ManagerServiceImpl(mDao,eDao);
		
		bobby.setfName("Keith");
		Manager manager = manServ.updateManager(bobby);
		Assertions.assertEquals("Keith",manager.getManfName());
	}
	
	@Test
	@Order(6)
	void addEmployee() {
		Employee employee = new Employee(0,"kurt1997","pword","kurt","dawiec");
		Manager bobby = new Manager(0,"bob@email.com","bobspassword","Bob","Bobson");
		
		ManagerDAO mDao = Mockito.mock(ManagerDAO.class);
		EmployeeDAO eDao = Mockito.mock(EmployeeDAO.class);
		
		
		
		Mockito.when(eDao.createEmployee(employee)).thenReturn(employee);
		ManagerService manServ = new ManagerServiceImpl(mDao,eDao);
		
		Manager manager = manServ.addEmployeeToManager(bobby, employee);
		Assertions.assertNotEquals(0,manager.getEmployees().size());
	}
	
	@Test
	@Order(7) 
	void deleteManager() {
		Manager bobby = new Manager(0,"bob@email.com","bobspassword","Bob","Bobson");
		
		ManagerDAO mDao = Mockito.mock(ManagerDAO.class);
		EmployeeDAO eDao = Mockito.mock(EmployeeDAO.class);
		
		Mockito.when(mDao.deleteManager(bobby)).thenReturn(true);
		ManagerService manServ = new ManagerServiceImpl(mDao,eDao);
		
		boolean result = manServ.deleteManager(bobby);
		Assertions.assertEquals(true,result);
	}
	

}
