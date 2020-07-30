package dev.kurt.daotests;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import dev.kurt.daos.EmployeeDAO;
import dev.kurt.daos.EmployeeDAOHibernate;
import dev.kurt.daos.ManagerDAO;
import dev.kurt.daos.ManagerDAOHibernate;
import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;

@TestMethodOrder(OrderAnnotation.class) 
class EmployeeDAOTests {
	
	private static EmployeeDAO eDao = new EmployeeDAOHibernate();
	private static ManagerDAO manDao = new ManagerDAOHibernate();
	private static Manager michael = new Manager(0,"gretzky@email.com","number1boss","Michael","Scott");
	
	@BeforeAll
	static void setUp() {
		manDao.createManager(michael);
	}
	
	@Test
	@Order(1)
	void createEmployee() {
		Employee kurt = new Employee(0,"kd@email.com","password","Kurt","Martinez");
		eDao.createEmployee(kurt);
		Assertions.assertNotEquals(0, kurt.getEmployeeId());
	}
	
	
	@Test
	@Order(2)
	void getEmployeeById() {
		Employee employee = eDao.getEmployeeById(1);
		Assertions.assertEquals(1, employee.getEmployeeId());
	}
	
	@Test
	@Order(3)
	void getEmployeeByLogin() {
		Employee employee = eDao.getEmployeeByLogin("kd@email.com", "password");
		Assertions.assertEquals(1, employee.getEmployeeId());
	}
	
	@Test
	@Order(4) 
	void getAllEmployees(){
		Employee bobby = new Employee(0,"bob@email.com","bobspassword","Bob","Bobson");
		eDao.createEmployee(bobby);
		List<Employee> employees = eDao.getAllEmployees();
		Assertions.assertEquals(2,employees.size());
	}
	
	@Test
	@Order(5) 
	void updateEmployee() {
		Employee employee = eDao.getEmployeeById(1);
		employee.setEmpPassword("MoreSecurePassword123");
		eDao.updateEmployee(employee);
		Assertions.assertNotEquals("password",employee.getEmpPassword());
	}
	
	@Test
	@Order(6)
	void getEmployeesByManager() {
		Employee kurt = eDao.getEmployeeById(1);
		kurt.setManager(michael);
		eDao.updateEmployee(kurt);
		Employee bobby = eDao.getEmployeeById(2);
		bobby.setManager(michael);
		eDao.updateEmployee(bobby);
		List<Employee> michaelsEmployees = eDao.getEmployeesByManager(michael);
		System.out.println(michaelsEmployees);
		Assertions.assertEquals(2,michaelsEmployees.size());
	}
	
	
	@Test
	@Order(7) 
	void deleteEmployee() {
		Employee employee = eDao.getEmployeeById(1);
		boolean result = eDao.deleteEmployee(employee);
		Assertions.assertEquals(true,result);
	}
	
	@AfterAll
	static void tearDown() {
		manDao.deleteManager(michael);
	}
	
	
	

}
