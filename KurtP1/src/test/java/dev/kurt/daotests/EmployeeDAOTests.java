package dev.kurt.daotests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import dev.kurt.daos.EmployeeDAO;
import dev.kurt.daos.EmployeeDAOHibernate;
import dev.kurt.entities.Employee;

@TestMethodOrder(OrderAnnotation.class) 
class EmployeeDAOTests {
	
	private static EmployeeDAO edao = new EmployeeDAOHibernate();
	
	
	
	@Test
	@Order(1)
	void createEmployee() {
		Employee kurt = new Employee(0,"kurt@email.com","password","kurt","dawiec",1);
		edao.createEmployee(kurt);
		Assertions.assertNotEquals(0, kurt.getEmployeeId());
	}
	
	@Test
	@Order(2)
	void getEmployeeByID() {
		Employee employee = edao.getEmployeeById(1);
		Assertions.assertEquals(1,employee.getEmployeeId());
	}
	
	@Test
	@Order(3)
	void updateEmployee() {
		Employee employee = edao.getEmployeeById(1);
		employee.setlName("Rodriguez");
		edao.updateEmployee(employee);
		Assertions.assertEquals("Rodriguez", employee.getlName());
	}
	
	

}
