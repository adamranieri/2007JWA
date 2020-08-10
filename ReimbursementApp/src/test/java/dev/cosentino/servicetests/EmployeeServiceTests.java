package dev.cosentino.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import dev.cosentino.entities.Employee;
import dev.cosentino.services.EmployeeService;
import dev.cosentino.services.EmployeeServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
class EmployeeServiceTests {

	private static EmployeeService eserv = new EmployeeServiceImpl();
	
	@Test
	@Order(1)
	void getAllEmployees() {
		List<Employee> emp = eserv.getAllEmployees();
		System.out.println(emp);
		Assertions.assertEquals(3, emp.size());
	}
	
	@Test
	@Order(2)
	void getEmployeeById(){
		Employee emp = eserv.getEmployeeById(1);
		Assertions.assertEquals(1, emp.geteId());
	}
		
	@Test
	@Order(3)
	void getEmployeeByUsername() {
		Employee emp = eserv.getEmployeeByUsername("ash89");
		System.out.println(emp);
		Assertions.assertEquals(2, emp.geteId());
	}
	
	@Test
	@Order(4)
	void getEmployeeByName() {
		List<Employee> employees = eserv.getEmployeeByName("Steve");
		Assertions.assertEquals(1, employees.size());
	}
	
	
	@Test
	@Order(5)
	void getAllManagers() {
		List<Employee> employees = eserv.getAllManagers();
		Assertions.assertEquals(3, employees.size());
	}
		
	
	@Test
	@Order(6)
	void updatePassword() {
		Employee employee = eserv.getEmployeeById(3);
		eserv.updatePassword(employee, "newpassword");
		Assertions.assertEquals("newpassword", employee.getPassword());
	}
	
	@Test
	@Order(7)
	void updateUsername() {
		Employee employee = eserv.getEmployeeById(3);
		eserv.updateUsername(employee, "AllissasNewUsername");
		Assertions.assertEquals("AllissasNewUsername", employee.getUsername());
	}
	
	@Test
	@Order(8)
	void updateTitle() {
		Employee employee = eserv.getEmployeeById(3);
		eserv.updateTitle(employee, "Manager");
		Assertions.assertEquals("Manager", employee.getTitle());
	}

}
