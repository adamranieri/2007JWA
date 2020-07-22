package dev.lee.daotests;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.lee.daos.CustomerDAO;
import dev.lee.daos.CustomerDAOMaria;
import dev.lee.entities.Customer;
import dev.lee.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class CustomerDAOtests {

	public static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();
	
	@BeforeAll
	static void setUp() {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL bank_db.set_up_database";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@AfterAll
	static void tearDown() {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL bank_db.tear_down_database";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void createCustomer() {		
		Customer testCustomer = new Customer(0, "testUser", "testpw");
		
		testCustomer = cdao.createCustomer(testCustomer);
		Assertions.assertNotEquals(0, testCustomer.getcID());
	}
	
	@Test
	@Order(2)
	void getCustomerById() {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL bank_db.populate_database";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Customer testCustomer = cdao.getCustomerById(2);
		
		Assertions.assertEquals(2, testCustomer.getcID());
	}
	
	@Test
	@Order(3)
	void getCustomerByUsername() {
		Customer testCustomer = cdao.getCustomerByUsername("testy");
		Assertions.assertEquals("testy", testCustomer.getUsername());
	}
	
	@Test
	@Order(4)
	void getAllCustomers() {
		Set<Customer> customers = cdao.getAllCustomers();
		
		Assertions.assertEquals(5, customers.size());
	}

	@Test
	@Order(5)
	void updateCustomer() {
		Customer customer = cdao.getCustomerById(1);
		customer.setUsername("updatedUser");
		
		customer = cdao.updateCustomer(customer);
		Assertions.assertEquals("updatedUser", customer.getUsername());
	}
	
	@Test
	@Order(6)
	void deleteCustomer() {
		boolean result = cdao.deleteCustomer(1);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(7)
	void deleteCustomerNegative() {
		boolean result = cdao.deleteCustomer(999);
		Assertions.assertEquals(false, result);
	}
	
}
