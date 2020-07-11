package dev.noah.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.noah.daos.CustomerDAO;
import dev.noah.daos.CustomerDAOLive;
import dev.noah.entities.Customer;
import dev.noah.exceptions.CustomerCreationException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class CustomerDAOTests {

	public static CustomerDAO cdao = CustomerDAOLive.getCustomerDAO();

	// Positive Tests

	@Test
	@Order(1)
	void createCustomer() throws CustomerCreationException {
		Customer cus = new Customer(0, "TheLegend27", "SuperCoolPasswrod", null);
		cdao.createCustomer(cus);
		Assertions.assertNotEquals(0, cus.getcId()); // Confirming an object is created and not at zero(all entities
														// start at zero until saved/created)
	}

	@Test
	@Order(3)
	void getCustomerById() throws CustomerCreationException {

		Customer cusTest = cdao.getCustomerByCId(1);
		Assertions.assertEquals(1, cusTest.getcId());
	}

	@Test()
	@Order(4)
	void renameCustomerUsername() {
		Customer cus = new Customer(0, "asdasdaas", "somekindofpassword", null);
		cus.setUsername("Thompson");
		Assertions.assertEquals("Thompson", cus.getUsername());
	}

	@Test()
	@Order(5)
	void getAllCustomers() throws CustomerCreationException {
		Customer cus = new Customer(0, "March111", "somekindofpassword", null);
		cdao.createCustomer(cus);
		cus = new Customer(0, "jeremy37", "passwordsLOL", null);
		cdao.createCustomer(cus);
		
		Set<Customer> allCustomers = cdao.getAllCustomers();
		System.out.println(allCustomers);
		System.out.println("The amount of customers is " + allCustomers.size());
		Assertions.assertEquals(3, allCustomers.size());
	}

	// Negative Tests

	@Test
	@Order(2)
	void negCreateCustomer() {

		Exception e = assertThrows(CustomerCreationException.class, () -> {
			Customer cus = new Customer(0, "tes", "pallly", null);
			cdao.createCustomer(cus);
		});

		Assertions.assertEquals("The username or password is less than 4 characters", e.getMessage());
	}
}
