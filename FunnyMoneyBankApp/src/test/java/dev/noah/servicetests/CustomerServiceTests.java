package dev.noah.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


import dev.noah.entities.Customer;
import dev.noah.services.CustomerService;
import dev.noah.services.CustomerServiceImpl;

import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceTests {

	public static CustomerService cserv = new CustomerServiceImpl();
	
	
	//Positive Tests
	
	@Test
	@Order(1)
	void createCustomer() {
		Customer cus = new Customer();
		cserv.createCustomer(cus);
		Assertions.assertEquals(1, cus.getcId());
	}
	
	@Test
	@Order(2)
	void getAllCustomers() {
		Customer cus = new Customer();
		cserv.createCustomer(cus);
		cus = new Customer();
		cserv.createCustomer(cus);
		cus = new Customer();
		cserv.createCustomer(cus);
		cus = new Customer();
		cserv.createCustomer(cus);
		Set<Customer> getCustomers = cserv.getAllCustomers();
		Assertions.assertEquals(5, getCustomers.size());
	}
	
	@Test
	@Order(3)
	void getCustomer() {
		Customer cus = cserv.getCustomerByCId(1);
		Assertions.assertEquals(1,cus.getcId());
	}

	
	@Test
	@Order(4)
	void updateCustomer() {
		Customer cus = cserv.getCustomerByCId(1);
		cus.setUsername("SummerSalt");
		cserv.updateCustomer(cus);
		
		Assertions.assertEquals("SummerSalt", cserv.getCustomerByCId(1).getUsername());
	}


	
	@Test
	@Order(6)
	void deleteCustomerByCId() {
		boolean result = cserv.deleteCustomerByCId(1);
		Assertions.assertEquals(true, result);
	}
}
