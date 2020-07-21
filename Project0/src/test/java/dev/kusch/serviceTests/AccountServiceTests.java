package dev.kusch.serviceTests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import dev.kusch.daos.AccountDAO;
import dev.kusch.daos.AccountDAOMaria;
import dev.kusch.entities.Account;
import dev.kusch.entities.Customer;
import dev.kusch.exceptions.NegativeBalanceException;
import dev.kusch.services.AccountServices;
import dev.kusch.services.AccountServicesImpl;
import dev.kusch.services.CustomerServices;
import dev.kusch.services.CustomerServicesImpl;
import dev.kusch.util.TestUtil;
import dev.kusch.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class AccountServiceTests {

	private static AccountServices aserv = new AccountServicesImpl();
	private static CustomerServices cserv = new CustomerServicesImpl();
	
	@BeforeAll
	static void startTests() {
		TestUtil.setUpDb();
	}
	
	@AfterAll
	static void endTests() {
		TestUtil.tearDownDb();
	}
	
	@Test
	@Order(1)
	void testAddAccountBasic() {
		Customer customer = new Customer(0, "Freddy", "NoCatchphrase4Lyf3");
		cserv.addCustomer(customer);
		
		Account account = new Account(0, "Food Fund", 200.00, 1);
		aserv.startAccount(account);
		Assertions.assertNotEquals(0, account.getaId());
				
	}
	
	@Test
	@Order(2)
	void testGetAllAccounts() {
		Account account = new Account(0, "Savings", 1543.76, 1);
		aserv.startAccount(account);
		Set<Account> accounts = aserv.getAllAccounts();
		Assertions.assertEquals(2, accounts.size());
	}
	
	@Test
	@Order(3)
	void testGetAccountById() {
		Account account = aserv.getAccount(2);
		Assertions.assertEquals(2, account.getaId());
	}
	
	@Test
	@Order(4)
	void testGetAccountByBadId() {
		Account account = aserv.getAccount(10000000);
		Assertions.assertNull(account);
	}
	
	@Test
	@Order(5)
	void testGetAccountByUser() {
		Set<Account> accounts = aserv.getAccountsByCustomer(1);
		Assertions.assertEquals(2, accounts.size());
	}
	
	@Test
	@Order(6)
	void testGetAccountByBadUser() {
		Set<Account> account = aserv.getAccountsByCustomer(2000);
		Assertions.assertEquals(0,account.size());
	}
	
	@Test
	@Order(7)
	void testGetAccountsLessThan() {
		Set<Account> accounts = aserv.getAccountsLessThan(300, 1);
		Assertions.assertEquals(1, accounts.size());
	}
	
	@Test
	@Order(8)
	void testGetAccountsLessThanNegative() {
		Set<Account> accounts = aserv.getAccountsLessThan(1000, 7);
		Assertions.assertEquals(0, accounts.size());
	}
	
	@Test
	@Order(9)
	void testGetAccountsGreaterThan() {
		Set<Account> accounts = aserv.getAccountsGreaterThan(1, 1);
		Assertions.assertEquals(2, accounts.size());
	}
	
	@Test
	@Order(10)
	void testGetAccountsGreaterThanNegative() {
		Set<Account> accounts = aserv.getAccountsGreaterThan(1, 9);
		Assertions.assertEquals(0, accounts.size());
	}
	
	@Test
	@Order(11)
	void testGetAccountsBetween() {
		Set<Account> accounts = aserv.getAccountsBetween(100, 500, 1);
		Assertions.assertEquals(1, accounts.size());
	}
	
	@Test
	@Order(12)
	void testGetAccountsBetweenNegative() {
		Set<Account> accounts = aserv.getAccountsBetween(100, 500, 90);
		Assertions.assertEquals(0, accounts.size());
	}
	
	@Test
	@Order(13)
	void testUpdateAccountBasic() {
		Account account = new Account(1, "Food Fund", 187.34, 1);
		try {
			aserv.updateAccount(account);
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
		}
		Assertions.assertEquals(187.34, account.getBalance());
	}
	
	@Test
	@Order(14)
	void testUpdateFakeAccount() {
		Account account = new Account(7, "My Fake Bank Account", 187.34, 1);
		try {
			account = aserv.updateAccount(account);
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
		}
		Assertions.assertNull(account);
	}
	
	@Test
	@Order(15)
	void testDeleteAccountBasic() {
		Account account = new Account(2, "Savings", 1543.76, 1);
		boolean result = aserv.deleteAccount(account);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(16)
	void testDeleteAccountNegative() {
		Account account = new Account(2, "Savings", 1543.76, 1);
		boolean result = aserv.deleteAccount(account);
		Assertions.assertEquals(false, result);
	}

}