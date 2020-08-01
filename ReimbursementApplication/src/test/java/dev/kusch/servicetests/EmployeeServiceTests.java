package dev.kusch.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.kusch.entities.Employee;
import dev.kusch.entities.Reimbursement;
import dev.kusch.services.EmployeeService;
import dev.kusch.services.EmployeeServiceImpl;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class EmployeeServiceTests {
	
	private static EmployeeService eserv = new EmployeeServiceImpl();
	
	@Test
	@Order(1)
	void getEmployeeById() {
		Employee employee = eserv.getEmployeeById(1);
		Assertions.assertEquals("Sullivan", employee.getLastName());
	}
	
	@Test
	@Order(2)
	void getEmployeeByIdNegative() {
		Employee employee = eserv.getEmployeeById(30000);
		Assertions.assertNull(employee);
	}
	
	@Test
	@Order(3)
	void getEmployeeByUser() {
		List<Employee> employee = eserv.getEmployeeByUser("Sullivan");
		Assertions.assertEquals(1, employee.size());
		Assertions.assertEquals("Sullivan", employee.get(0).getLastName());
	}
	
	@Test
	@Order(4)
	void getEmployeeByNegativeUser() {
		List<Employee> employee = eserv.getEmployeeByUser("FakeUser");
		Assertions.assertEquals(0, employee.size());
	}
	
	@Test
	@Order(5)
	void updateEmployee() {
		Employee employee = eserv.getEmployeeById(1);
		employee.setPassword("NewPass");
		eserv.updateEmployee(employee);
		Assertions.assertEquals("NewPass", eserv.getEmployeeById(1).getPassword());
		
		employee.setPassword("B3stSc4r3r");
		eserv.updateEmployee(employee);
	}
	
	@Test
	@Order(6)
	void updateBadEmployee() {
		Employee employee = new Employee(10, "Boo", "hugs", "Boo", "Boo", new ArrayList<Reimbursement>());
		Employee emp = eserv.updateEmployee(employee);
		Assertions.assertNull(emp);
	}
	
	@Test
	@Order(7)
	void testLoginSuccess() {
		boolean result = eserv.loginEmployee("Sullivan", "B3stSc4r3r");
		Assertions.assertTrue(result);
	}
	
	@Test
	@Order(8)
	void testLoginBadPass() {
		boolean result = eserv.loginEmployee("Sullivan", "FakePassword");
		Assertions.assertFalse(result);
	}

	@Test
	@Order(9)
	void testLoginBadUser() {
		boolean result = eserv.loginEmployee("FakeEmployee", "B3stSc4r3r");
		Assertions.assertFalse(result);
	}

	@Test
	@Order(10)
	void testLoginWrongUserPass() {
		boolean result = eserv.loginEmployee("Wazowski", "B3stSc4r3r");
		Assertions.assertFalse(result);
	}
}
