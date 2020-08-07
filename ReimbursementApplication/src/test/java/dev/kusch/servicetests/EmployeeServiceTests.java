package dev.kusch.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.kusch.daos.EmployeeDAO;
import dev.kusch.dtos.LoginDTO;
import dev.kusch.entities.Employee;
import dev.kusch.entities.Reimbursement;
import dev.kusch.services.EmployeeService;
import dev.kusch.services.EmployeeServiceImpl;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mockito;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class EmployeeServiceTests {
	
	private static EmployeeService eserv = null;
	
	private static Employee emp;
	private static Employee badEmp;
	private static Employee upEmp;
	private static List<Employee> empList;
	
	@BeforeAll
	static void setupMocks() {
		emp = new Employee(1, "Sullivan", "B3stSc4r3r", "James", "Sullivan", new ArrayList<Reimbursement>());
		upEmp = new Employee(1, "Sully", "B3stSc4r3r", "James", "Sullivan", new ArrayList<Reimbursement>());
		empList = new ArrayList<Employee>();
		empList.add(emp);
		badEmp = new Employee(100, "Boo", "hugs", "Boo", "Boo", new ArrayList<Reimbursement>());
		EmployeeDAO edao = Mockito.mock(EmployeeDAO.class);
		
		Mockito.when(edao.getEmployeeById(1)).thenReturn(emp);
		Mockito.when(edao.getEmployeeById(70)).thenReturn(null);
		Mockito.when(edao.getEmployeeByUser("Sullivan")).thenReturn(empList);
		Mockito.when(edao.getEmployeeByUser("Boo")).thenReturn(new ArrayList<Employee>());
		Mockito.when(edao.getEmployeeByPass("B3stSc4r3r")).thenReturn(empList);
		Mockito.when(edao.getEmployeeByPass("LotsOfHugs")).thenReturn(new ArrayList<Employee>());
		Mockito.when(edao.updateEmployee(upEmp)).thenReturn(upEmp);
		Mockito.when(edao.updateEmployee(badEmp)).thenReturn(null);
		
		eserv = new EmployeeServiceImpl(edao);
	}
	
	@Test
	@Order(1)
	void getEmployeeById() {
		Employee employee = eserv.getEmployeeById(1);
		Assertions.assertEquals("Sullivan", employee.getLastName());
	}
	
	@Test
	@Order(2)
	void getEmployeeByIdNegative() {
		Employee employee = eserv.getEmployeeById(70);
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
		List<Employee> employee = eserv.getEmployeeByUser("Boo");
		Assertions.assertEquals(0, employee.size());
	}
	
	@Test
	@Order(5)
	void updateEmployee() {
		Employee employee = upEmp;
		employee = eserv.updateEmployee(employee);
		Assertions.assertEquals("Sully", employee.getUsername());
	}
	
	@Test
	@Order(6)
	void updateBadEmployee() {
		Employee employee = badEmp;
		Employee emp = eserv.updateEmployee(employee);
		Assertions.assertNull(emp);
	}
	@Test
	@Order(7)
	void testLoginSuccess() {
		Employee result = eserv.loginEmployee(new LoginDTO("Sullivan", "B3stSc4r3r"));
		Assertions.assertNotNull(result);
	}
	
	@Test
	@Order(8)
	void testLoginBadPass() {
		Employee result = eserv.loginEmployee(new LoginDTO("Sullivan", "FakePassword"));
		Assertions.assertNull(result);
	}

	@Test
	@Order(9)
	void testLoginBadUser() {
		Employee result = eserv.loginEmployee(new LoginDTO("FakeEmployee", "B3stSc4r3r"));
		Assertions.assertNull(result);
	}

	@Test
	@Order(10)
	void testLoginWrongUserPass() {
		Employee result = eserv.loginEmployee(new LoginDTO("Wazowski", "B3stSc4r3r"));
		Assertions.assertNull(result);
	}
}
