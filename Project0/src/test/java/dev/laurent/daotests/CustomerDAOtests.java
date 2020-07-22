package dev.laurent.daotests;

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

import dev.laurent.daos.CustomerDAO;
import dev.laurent.daos.CustomerDAOMaria;
import dev.laurent.entities.Customer;
import dev.laurent.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class CustomerDAOtests {
	
	public static CustomerDAO cdao =  CustomerDAOMaria.getCustomerDAOMaria();
	
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
	void createCustomer() {
		Customer testCustomer = new Customer(0,"username1", "password1");
		cdao.createCustomer(testCustomer);
		Assertions.assertEquals("username1", testCustomer.getUsername());
	}
	
	@Test
	@Order(2)
	void getCustomerById() {		
		Customer testCustomer = cdao.getCustomerById(1);
		Assertions.assertEquals(1, testCustomer.getcId());
	}
	
	@Test
	@Order(3)
	void getAllCustomers() {
		Customer testCustomer = new Customer(0,"username2", "password2");
		cdao.createCustomer(testCustomer);
		Set<Customer> customers = cdao.getAllCustomers();
		Assertions.assertEquals(2,customers.size());
	}
	
	@Test
	@Order(4)
	void updateCustomer() {
		Customer testCustomer = cdao.getCustomerById(1);
		testCustomer.setUsername("UpdatedUsername");
		testCustomer = cdao.updateCustomer(testCustomer); //saves the changes to that customer
		Assertions.assertEquals("UpdatedUsername", testCustomer.getUsername());	
	}
	
	@Test
	@Order(5)
	void deleteCustomer() {
		boolean result = cdao.deleteCustomer(2);
		Assertions.assertEquals(true, result);
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
