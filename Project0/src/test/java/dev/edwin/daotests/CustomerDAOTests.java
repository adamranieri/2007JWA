package dev.edwin.daotests;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.edwin.daos.CustomerDAO;
import dev.edwin.daos.CustomerDAOImp;
import dev.edwin.entities.Customer;
import dev.edwin.utils.ConnectionUtil;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;


@TestMethodOrder(OrderAnnotation.class)
class CustomerDAOTests {
	
	private static CustomerDAO cDao = CustomerDAOImp.getCustomerDAO();


	@Test
	@Order(1)
	void testCreateCustomer() {
		Customer customer = new Customer(0, "Sir John", "password", null);
		Customer returned = cDao.createCustomer(customer);
		Assertions.assertNotEquals(0,returned.getcId());
	}
	
	@Test
	@Order(2)
	void testGetCustomerById() {
		Customer customer = null;
		customer = cDao.getCustomerById(1);
		Assertions.assertEquals("Steve A", customer.getUsername());
	}
	
	@Test
	@Order(3)
	void testGetAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		customers = cDao.getCustomers();
		
		Assertions.assertEquals(9, customers.size());
	}
	

	@Test
	@Order(4)
	void tesUpdateCustomer() 
	{
		Customer customer = cDao.getCustomerById(1);
		customer.setPassword("newPassword");
		Customer returned = cDao.updateCustomer(customer);
		
		Assertions.assertNotEquals("password", returned.getPassword());
	}
	
	@Test
	@Order(5)
	void testDeleteCustomer() 
	{
		Customer customer = cDao.getCustomerById(6);
		boolean returned = cDao.deleteCustomer(customer.getcId());
		
		Assertions.assertEquals(true, returned);
	}
	
	@Test
	@Order(6)
	void testGetCustomerByName()
	{
		List<Customer> returned = cDao.getCustomerByUsername("john");
		System.out.println(returned);
		Assertions.assertEquals(2, returned.size());
	}
	
	@AfterAll
	static void tearDown() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_project";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
