package dev.edwin.daotests;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.edwin.daos.AccountDAO;
import dev.edwin.daos.AccountDAOImp;
import dev.edwin.entities.Account;
import dev.edwin.utils.ConnectionUtil;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
class AccountDAOTests {
	
	private static AccountDAO aDao = AccountDAOImp.getAccountDAO();

	@Test
	@Order(1)
	void testCreateAccount() {
		Account account = new Account(0, 2, "New Account One", 1.00);
		Account returned = aDao.createAccount(account);
		
		Assertions.assertNotEquals(0, returned.getaId());
	}
	
	@Test
	@Order(2)
	void testGetAccountById()
	{
		Account returned = aDao.getAccountById(2);
		
		Assertions.assertEquals("Checking", returned.getAccountName());
	}
	
	@Test
	@Order(3)
	void testGetAllAccounts() 
	{
		List<Account> accounts = aDao.getAllAccounts();
		Assertions.assertNotEquals(0, accounts.size());
	}
	
	@Test
	@Order(4)
	void testUpdateAccount()
	{
		Account account = aDao.getAccountById(2);
		account.setBalance(400.50);
		Account returned = aDao.updateAccount(account);
		
		Assertions.assertEquals(400.50, returned.getBalance());
	}
	
	@Test
	@Order(5)
	void testDeleteAccount()
	{
		boolean result = aDao.deleteAccount(4);
		Assertions.assertEquals(true, result);
		
	}
	
	@Test
	@Order(6)
	void testGetAccountsByCustomer() 
	{
		List<Account> result = aDao.getAccountsByCustomerId(2);
		Assertions.assertNotEquals(0 ,result.size());
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
