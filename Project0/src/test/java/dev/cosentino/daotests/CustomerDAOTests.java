package dev.cosentino.daotests;

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

import dev.cosentino.daos.AccountDAO;
import dev.cosentino.daos.AccountDAOMaria;
import dev.cosentino.daos.CustomerDAO;
import dev.cosentino.daos.CustomerDAOMaria;
import dev.cosentino.resources.Account;
import dev.cosentino.resources.Customer;
import dev.cosentino.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class CustomerDAOTests {

	public static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();
	public static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();
	
	
	@BeforeAll
	static void setUp() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_projectdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void createCustomer() {
		Customer cust = new Customer(0,"user","pass");
		cdao.createCustomer(cust);
		Assertions.assertEquals(1, cust.getCustomerId());
	}
	
	@Test
	@Order(2)
	void getCustomerById() {
		Customer cust = cdao.getCustomerById(1);
		Assertions.assertEquals(1, cust.getCustomerId());
	}
	
	@Test
	@Order(4)
	void getAllCustomers() {
		Customer cust = new Customer(0, "user2", "pass");
		cdao.createCustomer(cust);
		Set<Customer> customers = cdao.getAllCustomers();
		Assertions.assertEquals(2, customers.size());
	}
	
	@Test
	@Order(5)
	void updateCustomer() {
		Customer cust = cdao.getCustomerById(1);
		cust.setUsername("newUser");
		cust = cdao.updateCustomer(cust);
		Assertions.assertEquals("newUser", cust.getUsername());
	}
	
	@Test
	@Order(6)
	void deleteCustomer() {
		boolean result = cdao.deleteCustomer(1);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(7)
	void deleteCustomerNegative() {
		boolean result = cdao.deleteCustomer(22);
		Assertions.assertEquals(false, result);
	}
	
	@Test
	@Order(8)
	void getCustomerByUsername() {
		Customer cust = cdao.getCustomerByUsername("user2");
		Assertions.assertEquals("user2", cust.getUsername());
	}
	
	
	
	@AfterAll
	static void tearDown() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_projectdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
