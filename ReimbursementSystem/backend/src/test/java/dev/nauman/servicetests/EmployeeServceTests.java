package dev.nauman.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.nauman.entities.Employee;
import dev.nauman.services.EmployeeService;
import dev.nauman.services.EmployeeServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
class EmployeeServceTests {

	private EmployeeService eserv = EmployeeServiceImpl.getEmployeeServiceImpl();
	
	@Test
	@Order(1)
	void verifyEmployeePositiveTest() {
		Employee employee = eserv.login("calzoneking", "ledgermanOfDunshire");
		Assertions.assertEquals("calzoneking", employee.getUsername());
	}
	
	@Test
	@Order(2)
	void verifyEmployeeNegativeTest() {
		Employee employee = eserv.login("123123","esquireofdunshire");
		Assertions.assertEquals(null, employee);
	}

	@Test
	@Order(3)
	void getEmployeeByIdPositiveTest() {
		Employee employee = eserv.getEmployeeById(1);
		Assertions.assertEquals("Ben", employee.getFirstName());
	}
	@Test
	@Order(4)
	void getEmployeeByIdNegativeTest() {
		Employee employee = eserv.getEmployeeById(133);
		Assertions.assertEquals(null, employee);
	}
	@Test
	@Order(5)
	void getAllEmployeesTest() {
		List<Employee> employees = eserv.getAllEmployees();
		Assertions.assertEquals(8, employees.size());
	}
	@Test
	@Order(6)
	void updateEmployeePositiveTest() {
		Employee employee = new Employee(1, "Ben", "Wyatt", "calzoneking", "ledgermanOfDunshire",false);
		employee = eserv.updateEmployee(employee);
		Assertions.assertEquals("ledgermanOfDunshire", employee.getPassword());
	}
	@Test
	@Order(7)
	void updateEmployeeWrongEmployeeTest() {
		Employee employee = new Employee(133, "Ben", "Wyatt", "calzoneking", "ledgermanOfDunshire",false);
		employee = eserv.updateEmployee(employee);
		Assertions.assertEquals(null, employee);
	}
	@Test
	@Order(8)
	void getAllManagersTest() {
		List<Employee> managers = eserv.getAllManagers();
		System.out.println(managers);
		Assertions.assertEquals(2, managers.size());
	}
}
