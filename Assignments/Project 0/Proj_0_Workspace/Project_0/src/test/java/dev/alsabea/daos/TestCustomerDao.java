package dev.alsabea.daos;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.alsabea.daos.impl.CustomerDaoImpl;
import dev.alsabea.entities.Account;
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
	@Test @Order(1)
	void createCustomer() {
		Customer c= new Customer();
		c.setCustomerId(10001);
		c.setUsername("testName");
		c.setPassword("testPass");
		
		Assertions.assertTrue(custDao.create(c));

	}
	
	@Test	@Order(2)
	void testRetrieveById() {
		
		Customer c = custDao.retrieveById(3);
		
		Assertions.assertEquals("rex", c.getUsername());
	}
	
	
	
	@Test  @Order(3)
	void testDeleteById() {
		
		Customer c= new Customer();
		c.setUsername("userToBeDeleted");
		c.setPassword("passToBeDeleted");
		custDao.create(c);
		int idInDb=custDao.getIdByUsernameAndPassword(c.getUsername(), c.getPassword());
		
		Assertions.assertTrue(custDao.delete(idInDb));
	}
	
	
	@Test
	void testUpdate() {
		
		Customer t= new Customer();
		String user = "userToBeUpdated";
		String pass = "passToBeUpdated";
		t.setUsername(user);
		t.setPassword(pass);
		custDao.create(t);
		int id= custDao.getIdByUsernameAndPassword(user, pass);
		
		Customer c = new Customer();
		c.setCustomerId(id);
		c.setUsername("updatedTestName");
		c.setPassword("updatedTestPassword");
		
		Assertions.assertTrue(custDao.update(c));
	}
	
	@Test
	void testGetAccounts() {
		List<Account> accountsList=new ArrayList<>();
		
		accountsList= custDao.getAccounts( /*customer_id*/ 1);
		
		//System.out.println(accountsList.get(0).getBalance());
		//System.out.println(accountsList.get(1).getBalance());
		
		Assertions.assertEquals(2, accountsList.size());
		
		
	}
	
	
}





























