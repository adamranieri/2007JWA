package dev.kurt.servicetests;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mockito;

import dev.kurt.daos.EmployeeDAO;
import dev.kurt.daos.ReimbursementDAO;
import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;
import dev.kurt.entities.Reimbursement;
import dev.kurt.services.EmployeeService;
import dev.kurt.services.EmployeeServiceImpl;

@TestMethodOrder(OrderAnnotation.class) 
public class EmployeeServiceTests {
	
	@Test
	@Order(1)
	void createEmployee() {
		Employee kurt = new Employee(1,"kd@email.com","password","Kurt","Martinez");
		ReimbursementDAO rDao = Mockito.mock(ReimbursementDAO.class);
		EmployeeDAO eDao = Mockito.mock(EmployeeDAO.class);
		
		Mockito.when(eDao.createEmployee(kurt)).thenReturn(kurt);
		EmployeeService eServ = new EmployeeServiceImpl(eDao,rDao);
		
		eServ.createEmployee(kurt);
		Assertions.assertNotEquals(0, kurt.getEmployeeId());
	}
	
	
	@Test
	@Order(2)
	void getEmployeeById() {
		Employee kurt = new Employee(1,"kd@email.com","password","Kurt","Martinez");
		ReimbursementDAO rDao = Mockito.mock(ReimbursementDAO.class);
		EmployeeDAO eDao = Mockito.mock(EmployeeDAO.class);
		
		Mockito.when(eDao.getEmployeeById(1)).thenReturn(kurt);
		EmployeeService eServ = new EmployeeServiceImpl(eDao,rDao);
		
		Employee Employee = eServ.getEmployeeById(1);
		Assertions.assertEquals(1, Employee.getEmployeeId());
	}
	
	@Test
	@Order(3)
	void getEmployeeByLogin() {
		Employee kurt = new Employee(1,"kd@email.com","password","Kurt","Martinez");
		ReimbursementDAO rDao = Mockito.mock(ReimbursementDAO.class);
		EmployeeDAO eDao = Mockito.mock(EmployeeDAO.class);
		
		Mockito.when(eDao.getEmployeeByLogin("kd@email.com", "password")).thenReturn(kurt);
		EmployeeService eServ = new EmployeeServiceImpl(eDao,rDao);
		
		Employee Employee = eServ.getEmployeeByLogin("kd@email.com", "password");
		Assertions.assertEquals(1, Employee.getEmployeeId());
	}
	
	@Test
	@Order(4) 
	void getAllEmployees(){
		Employee bobby = new Employee(0,"bob@email.com","bobspassword","Bob","Bobson");
		Employee kurt = new Employee(0,"kd@email.com","password","Kurt","Martinez");
		
		List<Employee> fakeEmployees = new ArrayList<Employee>();
		fakeEmployees.add(kurt);
		fakeEmployees.add(bobby);
		
		ReimbursementDAO rDao = Mockito.mock(ReimbursementDAO.class);
		EmployeeDAO eDao = Mockito.mock(EmployeeDAO.class);
		
		Mockito.when(eDao.getAllEmployees()).thenReturn(fakeEmployees);
		EmployeeService eServ = new EmployeeServiceImpl(eDao,rDao);
		
		List<Employee> Employees = eServ.getAllEmployees();
		Assertions.assertEquals(2,Employees.size());
	}
	
	@Test
	@Order(5) 
	void updateEmployee() {
		Employee bobby = new Employee(0,"bob@email.com","bobspassword","Bob","Bobson");
		
		Employee bobby2 = new Employee(0,"bob@email.com","bobspassword","Keith","Bobson");
		
		ReimbursementDAO rDao = Mockito.mock(ReimbursementDAO.class);
		EmployeeDAO eDao = Mockito.mock(EmployeeDAO.class);
		
		Mockito.when(eDao.updateEmployee(bobby)).thenReturn(bobby2);
		EmployeeService eServ = new EmployeeServiceImpl(eDao,rDao);
		
		bobby.setfName("Keith");
		Employee Employee = eServ.updateEmployee(bobby);
		Assertions.assertEquals("Keith",Employee.getEmpfName());
	}
	
	@Test
	@Order(6)
	void getEmployeesByManager() {
		Employee bobby = new Employee(0,"bob@email.com","bobspassword","Bob","Bobson");
		Employee kurt = new Employee(0,"kd@email.com","password","Kurt","Martinez");
		Manager keith = new Manager(0,"kd@email.com","password","Keith","Richards");
		
		List<Employee> fakeEmployees = new ArrayList<Employee>();
		fakeEmployees.add(kurt);
		fakeEmployees.add(bobby);
		
		ReimbursementDAO rDao = Mockito.mock(ReimbursementDAO.class);
		EmployeeDAO eDao = Mockito.mock(EmployeeDAO.class);
		
		Mockito.when(eDao.getEmployeesByManager(keith)).thenReturn(fakeEmployees);
		EmployeeService eServ = new EmployeeServiceImpl(eDao,rDao);
		
		
		List<Employee> michaelsEmployees = eServ.getEmployeesByManager(keith);
		Assertions.assertEquals(2,michaelsEmployees.size());
	}
	
	@Test
	@Order(7) 
	void addReimbursementToEmployee() {
		Employee kurt = new Employee(0,"kd@email.com","password","Kurt","Martinez");
		Reimbursement reimbursement = new Reimbursement(0,"Ubers from airport",100,"july29",kurt);
		Employee kurt2 = new Employee(0,"kd@email.com","password","Kurt","Martinez");
		kurt2.getReimbursements().add(reimbursement);
		
		ReimbursementDAO rDao = Mockito.mock(ReimbursementDAO.class);
		EmployeeDAO eDao = Mockito.mock(EmployeeDAO.class);
		
		Mockito.when(eDao.updateEmployee(kurt)).thenReturn(kurt2);
		Mockito.when(rDao.createReimbursement(reimbursement)).thenReturn(reimbursement);
		EmployeeService eServ = new EmployeeServiceImpl(eDao,rDao);
		
		eServ.addReimbursementToEmployee(kurt, reimbursement);
		List<Reimbursement> reis = kurt.getReimbursements();
		Assertions.assertEquals(1,reis.size());
	}
	
	
	@Test
	@Order(8) 
	void deleteEmployee() {
		Employee bobby = new Employee(0,"bob@email.com","bobspassword","Bob","Bobson");
		
		EmployeeDAO eDao = Mockito.mock(EmployeeDAO.class);
		ReimbursementDAO rDao = Mockito.mock(ReimbursementDAO.class);
		
		Mockito.when(eDao.deleteEmployee(bobby)).thenReturn(true);
		EmployeeService manServ = new EmployeeServiceImpl(eDao,rDao);
		
		boolean result = manServ.deleteEmployee(bobby);
		Assertions.assertEquals(true,result);
	}
	

}
