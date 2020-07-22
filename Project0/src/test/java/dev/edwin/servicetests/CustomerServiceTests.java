package dev.edwin.servicetests;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import dev.edwin.entities.Customer;
import dev.edwin.services.CustomerService;
import dev.edwin.services.CustomerServiceImp;
import dev.edwin.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceTests {
	
//	Methods to test:
//	Customer signUpNewCustomer(Customer customer);
//	Customer getCustomerById(int cId);
//	List<Customer> getAllCustomers();
//	List<Customer> searchByUsername(String username);
//	Customer updateCustomer(Customer customer);
//	boolean deleteCustomer(int cId);
//	boolean deleteCustomer(Customer customer);

	private static CustomerService cserv = CustomerServiceImp.getCustomerService();
	
	
	@Test
	@Order(1)
	void testSignUpNewCustomer() {
		Customer customer = new Customer(0,"Raul Papi", "defaultPass", null);
		
		Assertions.assertNotEquals(0,cserv.signUpNewCustomer(customer));
	}
	
	@Test
	@Order(2)
	void testGetCustomerById()
	{
		Assertions.assertEquals("Steve A", (cserv.getCustomerById(1)).getUsername());
	}
	
	@Test
	@Order(3)
	void testGetAllCustomers()
	{
		Assertions.assertNotEquals(0, (cserv.getAllCustomers()).size());
	}
	
	@Test
	@Order(4)
	void testSearchByUsername()
	{
		Assertions.assertNotEquals(0, (cserv.searchByUsername("john")).size());
	}
	
	@Test
	@Order(5)
	void testUpdateCustomer()
	{
		Customer customer = cserv.getCustomerById(7);
		customer.setPassword("newPass");
		Assertions.assertNotEquals("defaultPass", (cserv.updateCustomer(customer)).getPassword());
	}
	
	@Test
	@Order(6)
	void testDeleteCustomerById()
	{
		Assertions.assertEquals(true,cserv.deleteCustomer(2));
	}
	
	@Test
	@Order(7)
	void testDeleteCustomerByCustomerObject()
	{
		Assertions.assertEquals(true, cserv.deleteCustomer(cserv.getCustomerById(1)));
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
