package dev.lee.servicetests;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.lee.entities.Customer;
import dev.lee.services.CustomerService;
import dev.lee.services.CustomerServiceImpl;
import dev.lee.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceTests {

	private static CustomerService cserv = new CustomerServiceImpl();
	
	@BeforeAll
	static void setUp() {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL bank_db.set_up_accdatabase";
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
	void establishCustomer() {
		Customer test = new Customer(0, "testUser", "TestPassword");
		cserv.establishCustomer(test);
		Assertions.assertNotEquals(0, test.getcID());
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
		
		Customer test = cserv.getCustomerById(1);
		Assertions.assertEquals(1, test.getcID());
	}
	
	@Test
	@Order(2)
	void getCustomerByIdNegative() {
		Assertions.assertEquals(null, cserv.getCustomerById(99));		
	}
	
	@Test
	@Order(3)
	void getCustomerByUsername() {
		Customer test = cserv.getCustomerByUsername("testUser");
		Assertions.assertEquals("testUser", test.getUsername());
	}
	
	@Test
	@Order(3)
	void getCustomerByUsernameNegative() {	
		Assertions.assertEquals(null, cserv.getCustomerByUsername("does not exist"));
	}
	
	@Test
	@Order(3)
	void getAllCustomersWithoutAccounts() {
		Set<Customer> test = cserv.getAllCustomersWithoutAccounts();
		Assertions.assertEquals(6, test.size());
	}

	@Test
	@Order(3)
	void getAllCustomersWithAccounts() {
		Set<Customer> test = cserv.getAllCustomers();
		Assertions.assertEquals(6, test.size());
		
		Iterator<Customer> customerIterator = test.iterator();
		Customer customer = customerIterator.next();
		int cId = customer.getcID();
		
		Assertions.assertEquals(cserv.getCustomerById(cId).getAccounts().size(),customer.getAccounts().size());
	}
	
	@Test
	@Order(3)
	void updateCustomer() {
		Customer customer = cserv.getCustomerById(2);
		customer.setUsername("UpdatedUsername");
		cserv.updateCustomer(customer);
		
		Assertions.assertEquals("UpdatedUsername", cserv.getCustomerById(2).getUsername());
	}
	
	@Test
	@Order(4)
	void deleteCustomerById() {
		boolean deleted = cserv.deleteCustomer(3);
		Assertions.assertTrue(deleted);
	}
	
	@Test
	@Order(4)
	void deleteCustomerByIdNegative() {
		boolean wasDeleted = cserv.deleteCustomer(99);
		Assertions.assertFalse(wasDeleted);
	}
	
	@Test
	@Order(4)
	void deleteCustomerByObject() {
		Customer test = cserv.getCustomerById(1);
		boolean wasDeleted = cserv.deleteCustomer(test);
		Assertions.assertTrue(wasDeleted);
	}
	
	@Test
	@Order(4)
	void deleteCustomerByObjectNegative() {
		Customer nonExistentCustomer = new Customer();
		boolean wasDeleted = cserv.deleteCustomer(nonExistentCustomer);
		Assertions.assertFalse(wasDeleted);
	}
	
	@Test
	@Order(3)
	void changeUsername() {
		Customer customer = cserv.getCustomerById(1);
		cserv.changeUsername(customer, "newUsername");
		Assertions.assertEquals("newUsername", customer.getUsername());
	}
	
	@Test
	@Order(3)
	void changePassword() {
		Customer customer = cserv.getCustomerById(1);
		cserv.changePassword(customer, "newpassword123");
		Assertions.assertEquals("newpassword123", customer.getPassword());
	}
}
