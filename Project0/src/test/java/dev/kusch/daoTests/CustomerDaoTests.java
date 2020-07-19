package dev.kusch.daoTests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.kusch.daos.CustomerDAO;
import dev.kusch.daos.CustomerDAOMaria;
import dev.kusch.entities.Customer;
import dev.kusch.util.TestUtil;
import dev.kusch.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)// necessary to run tests in order 
class CustomerDaoTests {
	
	public static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();

	@BeforeAll
	static void startTests() {
		TestUtil.setUpDb();
	}
	
	@AfterAll
	static void endTests() {
		TestUtil.tearDownDb();
	}
	
	@Test
	@Order(1)
	void testGetAllCustomersNegative() {
		Set<Customer> base = new HashSet<Customer>();
		Set<Customer> customers = cdao.getAllCustomers();
		Assertions.assertEquals(base, customers);
	}
	
	@Test
	@Order(2)
	void testCreateBasic() {
		Customer testCust = new Customer(0, "John Smith", "password123");
		cdao.createCustomer(testCust);
		Assertions.assertNotEquals(0, testCust.getcId());
	}
	
	@Test
	@Order(3)
	void getCustomerByIdBasic() {
		Customer getCust = cdao.getCustomerById(1);
		Assertions.assertEquals(1, getCust.getcId());
	}
	
	@Test
	@Order(4)
	void testGetCustomerByIdNegative() {
		Customer customer = cdao.getCustomerById(10000000);
		Assertions.assertNull(customer);
	}
	
	@Test
	@Order(5)
	void getCustomerByUserBasic() {
		Set<Customer> getCust = cdao.getCustomerByUser("John Smith");
		Assertions.assertEquals(1, getCust.iterator().next().getcId());
	}
	
	@Test
	@Order(6)
	void testGetCustomerByUserNegative() {
		Set<Customer> customer = cdao.getCustomerByUser("A cow jumped over the moon");
		Assertions.assertFalse(customer.iterator().hasNext());
	}
	
	@Test
	@Order(7)
	void getAllCustomers() {
		Customer testCust2 = new Customer(0, "John Snow", "mysecurepassword");
		cdao.createCustomer(testCust2);
		
		Set<Customer> fullCust = cdao.getAllCustomers();
		Assertions.assertEquals(2, fullCust.size());	
	}
	
	@Test
	@Order(8)
	void updateCustomerBasic() {
		Customer smith = cdao.getCustomerById(1);
		smith.setPassword("betterPassw0rd");
		smith = cdao.updateCustomer(smith);
		Assertions.assertEquals("betterPassw0rd", smith.getPassword());
	}
	
	@Test
	@Order(9)
	void testUpdateCustomerNegative() {
		Customer updater = new Customer(4000, "John Snow", "myREALLYsecurepassword");
		updater = cdao.updateCustomer(updater);
		Assertions.assertNull(updater);
	}
	
	@Test
	@Order(10)
	void deleteCustomer() {
		boolean result = cdao.deleteCustomer(2);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(11)
	void deleteCustomerNegative() {
		boolean result = cdao.deleteCustomer(20);
		Assertions.assertEquals(false, result);
	}
}
