package dev.kurt.servicetests;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.kurt.entities.Customer;
import dev.kurt.services.CustomerService;
import dev.kurt.services.CustomerServiceImpl;
import dev.kurt.utils.ConnectionUtil;


@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceTests {
	
	private static CustomerService cusServ = new CustomerServiceImpl();
	
	@BeforeAll 
	static void setUp() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_kurtbankdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Order(1)
	void createCustomer() {
		Customer customer = new Customer(0, "Dothraki1337", "prettyladywhitehair");
		cusServ.createCustomer(customer);
		Assertions.assertEquals(1, customer.getcId());
		
	}
	
	@Test
	@Order(2)
	void getCustomerById() {
		Customer customer = new Customer(0, "Joeffrey", "badboi");
		cusServ.createCustomer(customer);
		Customer doth = cusServ.getCustomerById(1);
		Assertions.assertNotEquals("Joeffrey", doth.getUsername());
	}
	
	@Test
	@Order(3)
	void getCustomerByUsername() {
		Customer customer = cusServ.getCustomerByUsername("Joeffrey");
		Assertions.assertEquals(2,customer.getcId());
	}
	
	@Test
	@Order(4)
	void getAllCustomers() {
		Set<Customer> customers = cusServ.getAllCustomers();
		Assertions.assertEquals(2,customers.size());
	}
	
	@Test
	@Order(5)
	void updateCustomer() {
		Customer doth = cusServ.getCustomerById(1);
		doth.setPassword("moreSecurePassword1234");
		cusServ.updateCustomer(doth);
		Assertions.assertNotEquals("prettyladywhitehair",doth.getPassword());
	}
	
	@Test
	@Order(6)
	void deleteCustomerById() {
		boolean result = cusServ.deleteCustomerById(1);
		Assertions.assertEquals(true, result);
	}
	
	
	@AfterAll
	static void tearDown() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_kurtbankdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
