package dev.schneider.services;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.schneider.entities.Customer;
import dev.schneider.utils.ConnectionUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


class CustomerServiceTests {

	private static CustomerService cserv = new CustomerServiceImpl();
	
	
	@BeforeAll
	static void setup() {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void createCustomer() {
		Customer customer = new Customer(0, "myusername", "mypass");
		cserv.createCustomer(customer);
		Assertions.assertNotEquals(0, customer.getcID());
	}
	
	@Test
	@Order(2)
	void getAllCustomers() {
		Set<Customer> customers = cserv.getAllCustomers();
		Assertions.assertNotEquals(0, customers.size());
		Assertions.assertEquals(1, customers.size());
	}

	@Test
	@Order(3)
	void getCustomerByID()  {
		Customer customer = cserv.getCustomerByID(1);
		Assertions.assertEquals("myusername", customer.getUsername());
	}
	
	@Test
	@Order(4)
	void getCustomerByUsername()  {
		Customer customer = cserv.getCustomerbyUsername("myusername");
		Assertions.assertEquals("mypass", customer.getPassword());
	}
	
	@Test
	@Order(5)
	void updateCustomer(){
		Customer customer = cserv.getCustomerByID(1);	
		customer.setPassword("mynewpass");
		cserv.updateCustomer(customer);
		Assertions.assertEquals("mynewpass", customer.getPassword());
		}
	
	
	@Test
	@Order(6)
	void deleteCustomernegative() {
		boolean result = cserv.deleteCustomer(13453);
		Assertions.assertEquals(false, result);
		}
	
	@Test
	@Order(7)
	void deleteCustomer() {
		boolean result = cserv.deleteCustomer(1);
		Assertions.assertEquals(true, result);
		}
	
	@AfterAll
	static void tearDown() {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
