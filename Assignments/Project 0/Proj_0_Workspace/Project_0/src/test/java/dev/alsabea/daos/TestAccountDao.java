package dev.alsabea.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import dev.alsabea.connectionUtils.ConnectionUtils;
import dev.alsabea.daos.impl.AccountDaoImpl;
import dev.alsabea.entities.Account;


public class TestAccountDao {

	AccountDao aDao= AccountDaoImpl.getAccountDao();
	
	@Test @Order(1)
	void testCreateAccount() {
		Account a= new Account();
		a.setCustomerId(5);
		a.setAccountName("test_create_name");
		a.setBalance(800);
		
		Assertions.assertNotEquals(-1,aDao.create(a));
		

	}
	
	@Test	@Order(2)
	void testRetrieveById() {
		Account a= aDao.retrieveById(100);
		Assertions.assertEquals("savings", a.getAccountName());
		
	}
	
	
	
	@Test  @Order(3)
	void testDeleteById() {
		Account a= new Account();
		a.setCustomerId(5);
		a.setAccountName("test_to_be_deleted");
		a.setBalance(800);

		int id=aDao.create(a);
		
		Assertions.assertTrue(aDao.delete(id));
		
		
	}
	
	
	@Test
	void testUpdate() {
		Account a= new Account();
		a.setCustomerId(5);
		a.setAccountName("testToBeUpdatedName");
		a.setBalance(800);
		
		int id= aDao.create(a);
		Account aUpdated= new Account();
		aUpdated.setAccountId(id);
		aUpdated.setCustomerId(5);
		aUpdated.setAccountName("testUpdatedName");
		aUpdated.setBalance(1000);
		
		Assertions.assertTrue(aDao.update(aUpdated));
		
	}
	
	
	

	@Test
	void testGetAccounts() {
		List<Account> accountsList=new ArrayList<>();
		
		accountsList= aDao.getAllCustomerAccounts( /*customer_id*/ 1);
		
		//System.out.println(accountsList.get(0).getBalance());
		//System.out.println(accountsList.get(1).getBalance());
		
		Assertions.assertEquals(2, accountsList.size());
		
		
	}
	
	
	@AfterAll
	static void removeTestingStuff() {
		String sql = "delete from account where account_name LIKE 'test%';";
		
		try (Connection con= ConnectionUtils.getConnection();){
			
			PreparedStatement ps=con.prepareStatement(sql);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
