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
import dev.noah.exceptions.CustomerCreationLengthException;
import dev.noah.services.CustomerService;
import dev.noah.services.CustomerServiceImpl;

import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceTests {

	public static CustomerService cserv = new CustomerServiceImpl();
	
	
	//Positive Tests
	
	@Test
	@Order(1)
	void createCustomer() throws CustomerCreationLengthException {
		Customer cus = cserv.createCustomer("Scary Terry","SomeKindOfPassword");
		Assertions.assertEquals(1, cus.getcId());
	}
	
	@Test
	@Order(2)
	void getAllCustomers() throws CustomerCreationLengthException {
		Customer cus = cserv.createCustomer("TheNewGuy", "SomeWhatOfAPassword");
		cus = cserv.createCustomer("SomePerson", "The real password");
		cus = cserv.createCustomer("ExtraPerson", "The real password");
		cus = cserv.createCustomer("CreativePerson", "The real password");
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
	void changeCustomerUsername() {
		Customer cus = cserv.getCustomerByCId(1);
		cserv.changeCustomerUsernameById(cus.getcId(), "SummerSalt");
		
		Assertions.assertEquals("SummerSalt", cus.getUsername());
	}

	@Test
	@Order(5)
	void changeCustomerPassword() {
		Customer cus = cserv.getCustomerByCId(1);
		cserv.changeCustomerPasswordById(cus.getcId(), "Tastey");
		Assertions.assertEquals("Tastey", cus.getPassword());
	}
	
	@Test
	@Order(6)
	void deleteCustomerByCId() {
		boolean result = cserv.deleteCustomerByCId(1);
		Assertions.assertEquals(true, result);
	}
	
	//Negative Tests
	
	@Test
	@Order(7)
	void negCreateCustomer() {

		Exception e = assertThrows(CustomerCreationLengthException.class, () -> {
			Customer cus = cserv.createCustomer( "tes", "pallly");
		});

		Assertions.assertEquals("The username or password is less than 4 characters", e.getMessage());
	}
}
