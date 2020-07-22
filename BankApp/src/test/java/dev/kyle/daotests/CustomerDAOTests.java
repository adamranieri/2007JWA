package dev.kyle.daotests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.kyle.daos.CustomerDAO;
import dev.kyle.daos.CustomerDAOMaria;

import dev.kyle.entities.Customer;
import dev.kyle.utils.ConnectionUtil;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerDAOTests {
	
	//CRUD Operations Test Class for customer
	
	private static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();

	@BeforeAll
	static void setUp() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "CALL set_up_bank";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Create Test
	@Test
	@Order(1)
	void createCustomerTest() {
		Customer kyle = new Customer(0, "Kylerv", "Password");
		
		cdao.createCustomer(kyle);
		
		Assertions.assertNotEquals(0, kyle.getCid()); //cid = 1
	}
	
	// Read Test
	@Test
	@Order(3)
	void getCustomerByIdTest() {
		Customer kyle = cdao.getCustomerById(1);
		
		Assertions.assertEquals(1, kyle.getCid());
	}
	
	// Update test
	@Test
	@Order(3)
	void updateCustomerTest() {
		Customer kyle = cdao.getCustomerById(1);
		kyle.setUsername("KyleIsCool");
		cdao.updateCustomer(kyle);
		
		Assertions.assertEquals("KyleIsCool", kyle.getUsername());
	}
	
	// Delete Test
	@Test
	@Order(999)
	void deleteCustomer() {
		boolean res = cdao.deleteCustomer(1);
		Assertions.assertEquals(true, res);
	}
	
	@Test
	@Order(5)
	void deleteCustomerNegativeTest() {
		boolean res = cdao.deleteCustomer(10);
		Assertions.assertEquals(false, res);
	}
	
	@AfterAll
	static void tearDown() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "CALL tear_down_bank";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
