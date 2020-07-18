package dev.alsabea.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.alsabea.connectionUtils.ConnectionUtils;
import dev.alsabea.daos.impl.CustomerDaoImpl;
import dev.alsabea.entities.Customer;


@TestMethodOrder(OrderAnnotation.class)
public class TestCustomerDao {
	
	CustomerDao custDao= CustomerDaoImpl.getCustomerDao();
	
	/*
	 * T create( T t) ;
	
		boolean delete(int id);
		
		T retrieveById(int id);
		
		T update ( T t);
	 */
	//List<Account> getAccounts ( int id);

	/*
	 * 	private int customerId;
	private String username;
	private String password;
	private List<Account> Accounts;
	 */
	/*
	 * call proj_0_db.procedure_setup_proj_0_db(); 

call proj_0_db.procedure_populate_tables;

	 */
	
//	@BeforeAll
//	static void setUpDB() {
//		String sql = "CALL proj_0_db.procedure_setup_proj_0_db;";
//		String sql2 = "CALL proj_0_db.procedure_populate_tables;";
//		Connection con= ConnectionUtils.getConnection();
//		try {
//			
//			CallableStatement cs=con.prepareCall(sql);
//			cs.execute();
//			
//			CallableStatement cs2 = con.prepareCall(sql2);
//			cs2.execute();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
	
	@Test
	void testRetrieveById() {
		
		Customer c = custDao.retrieveById(3);
		
		Assertions.assertEquals("rex", c.getUsername());
	}
	
	@Test
	void testRetrieveByUserName() {
		
		Customer c = custDao.retrieveByUsername("rex").get(0);
		
		Assertions.assertEquals(3, c.getCustomerId());
	}
	
	@Test
	void testRetrieveAll() {
		
		List<Customer> c = custDao.retrieveAll();
		
		Assertions.assertEquals(5, c.size());
	}
	
	
	@Test @Order(1)
	void createCustomer() {
		Customer c= new Customer();
		c.setCustomerId(10001);
		c.setUsername("testName");
		c.setPassword("testPass");
		Assertions.assertNotEquals( 0, custDao.create(c));

	}
	
	
	
	
	
	@Test  @Order(3)
	void testDeleteById() {
		
		Customer c= new Customer();
		c.setUsername("testUserToBeDeleted");
		c.setPassword("testPassToBeDeleted");
		
		int idInDb=custDao.create(c);
		
		Assertions.assertTrue(custDao.delete(idInDb));
	}
	
	
	@Test
	void testUpdate() {
		
		Customer t= new Customer();
		String user = "testUserToBeUpdated";
		String pass = "testPassToBeUpdated";
		t.setUsername(user);
		t.setPassword(pass);
		
		int id= custDao.create(t);
		
		Customer c = new Customer();
		c.setCustomerId(id);
		c.setUsername("testUpdatedName");
		c.setPassword("testUpdatedPassword");
		
		Assertions.assertTrue(custDao.update(c));
	}
	
	
	@AfterAll 
	//@Disabled
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
	

//	@AfterAll
//	static void tearDownDB() {
//		String sql = "CALL proj_0_db.procedure_tear_down_tables;";
//		Connection con= ConnectionUtils.getConnection();
//		try {
//			
//			CallableStatement cs=con.prepareCall(sql);
//			cs.execute();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
	
}





























