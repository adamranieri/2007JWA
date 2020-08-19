package dev.nauman.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.eclipse.jetty.util.log.Log;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.nauman.daos.EmployeeDAO;
import dev.nauman.daos.EmployeeDAOImpl;
import dev.nauman.daos.ManagerDAO;
import dev.nauman.daos.ManagerDAOImpl;
import dev.nauman.entities.Employee;
import dev.nauman.utils.HibernateUtil;

@TestMethodOrder(OrderAnnotation.class)
class EmployeeDAOTests {

	EmployeeDAO edao = EmployeeDAOImpl.getEmployeeDAOImpl();
	ManagerDAO ddao = ManagerDAOImpl.getManagerDAOImpl();
	
	@Test
	@Order(1)
	void getEmployeeByIdPositiveTest() {
		Employee employee = edao.getEmployeeById(1);
		Assertions.assertEquals("Ben", employee.getFirstName());
	}
	@Test
	@Order(2)
	void getEmployeeByIdNegativeTest() {
		Employee employee = edao.getEmployeeById(133);
		Assertions.assertEquals(null, employee);
	}
	@Test
	@Order(3)
	void getAllEmployeesTest() {
		List<Employee> employees = edao.getAllEmployees();
		Assertions.assertEquals(8, employees.size());
	}
	@Test
	@Order(4)
	void updateEmployeePositiveTest() {
		Employee employee = new Employee(1, "Ben", "Wyatt", "calzoneking", "ledgermanOfDunshire",false);
		employee = edao.updateEmployee(employee);
		Assertions.assertEquals("ledgermanOfDunshire", employee.getPassword());
	}
	@Test
	@Order(5)
	void updateEmployeeWrongEmployeeTest() {
		Employee employee = new Employee(133, "Ben", "Wyatt", "calzoneking", "ledgermanOfDunshire",false);
		employee = edao.updateEmployee(employee);
		Assertions.assertEquals(null, employee);
	}
	@Test
	@Order(6)
	void getAllManagersTest() {
		List<Employee> managers = edao.getAllManagers();
		System.out.println(managers);
		Assertions.assertEquals(2, managers.size());
	}
	

}
