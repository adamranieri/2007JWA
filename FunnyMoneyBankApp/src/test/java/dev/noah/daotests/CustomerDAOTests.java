package dev.noah.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.noah.daos.CustomerDAO;
import dev.noah.daos.CustomerDAOLocal;
import dev.noah.entities.Customer;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class CustomerDAOTests {

	public static CustomerDAO cdao = CustomerDAOLocal.getCustomerDAO();

	// Positive Tests

	@Test
	@Order(1)
	void createCustomer() {
		Customer cus = new Customer(0, "TheLegend27", "SuperCoolPasswrod", null);
		cdao.createCustomer(cus);
		Assertions.assertNotEquals(0, cus.getcId());
													
	}

	@Test
	@Order(2)
	void getCustomerById() {

		Customer cusTest = cdao.getCustomerByCId(1);
		Assertions.assertEquals(1, cusTest.getcId());
	}

	@Test()
	@Order(3)
	void updateCustomer() {
		Customer cus = cdao.getCustomerByCId(1);
		cus.setUsername("TommyJohnson");
		cus = cdao.updateCustomer(cus);
		
		Assertions.assertEquals("TommyJohnson", cus.getUsername());
	}

	@Test()
	@Order(4)
	void getAllCustomers() {
		Customer cus = new Customer(0, "March111", "somekindofpassword", null);
		cdao.createCustomer(cus);
		cus = new Customer(0, "jeremy37", "passwordsLOL", null);
		cdao.createCustomer(cus);
		
		Set<Customer> allCustomers = cdao.getAllCustomers();
		//System.out.println("The amount of customers is " + allCustomers.size());
		Assertions.assertEquals(3, allCustomers.size());
	}
	
	@Test
	@Order(5)
	void deleteCustomerTest() {
		boolean cus = cdao.deleteCustomer(1);
		//System.out.println("The current customer table size is " + cdao.getAllCustomers().size());
		Assertions.assertEquals(true, cus);
	}
	
	// Negative Tests

	
	@Test
	@Order(6)
	void negDeleteCustomerTest() {
		boolean cus = cdao.deleteCustomer(6);
		System.out.println();
		Assertions.assertEquals(false, cus);
		
	}
	
	@Test
	@Order(7)
	void negGetCustomerByCId() {
		Customer cus = cdao.getCustomerByCId(17);
		Assertions.assertEquals(null, cus);
	}
	
}
