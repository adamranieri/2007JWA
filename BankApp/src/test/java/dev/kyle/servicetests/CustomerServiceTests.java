package dev.kyle.servicetests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


import dev.kyle.entities.Customer;
import dev.kyle.services.CustomerService;
import dev.kyle.services.CustomerServiceImpl;
import dev.kyle.utils.ConnectionUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceTests {

	private static CustomerService cserv = new CustomerServiceImpl();
	Customer kyle = new Customer(1, "Kylerv", "Password");
	
	@BeforeAll
	static void setUp() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "CALL set_up_bank";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void testAddCustomer() {
		cserv.addCustomer(kyle);
		Assertions.assertNotEquals(0, kyle.getCid());
	}
	
	@Test
	@Order(2)
	void testChangeUsername() {
		Customer kyle = cserv.getCustomerById(1);
		cserv.changeUsername(kyle, "Cooler Kyle");
		Assertions.assertEquals("Cooler Kyle", kyle.getUsername());
	}
	
	@Test
	@Order(3)
	void testChangePassword() {
		Customer kyle = cserv.getCustomerById(1);
		cserv.changePassword(kyle, "NewerPassword");
		Assertions.assertEquals("NewerPassword", kyle.getPassword());
	}
	
	@Test
	@Order(4)
	void testGetCustomerByName() {
		Customer kyle = cserv.getCustomerByName("Cooler Kyle");
		Assertions.assertEquals("Cooler Kyle", kyle.getUsername());
	}
	
	@Test
	@Order(5)
	void testGetAllCustomers() {
		Customer notkyle = new Customer(2, "Brad", "Password2");
		cserv.addCustomer(notkyle);
		Set<Customer> customers = cserv.getAllCustomers();
		Assertions.assertEquals(2, customers.size());
	}
	
	@Test
	@Order(6)
	void testDeleteCustomer() {
		boolean res = cserv.deleteCustomerById(2);
		Assertions.assertEquals(true, res);
	}
	
	@AfterAll
	static void tearDown() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "CALL tear_down_bank";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
