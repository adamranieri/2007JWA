package dev.rutan.daotests;

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

import dev.rutan.daos.CustomerDAO;
import dev.rutan.daos.CustomerDAOMaria;
import dev.rutan.entities.Customer;
import dev.rutan.utils.ConnectionUtil;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class CustomerDAOTests {

	public static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();
	
	@BeforeAll
	static void setUp() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_bank_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void createCustomer() {
		Customer customer = new Customer(0, "george", "password");
		cdao.createCustomer(customer);
		
		Assertions.assertNotEquals(0, customer.getcId());
	}
	
	@Test
	@Order(2)
	void getCustomerById() {
		Customer customer = cdao.getCustomerById(1);
		Assertions.assertEquals(1, customer.getcId());
	}

	@Test
	@Order(3) 
	void getAllCustomers() {
		Customer customer = new Customer(0, "norman", "safe");
		cdao.createCustomer(customer);
		Set<Customer> customers = cdao.getAllCustomers();
		Assertions.assertEquals(2, customers.size());
	}

	@Test
	@Order(4)
	void updateCustomer() {
		Customer customer = cdao.getCustomerById(1);
		customer.setPassword("better password");
		cdao.updateCustomer(customer);
		Assertions.assertEquals("better password", customer.getPassword());
	}
	
	@Test
	@Order(5)
	void deleteCustomer() {
		boolean result = cdao.deleteCustomer(1);
		Assertions.assertEquals(true, result);
	}
	
	@AfterAll
	static void endTest() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
