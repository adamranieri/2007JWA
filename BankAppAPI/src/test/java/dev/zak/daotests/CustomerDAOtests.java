package dev.zak.daotests;

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

import dev.zak.daos.CustomerDaoInterface;
import dev.zak.daos.CustomerDaoMaria;
import dev.zak.entities.Customer;
import dev.zak.utilities.ConnectionUtility;

//JUnit does not run your tests in order UNLESS you define it
@TestMethodOrder(OrderAnnotation.class) // necessary to order your tests
class CustomerDaotests {
	
	public static CustomerDaoInterface sDao =  CustomerDaoMaria.getCustomerDaoMaria();
	
	// will execute only once. Will be the first method called when we run these test cases
	@BeforeAll
	static void setUp() {

		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "CALL set_up_bank_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(1)
	void createCustomer() {
		Customer c = new Customer(0,"Jon","Jon_password");
		sDao.createCustomer(c);
		Assertions.assertNotEquals(0, c.getcId());
	}
	
	@Test
	@Order(2)
	void getCustomerById() {		
		Customer c = sDao.getCustomerById(1);
		Assertions.assertEquals(1, c.getcId());
	}
	
	@Test
	@Order(3)
	void getAllCustomers() {
		Customer c = new Customer(0,"Kevin","kevin_password");
		sDao.createCustomer(c);
		Set<Customer> Customers = sDao.getAllCustomers();
		Assertions.assertEquals(2,Customers.size());
	}
	
	@Test
	@Order(4)
	void updateCustomer() {
		Customer c = sDao.getCustomerById(1);
		c.setUsername("Zakaria");
		c = sDao.updateCustomer(c); //saves the changes to that Customer
		Assertions.assertEquals("Zakaria", c.getUsername());
		
	}
	
	@Test
	@Order(5)
	void deleteCustomer() {
		boolean result = sDao.deleteCustomer(1);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(6)
	void deleteCustomerNegative() {
		boolean result = sDao.deleteCustomer(10);
		Assertions.assertEquals(false, result);
	}
	
	/*@AfterAll
	static void tearDown() {
		
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "CALL tear_down_bank_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}*/

}


