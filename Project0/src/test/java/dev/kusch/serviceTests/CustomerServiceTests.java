package dev.kusch.serviceTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.kusch.entities.Customer;
import dev.kusch.services.CustomerServices;
import dev.kusch.services.CustomerServicesImpl;
import dev.kusch.util.TestUtil;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceTests {

	private static CustomerServices cserv = new CustomerServicesImpl();
	
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
	void testAddCustomerBasic() {
		Customer customer = new Customer(0, "Mr.Cool123", "C00lGuyz45");
		cserv.addCustomer(customer);
		Assertions.assertNotEquals(0, customer.getcId());
				
	}
	
	@Test
	@Order(2)
	void testGetAllCustomers() {
		Customer customer = new Customer(0, "PuppLover87", "DebbieDaschound234");
		cserv.addCustomer(customer);
		Set<Customer> customers = cserv.getAllCustomers();
		Assertions.assertEquals(2, customers.size());
	}
	
	@Test
	@Order(3)
	void testGetCustomerById() {
		Customer customer = cserv.getCustomer(2);
		Assertions.assertEquals("PuppLover87", customer.getUsername());
	}
	
	@Test
	@Order(4)
	void testGetCustomerByBadId() {
		Customer customer = cserv.getCustomer(10000000);
		Assertions.assertNull(customer);
	}
	
	@Test
	@Order(5)
	void testGetCustomerByUser() {
		Set<Customer> customer = cserv.getCustomer("PuppLover87");
		Assertions.assertEquals(2, customer.iterator().next().getcId());
	}
	
	@Test
	@Order(6)
	void testGetCustomerByBadUser() {
		Set<Customer> customer = cserv.getCustomer("EXPLICITNAME");
		Assertions.assertFalse(customer.iterator().hasNext());
	}
	
	@Test
	@Order(7)
	void testUpdateCustomerBasic() {
		Customer customer = new Customer(1, "Mr.Cool123", "don'tLookAt3xpl0si0n$");
		cserv.updateCustomer(customer);
		Assertions.assertEquals("don'tLookAt3xpl0si0n$", customer.getPassword());
	}
	
	@Test
	@Order(8)
	void testUpdateFakeCustomer() {
		Customer customer = new Customer(50, "Mr.Drool123", "don'tLookAt3xpl0si0n$");
		customer = cserv.updateCustomer(customer);
		Assertions.assertNull(customer);
	}
	
	@Test
	@Order(9)
	void testDeleteCustomerBasic() {
		Customer customer = new Customer(2, "PuppLover87", "DebbieDaschound234");
		boolean result = cserv.deleteCustomer(customer);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(10)
	void testDeleteCustomerNegative() {
		Customer customer = new Customer(17, "PuppH8r87", "DebbieDoxenEvil");
		boolean result = cserv.deleteCustomer(customer);
		Assertions.assertEquals(false, result);
	}

}
