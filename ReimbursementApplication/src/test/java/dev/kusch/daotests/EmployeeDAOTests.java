package dev.kusch.daotests;

import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import dev.kusch.daos.EmployeeDAO;
import dev.kusch.daos.EmployeeDAOHibernate;
import dev.kusch.entities.Employee;
import dev.kusch.entities.Reimbursement;

@TestMethodOrder(OrderAnnotation.class)
public class EmployeeDAOTests {
	
	private static EmployeeDAO edao= EmployeeDAOHibernate.getEmployeeDAOHibernate();
	
	@Test
	@Order(1)
	void getEmployeeById() {
		Employee employee = edao.getEmployeeById(2);
		Assertions.assertEquals("Wazowski", employee.getUsername());
	}
	
	@Test
	@Order(2)
	void getEmployeeByBadId() {
		Employee employee = edao.getEmployeeById(500);
		Assertions.assertNull(employee);
	}
	
	@Test
	@Order(3)
	void getEmployeeByUser() {
		List<Employee> employee = edao.getEmployeeByUser("Wazowski");
		Assertions.assertEquals(2, employee.get(0).getEid());
	}
	
	@Test
	@Order(4)
	void getEmployeeByBadUser() {
		List<Employee> employee = edao.getEmployeeByUser("BADUSER");
		Assertions.assertEquals(0, employee.size());
	}
	
	@Test
	@Order(5)
	void getEmployeeByPass() {
		List<Employee> employee = edao.getEmployeeByPass("B3stT3ch");
		System.out.println(employee);
		Assertions.assertEquals(1, employee.size());
		Assertions.assertEquals("Mike", employee.get(0).getFirstName());
	}
	
	@Test
	@Order(6)
	void getEmployeeByBadPass() {
		List<Employee> employee = edao.getEmployeeByPass("BADPASS");
		Assertions.assertEquals(0, employee.size());
	}
	
	@Test
	@Order(7)
	void updateEmployee() {
		Employee employee = edao.getEmployeeById(1);
		employee.setPassword("WazowskiWuzHere");
		employee = edao.updateEmployee(employee);
		Assertions.assertEquals("WazowskiWuzHere", employee.getPassword());
		
		employee.setPassword("B3stSc4r3r");
		employee = edao.updateEmployee(employee);
	}
	
	@Test
	@Order(8)
	void updateBadEmployee() {
		List<Reimbursement> reim = new ArrayList<Reimbursement>();
		Employee employee = new Employee(130, "BAD", "BAD", "BAD", "BAD", reim);
		employee = edao.updateEmployee(employee);
		Assertions.assertNull(employee);	
	}
	
	

}
