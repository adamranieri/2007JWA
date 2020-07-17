package dev.alsabea.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.alsabea.connectionUtils.ConnectionUtils;
import dev.alsabea.entities.Account;
import dev.alsabea.entities.Customer;
import dev.alsabea.services.impl.CustomerServicesImpl;


@TestMethodOrder(OrderAnnotation.class)
class TestCustomerServices {

	private static CustomerServices cServices = CustomerServicesImpl.getCustomerServicesInstance();
	

	@Test @Order(1)
	void testRetrieveById() {
		
		Customer c = cServices.getCustomerById(3);
		
		Assertions.assertEquals("rex", c.getUsername());
	}
	
	@Test @Order(2)
	void TestAddCustomerService() {
		Customer c= new Customer();
		c.setCustomerId(10001);
		c.setUsername("testName");
		c.setPassword("testPass");
		Assertions.assertNotEquals( 0,cServices.addCustomer(c));
		
	}
	
	
	@Test  
	void testDeleteById() {
		
		Customer c= new Customer();
		c.setUsername("testUserToBeDeleted");
		c.setPassword("testPassToBeDeleted");
		
		int idInDb=cServices.addCustomer(c);
		
		Assertions.assertTrue(cServices.deleteCustomer(idInDb));
	}
	
	
	@Test
	void testUpdate() {
		
		Customer t= new Customer();
		String user = "testUserToBeUpdated";
		String pass = "testPassToBeUpdated";
		t.setUsername(user);
		t.setPassword(pass);
		
		int id= cServices.addCustomer(t);
		
		Customer c = new Customer();
		c.setCustomerId(id);
		c.setUsername("testUpdatedName");
		c.setPassword("testUpdatedPassword");
		
		Assertions.assertTrue(cServices.updateCustomer(c));
	}
	
	@Test
	void testGetAccounts() {
		List<Account> accountsList=new ArrayList<>();
		
		accountsList= cServices.getCustomerAccounts( /*customer_id*/ 1);
		
		Assertions.assertEquals(2, accountsList.size());
		
		
	}
	
	
	@AfterAll
	static void removeTestingStuff() {
		String sql = "delete from customer where username LIKE 'test%';";
		Connection con= ConnectionUtils.getConnection();
		try {
			
			PreparedStatement ps=con.prepareStatement(sql);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
