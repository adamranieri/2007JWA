package dev.alsabea.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.alsabea.connectionUtils.ConnectionUtils;
import dev.alsabea.entities.Account;
import dev.alsabea.exceptions.NegativeBalanceException;
import dev.alsabea.services.impl.AccountServicesImpl;

@TestMethodOrder(OrderAnnotation.class)
class TestAccountServices {
	
	
	AccountServices aServices = AccountServicesImpl.getAccountServicesInstance();

	@Test @Order(1)
	void testCreateAccountHappyPath() {
		Account a= new Account();
		a.setCustomerId(5);
		a.setAccountName("test_create_name");
		a.setBalance(800);
		
		try {
			Assertions.assertNotEquals(0,aServices.create(a));
		} catch (NegativeBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testCreateAccountUnhappyPath() {
		Account a= new Account();
		a.setCustomerId(5);
		a.setAccountName("test_create_name");
		a.setBalance(-800);
		
		Assertions.assertThrows(NegativeBalanceException.class, ()->{
			aServices.create(a);
		});
	}
	
	
	@Test	@Order(2)
	void testRetrieveById() {
		Account a= aServices.retrieveById(100);
		Assertions.assertEquals("savings", a.getAccountName());
		
	}
	
	
	
	@Test 
	void testDeleteById() {
		Account a= new Account();
		a.setCustomerId(5);
		a.setAccountName("test_to_be_deleted");
		a.setBalance(800);

		int id=0;
		try {
			id = aServices.create(a);
		} catch (NegativeBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertTrue(aServices.delete(id));
		
		
	}
	
	
	@Test
	void testUpdateHappyPath() {
		Account a= new Account();
		a.setCustomerId(5);
		a.setAccountName("testToBeUpdatedName");
		a.setBalance(800);
		
		int id=0;
		try {
			id = aServices.create(a);
			Account aUpdated= new Account();
			aUpdated.setAccountId(id);
			aUpdated.setCustomerId(5);
			aUpdated.setAccountName("testUpdatedName");
			aUpdated.setBalance(1000);
			Assertions.assertTrue(aServices.update(aUpdated));
		} catch (NegativeBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testUpdateUnhappyPath() {
		Account a= new Account();
		a.setCustomerId(5);
		a.setAccountName("testToBeUpdatedName");
		a.setBalance(800);
		
		int id=0;
		try {
			id = aServices.create(a);
			} catch (NegativeBalanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Account aUpdated= new Account();
			aUpdated.setAccountId(id);
			aUpdated.setCustomerId(5);
			aUpdated.setAccountName("testUpdatedName");
			aUpdated.setBalance(-1000);
			Assertions.assertThrows(NegativeBalanceException.class, ()->{
				aServices.update(aUpdated);	
			});
		
		
	}
	
	
	@Test
	void getAccountsWithBalanceLessThan() {
		List<Account> accts= aServices.balanceLessThan(1, 191);
		
		Assertions.assertEquals(2, accts.size());
	}
	
	
	@Test
	void getAccountsWithBalanceGreaterThan() {
		List<Account> accts= aServices.balanceGreaterThan(2, 1000);
		
		Assertions.assertEquals(1, accts.size());
	}
	
	@Test
	void testGetAccounts() {
		List<Account> accountsList=new ArrayList<>();
		
		accountsList= aServices.retrieveAllAccounts( /*customer_id*/ 1);
		
		Assertions.assertEquals(2, accountsList.size());
		
		
	}
	
	@AfterAll
	static void removeTestingStuff() {
		String sql = "delete from proj_0_db.account where account_name LIKE 'test%';";
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
