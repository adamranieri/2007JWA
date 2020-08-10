package dev.cosentino.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.cosentino.daos.EmployeeDAO;
import dev.cosentino.daos.EmployeeDAOhibernate;
import dev.cosentino.entities.Employee;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class EmployeeDAOtests {

	private static EmployeeDAO edao = EmployeeDAOhibernate.getEmployeeDAOhibernate();
	
	@Test 
	@Order(1)
	void getAllEmployees() {
		List<Employee> employees = edao.getAllEmployees();
		Assertions.assertEquals(3, employees.size());
	}
	
	@Test
	@Order(2)
	void getEmployeeById() {
		Employee employee = edao.getEmployeeById(1);
		Assertions.assertEquals(1, employee.geteId());
	}
	
	@Test
	@Order(3)
	void getEmployeeByUsername() {
		Employee employee = edao.getEmployeeByUsername("allissa_the_great01");
		Assertions.assertEquals(3, employee.geteId());
	}

	@Test
	@Order(4)
	void getEmployeeByName() {
		List<Employee> employees = edao.getEmployeeByName("Steve");
		Assertions.assertEquals(1, employees.size());
	}
	
	@Test
	@Order(5)
	void updateEmployee() {
		Employee employee = edao.getEmployeeById(2);
		employee.setUsername("ash89");
		edao.updateEmployee(employee);
		Assertions.assertEquals("ash89", employee.getUsername());
	}
	
	@Test
	@Order(6)
	void getAllManagers() {
		List<Employee> employees = edao.getAllManagers();
		Assertions.assertEquals(3, employees.size());
	}
}
