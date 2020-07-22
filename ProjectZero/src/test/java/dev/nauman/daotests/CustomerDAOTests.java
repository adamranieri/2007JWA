package dev.nauman.daotests;

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

import dev.nauma.utils.ConnectionUtil;
import dev.nauman.daos.CustomerDAO;
import dev.nauman.daos.CustomerDAOImpl;
import dev.nauman.entities.Customer;

@TestMethodOrder(OrderAnnotation.class)
class CustomerDAOTests {

	private static CustomerDAO cdao = CustomerDAOImpl.getCustomerDAOImpl();
	
	@BeforeAll
	static void setUp() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "CALL set_up_projectzerodb()";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void createCustomerTest1() {
		Customer customer1 = new Customer(0, "frodo99","ohSam333");
		Customer customer2 = new Customer(0, "sam101","gafferRULES");
		Customer customer3 = new Customer(0, "stewardofgondor","farmerMaggot13");
		Customer customer4 = new Customer(0, "knightofrohan","IAMTALLERTHANPIPPIN");
		
		cdao.createCustomer(customer1);
		cdao.createCustomer(customer2);
		cdao.createCustomer(customer3);
		cdao.createCustomer(customer4);
		
		Assertions.assertNotEquals(0, customer1.getcId());
		Assertions.assertNotEquals(0, customer2.getcId());
		Assertions.assertNotEquals(0, customer3.getcId());
		Assertions.assertNotEquals(0, customer4.getcId());
	}
	@Test
	@Order(2)
	void getCustomerByIdTest() {
		Customer customer = cdao.getCustomerById(1);
		
		Assertions.assertEquals(1, customer.getcId());
	}
	@Test
	@Order(3)
	void getAllCustomerTest() {
		Set<Customer> customers = cdao.getAllCustomers();
		
		Assertions.assertEquals(4, customers.size());
	}
	@Test
	@Order(4)
	void updateCustomerTest() {
		Customer cs = new Customer(1, "Mr. UnderHill", "ohSam333");
		cs= cdao.updateCustomer(cs);
		
		Assertions.assertEquals("Mr. UnderHill", cs.getUsername());
	}
	@Test
	@Order(5)
	void deleteCustomerByIdPositiveTest() {
		boolean result = cdao.deleteCustomerById(2);
		
		Assertions.assertEquals(true, result);
	}
	@Test
	@Order(6)
	void deleteCustomerByIdNegativeTest() {
		boolean result = cdao.deleteCustomerById(14);
		
		Assertions.assertEquals(false, result);
	}
	
	@AfterAll
	static void tearDown() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_projectzerodb()";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}