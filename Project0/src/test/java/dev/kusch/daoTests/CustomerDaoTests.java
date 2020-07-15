package dev.kusch.daoTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import dev.kusch.daos.CustomerDAO;
import dev.kusch.daos.CustomerDAOLocal;
import dev.kusch.entities.Customer;

class CustomerDaoTests {
	
	public static CustomerDAO cdao = CustomerDAOLocal.getCustomerDAO();

	@Test
	@Order(1)
	void testCreateBasic() {
		Customer testCust = new Customer("John Smith", "password123", 0);
		cdao.createCustomer(testCust);
		Assertions.assertNotEquals(0, testCust.getcId());
	}
	
	@Test
	@Order(2)
	void getCustomerByIdBasic() {
		Customer getCust = cdao.getCustomerById(1);
		Assertions.assertEquals(1, getCust.getcId());
	}
	
	@Test
	@Order(3)
	void getAllCustomers() {
		Customer testCust2 = new Customer("John Snow", "mysecurepassword", 0);
		cdao.createCustomer(testCust2);
		
		Set<Customer> fullCust = cdao.getAllCustomers();
		Assertions.assertEquals(2, fullCust.size());	
	}
	
	@Test
	@Order(4)
	void updateCustomerBasic() {
		Customer smith = cdao.getCustomerById(1);
		smith.setPassword("betterPassw0rd");
		smith = cdao.updateCustomer(smith);
		Assertions.assertEquals("betterPassw0rd", smith.getPassword());
	}
	
	@Test
	@Order(5)
	void deleteCustomer() {
		boolean result = cdao.deleteCustomer(2);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(6)
	void deleteCustomerNegative() {
		boolean result = cdao.deleteCustomer(20);
		Assertions.assertEquals(false, result);
	}
}
