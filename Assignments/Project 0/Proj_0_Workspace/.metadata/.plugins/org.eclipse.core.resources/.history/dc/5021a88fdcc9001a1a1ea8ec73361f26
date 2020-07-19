package dev.alsabea.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.alsabea.connectionUtils.ConnectionUtils;
import dev.alsabea.entities.Account;
import dev.alsabea.entities.Customer;
import dev.alsabea.services.impl.CustomerServicesImpl;



class TestCustomerServices {

	private static CustomerServices cServices = CustomerServicesImpl.getCustomerServicesInstance();
	
	
	@Test
	void testRetrieveById() {
		
		Customer c = cServices.retrieveById(3);
		
		Assertions.assertEquals(3, c.getCustomerId());
	}

	@Test
	void testRetrieveByUsername() {
		
		Customer c = cServices.retrieveByUsername("rex").get(0);
		
		Assertions.assertEquals(3, c.getCustomerId());
	}
	
	
	@Test
	void testRetrieveAll() {
		
		List<Customer> c = cServices.retrieveAll();
		
		Assertions.assertEquals(5, c.size());
	}
	
	@Test 
	void TestAddCustomerService() {
		Customer c= new Customer();
		c.setCustomerId(10001);
		c.setUsername("testName");
		c.setPassword("testPass");
		Assertions.assertNotEquals( 0,cServices.create(c));
		
	}
	
	
	@Test  
	void testDeleteById() {
		
		Customer c= new Customer();
		c.setUsername("testUserToBeDeleted");
		c.setPassword("testPassToBeDeleted");
		
		int idInDb=cServices.create(c);
		
		Assertions.assertTrue(cServices.delete(idInDb));
	}
	
	
	@Test
	void testUpdate() {
		
		Customer t= new Customer();
		String user = "testUserToBeUpdated";
		String pass = "testPassToBeUpdated";
		t.setUsername(user);
		t.setPassword(pass);
		
		int id= cServices.create(t);
		
		Customer c = new Customer();
		c.setCustomerId(id);
		c.setUsername("testUpdatedName");
		c.setPassword("testUpdatedPassword");
		
		Assertions.assertTrue(cServices.update(c));
	}
	
	@Test
	void testGetAccounts() {
		List<Account> accountsList=new ArrayList<>();
		
		accountsList= cServices.getCustomerAccounts( /*customer_id*/ 1);
		
		Assertions.assertEquals(2, accountsList.size());
		
		
	}
	
	
	@AfterAll
	static void removeTestingStuff() {
		String sql = "delete from proj_0_db.customer where username LIKE 'test%';";
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
