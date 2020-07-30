package dev.kurt.servicetests;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;
import dev.kurt.entities.Reimbursement;
import dev.kurt.services.EmployeeService;
import dev.kurt.services.EmployeeServiceImpl;
import dev.kurt.services.ManagerService;
import dev.kurt.services.ManagerServiceImpl;

@TestMethodOrder(OrderAnnotation.class) 
public class EmployeeServiceTests {
	
	private static EmployeeService eServ = new EmployeeServiceImpl();
	private static ManagerService manServ = new ManagerServiceImpl();
	private static Manager michael = new Manager(0,"gretzky@email.com","number1boss","Michael","Scott");
	
	@BeforeAll
	static void setUp() {
		manServ.createManager(michael);
	}
	
	@Test
	@Order(1)
	void createEmployee() {
		Employee kurt = new Employee(0,"kd@email.com","password","Kurt","Martinez");
		eServ.createEmployee(kurt);
		Assertions.assertNotEquals(0, kurt.getEmployeeId());
	}
	
	
	@Test
	@Order(2)
	void getEmployeeById() {
		Employee employee = eServ.getEmployeeById(1);
		Assertions.assertEquals(1, employee.getEmployeeId());
	}
	
	@Test
	@Order(3)
	void getEmployeeByLogin() {
		Employee employee = eServ.getEmployeeByLogin("kd@email.com", "password");
		Assertions.assertEquals(1, employee.getEmployeeId());
	}
	
	@Test
	@Order(4) 
	void getAllEmployees(){
		Employee bobby = new Employee(0,"bob@email.com","bobspassword","Bob","Bobson");
		eServ.createEmployee(bobby);
		List<Employee> employees = eServ.getAllEmployees();
		Assertions.assertEquals(2,employees.size());
	}
	
	@Test
	@Order(5) 
	void updateEmployee() {
		Employee employee = eServ.getEmployeeById(1);
		employee.setEmpPassword("MoreSecurePassword123");
		eServ.updateEmployee(employee);
		Assertions.assertNotEquals("password",employee.getEmpPassword());
	}
	
	@Test
	@Order(6)
	void getEmployeesByManager() {
		Employee kurt = eServ.getEmployeeById(1);
		kurt.setManager(michael);
		eServ.updateEmployee(kurt);
		Employee bobby = eServ.getEmployeeById(2);
		bobby.setManager(michael);
		eServ.updateEmployee(bobby);
		List<Employee> michaelsEmployees = eServ.getEmployeesByManager(michael);
		Assertions.assertEquals(2,michaelsEmployees.size());
	}
	
	@Test
	@Order(7) 
	void addReimbursementToEmployee() {
		Employee kurt = eServ.getEmployeeById(1);
		Reimbursement reimbursement = new Reimbursement(0,"Ubers from airport",100,"july29",kurt);
		eServ.addReimbursementToEmployee(kurt, reimbursement);
		List<Reimbursement> reis = kurt.getReimbursements();
		Assertions.assertEquals(1,reis.size());
	}
	
	
	@Test
	@Order(8) 
	void deleteEmployee() {
		Employee employee = eServ.getEmployeeById(1);
		boolean result = eServ.deleteEmployee(employee);
		Assertions.assertEquals(true,result);
	}
	
	@AfterAll
	static void tearDown() {
		manServ.deleteManager(michael);
	}
}
