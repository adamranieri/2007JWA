package dev.kurt.daotests;

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

import dev.kurt.daos.CustomerDAO;
import dev.kurt.daos.CustomerDAOMaria;
import dev.kurt.entities.Customer;
import dev.kurt.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class CustomerDAOTests {

	private static CustomerDAO cusDao = CustomerDAOMaria.getCustomerDAOMaria();
	
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
	void createCustomer(){
		Customer customer = new Customer(0,"KurtDawiec","y0uW1llN3vErh4cKm3");
		cusDao.createCustomer(customer);
		Assertions.assertEquals(1, customer.getcId());
	}
	
	@Test
	@Order(2)
	void  getCustomerById(){
		Customer kurt = cusDao.getCustomerById(1);
		Assertions.assertEquals(1, kurt.getcId());
	}
	
	@Test
	@Order(3)
	void getCustomerByUsername(){
		Customer michael = new Customer(0,"MichaelBluth", "ILoveMySon1984"); 
		cusDao.createCustomer(michael);
		Assertions.assertNotEquals("KurtDawiec", michael.getUsername());
	}
	
	@Test
	@Order(4)
	void getAllCustomers(){
		Customer job = new Customer(0,"JobBluth","Alakazam69"); 
		cusDao.createCustomer(job);
		Set<Customer> theBluthsAndKurt = cusDao.getAllCustomers();
		Assertions.assertEquals(3, theBluthsAndKurt.size());
	}
	
	@Test
	@Order(5)
	void updateCustomer(){
		Customer kurt = cusDao.getCustomerById(1);
		kurt.setUsername("KurtBluth");
		cusDao.updateCustomer(kurt);
		Assertions.assertNotEquals("KurtDawiec", kurt.getUsername());
	}
	
	@Test
	@Order(6)
	void deleteCustomer(){
		boolean result = cusDao.deleteCustomer(1);
		Assertions.assertEquals(true,result);
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
