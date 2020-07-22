package dev.zak.daotests;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.zak.daos.AccountDaoInterface;
import dev.zak.daos.AccountDaoMaria;
import dev.zak.daos.CustomerDaoInterface;
import dev.zak.daos.CustomerDaoMaria;
import dev.zak.entities.Account;
import dev.zak.entities.Customer;
import dev.zak.utilities.ConnectionUtility;


@TestMethodOrder(OrderAnnotation.class)
class AccountDAOTest {
	
	public static AccountDaoInterface aDao =  AccountDaoMaria.getAccountDaoMaria();
	public static CustomerDaoInterface cDao =  CustomerDaoMaria.getCustomerDaoMaria();

	@BeforeAll
	static void setUp() {

		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "CALL set_up_bank_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(1)
	void createAccount() {
		Customer c = new Customer(0,"Jon","Jon_password");
		cDao.createCustomer(c);
		
		Account a = new Account(0, 1,"Jon account",3000);
		aDao.createAccount(c.getcId(), a);
		Assertions.assertNotEquals(0, c.getcId());
	}
	
	@Test
	@Order(2)
	void getAccountById() {		
		Account c = aDao.getAccountById(1, 1);
		Assertions.assertEquals(1, c.getcId());
	}
	
	@Test
	@Order(3)
	void getAllAccountsByCustomerId() {
		Account a = new Account(0, 1,"Kevin account",2000);
		aDao.createAccount(a.getcId(), a);
		Set<Account> accounts = aDao.getAllAccountsByCustomerId(1);
		Assertions.assertEquals(2, accounts.size());
	}
	
	@Test
	@Order(4)
	void updateAccount() {
		Account a = aDao.getAccountById(1, 1);
		a.setAccountName("Zakaria account");
		a = aDao.updateAccount(1, a);
		Assertions.assertEquals("Zakaria account", a.getAccountName());
	}
	
	@Test
	@Order(5)
	void updateAccountNegative() {
		Account a = new Account(2000, 1,"Kevin account 3",2000);
		a = aDao.updateAccount(1, a);
		Assertions.assertEquals(null, a);
	}
	
	@Test
	@Order(6)
	void getAccountLessThan() {
		Set<Account> accounts = aDao.getAccountLessThan(1, 3000);
		Assertions.assertEquals(1, accounts.size());
	}
	
	@Test
	@Order(7)
	void getAccountGreaterThan() {
		Set<Account> accounts = aDao.getAccountGreaterThan(1, 2000);
		Assertions.assertEquals(1, accounts.size());
	}
	
	@Test
	@Order(8)
	void getAccountBalanceBtween() {
		Set<Account> accounts = aDao.getAccountBalanceBtween(1, 1000, 3000);
		Assertions.assertEquals(2, accounts.size());
	}
	 
	
	@Test
	@Order(9)
	void deleteAccount() {
		boolean result = aDao.deleteAccount(1,1);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(10)
	void deleteAccountNegative() {
		boolean result = aDao.deleteAccount(1,10);
		Assertions.assertEquals(false, result);
	}
	
	@AfterAll
	static void tearDown() {
		
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "CALL tear_down_bank_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
