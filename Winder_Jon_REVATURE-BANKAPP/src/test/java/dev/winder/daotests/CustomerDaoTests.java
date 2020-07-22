package dev.winder.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


import dev.winder.daos.CustomerDAO;
import dev.winder.daos.CustomerDAOMaria;
import dev.winder.entities.Customer;

import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class CustomerDaoTests {
	
	
	//create the customerdaomara object with the singleton object thats calling upon the method that calls
	//on the private constructor to create cDAO if its null
	private static CustomerDAO cDao = CustomerDAOMaria.getCustomerDAOMaria(); 

	
	@Test
	@Order(1)
	public void createCustomer() {
		
		//customer id will be auto-incremented with an auto-increment BankDB INT VALUE
		Customer customer = new Customer(0,"Jon Winder","9126");
		cDao.createCustomer(customer);
		//make sure the id is auto-incremented
		Assertions.assertNotEquals(0, customer.getCustomerId());
		//make sure SQL AUTO INCREMENT is working
		Assertions.assertEquals(1,customer.getCustomerId());
		
	}//end J-Unit 1. TEST WAS CONFIRMED AS PASSED BY JONATHAN WINDER
	
	@Test
	@Order(2)
	public void getCustomerByCustomerId() {
		
		//since we created a customer in the previous test case, we can execute this test case.
		Customer customer = cDao.getCustomerByCustomerId(1);
		Assertions.assertNotNull(customer);
		Assertions.assertEquals(1, customer.getCustomerId());
		
	}//end J-Unit 2. TEST WAS CONFIRMED AS PASSED BY JONATHAN WINDER
	
	@Test
	@Order(3)
	public void getCustomerByUsername() {
		Customer customer = cDao.getCustomerByCustomerId(1);
		Customer testName = new Customer();
		testName = cDao.getCustomerByUsername(customer.getCustomerName());
		Assertions.assertEquals("Jon Winder", testName.getCustomerName());
		
		
		
	}
	
	
	@Test
	@Order(4)
	public void getAllCustomers() {
		
		Customer customerTwo = new Customer(0,"Joey S","8746");
		//since we know createCustomer works, add customerTwo to the BankDB
		cDao.createCustomer(customerTwo);
		
		Set<Customer>customers = cDao.getAllCustomers();
		
		Assertions.assertNotNull(customers.size());
		Assertions.assertEquals(2, customers.size());
		
	}//end J-Unit 3. TEST WAS CONFIRMED AS PASSED BY JONATHAN WINDER
	
	@Test
	@Order(5)
	//customer info can by updated by customer pin or name
	public void updateCustomerInfo() {
		
		Customer customer = cDao.getCustomerByCustomerId(1);
		customer.setCustomerName("Jonathan");
		customer = cDao.updateCustomer(customer);
		
		Assertions.assertEquals("Jonathan", customer.getCustomerName());
		
	}//end J-Unit 4. TEST WAS CONFIRMED AS PASSED BY JONATHAN WINDER.
	
	@Test
	@Order(6)
	public void deleteCustomer() {
		
		Boolean deletedCustomer = false;	
		deletedCustomer = cDao.deleteCustomerById(2);
		Assertions.assertNotEquals(false, deletedCustomer);
		Assertions.assertEquals(true, deletedCustomer);
	}//end J-Unit 5. TEST WAS CONFIRMED AS PASSED BY JONATHAN WINDER.
	


}