package dev.schneider.daotests;

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

import dev.schneider.dao.AccountDAO;
import dev.schneider.dao.CustomerDAO;
import dev.schneider.dao.CustomerDAOImpl;
import dev.schneider.entities.Account;
import dev.schneider.entities.Customer;
import dev.schneider.utils.ConnectionUtil;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class CustomerDAOTests {

public static CustomerDAO cdao = CustomerDAOImpl.getCustomerDAO();
	
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
	//create
	void createCustomer() {
		Customer customer = new Customer(0, "usernameguy", "passguy"); 
		cdao.createCustomer(customer);
		Assertions.assertNotEquals(0, customer.getcID());
		Assertions.assertEquals(1, customer.getcID());
	}
	
	//read
	
	@Test
	@Order(2)
	void getCustomers() {
		Set<Customer> customers = cdao.getAllCustomer();
		Assertions.assertNotEquals(0, customers.size());
		Assertions.assertEquals(1, customers.size());
	}
	
	@Test
	@Order(3)
	void getCustomerByID()  {
		Customer customer = cdao.getCustomerByID(1);
		Assertions.assertNotEquals(0, customer.getcID());
		Assertions.assertEquals("passguy", customer.getPassword());
	}
	
	@Test
	@Order(4)
	void getCustomerByUsername()  {
		Customer customer = cdao.getCustomerByUsername("usernameguy");
		Assertions.assertEquals("passguy", customer.getPassword());
	}
	
	
	//update
	
	@Test
	@Order(5)
	void changeCustomerUsername()  {
		Customer customer = cdao.getCustomerByID(1);
		customer.setUsername("dsdfsf");
		customer = cdao.updateCustomer(customer); 
		Assertions.assertEquals("dsdfsf", customer.getUsername());
	}
	
	@Test
	@Order(6)
	void changeCustomerPassword() {
		Customer customer = cdao.getCustomerByID(1);
		customer.setPassword("dsdfsf");
		customer = cdao.updateCustomer(customer); 
		Assertions.assertEquals("dsdfsf", customer.getPassword());
	}
	
	
	
	@Test()
	@Order(7)
	void deleteCustomer() {
		boolean result = cdao.deleteCustomer(1);
		Assertions.assertEquals(true, result);
	}
	
	@Test()
	@Order(8)
	void deleteCustomerBad() {
		boolean result = cdao.deleteCustomer(10);
		Assertions.assertEquals(false, result);
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
