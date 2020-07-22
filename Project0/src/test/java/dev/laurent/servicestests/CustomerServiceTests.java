package dev.laurent.servicestests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import dev.laurent.entities.Customer;
import dev.laurent.services.CustomerService;
import dev.laurent.services.CustomerServiceImpl;
import dev.laurent.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceTests {
	
	private static CustomerService cserv = new CustomerServiceImpl();

	@BeforeAll
	static void setUp() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_bankdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void addCustomer() {		
		Customer testCustomer = new Customer(0,"username1", "password1");
		cserv.addCustomer(testCustomer);
		Assertions.assertNotEquals(0, testCustomer.getcId());		
	}
	
	@Order(2)
	@Test
	void getCustomerById() {
		Customer testCustomer = cserv.getCustomerById(1);
		Assertions.assertEquals(1, testCustomer.getcId());
	}
	
	@Order(3)
	@Test
	void updateCustomer() {
		Customer testCustomer = cserv.getCustomerById(1);
		testCustomer.setUsername("UpdatedUsername");
		cserv.updateCustomer(testCustomer);
		Assertions.assertEquals("UpdatedUsername", testCustomer.getUsername());	
	}
	
	@Order(4)
	@Test
	void deleteCustomerById() {
		Customer testCustomer = cserv.getCustomerById(1);
		boolean result = cserv.deleteCustomerById(testCustomer.getcId());
		Assertions.assertEquals(true, result);
	}
		
	@Order(5)
	@Test
	void getAllCustomers() {
		Customer testCustomer = new Customer(0,"username2", "password2");
		cserv.addCustomer(testCustomer);
		Set<Customer> customers = cserv.getAllCustomers();
		Assertions.assertEquals(1,customers.size());
	}
	
	@AfterAll
	static void tearDown() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_bankdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
}
