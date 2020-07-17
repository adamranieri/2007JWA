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
		
		Account Account = new Account(0, "Food Fund", 200.00, 1);
		aserv.startAccount(Account);
		Assertions.assertNotEquals(0, Account.getcId());
				
	}
	
	@Test
	@Order(2)
	void testGetAllAccounts() {
		Account Account = new Account(0, "Savings", 1543.76, 1);
		aserv.startAccount(Account);
		Set<Account> Accounts = aserv.getAllAccounts();
		Assertions.assertEquals(2, Accounts.size());
	}
	
	@Test
	@Order(3)
	void testGetAccountById() {
		Account Account = aserv.getAccount(2);
		Assertions.assertEquals("PuppLover87", Account.getUsername());
	}
	
	@Test
	@Order(4)
	void testGetAccountByBadId() {
		Account Account = aserv.getAccount(10000000);
		Assertions.assertNull(Account);
	}
	
	@Test
	@Order(5)
	void testGetAccountByUser() {
		Account Account = aserv.getAccount("PuppLover87");
		Assertions.assertEquals(2, Account.getcId());
	}
	
	@Test
	@Order(6)
	void testGetAccountByBadUser() {
		Account Account = aserv.getAccount("EXPLICITNAME");
		Assertions.assertNull(Account);
	}
	
	@Test
	@Order(7)
	void testUpdateAccountBasic() {
		Account Account = new Account(1, "Mr.Cool123", "don'tLookAt3xpl0si0n$");
		aserv.updateAccount(Account);
		Assertions.assertEquals("don'tLookAt3xpl0si0n$", Account.getPassword());
	}
	
	@Test
	@Order(8)
	void testUpdateFakeAccount() {
		Account Account = new Account(50, "Mr.Drool123", "don'tLookAt3xpl0si0n$");
		Account = aserv.updateAccount(Account);
		Assertions.assertNull(Account);
	}
	
	@Test
	@Order(9)
	void testDeleteAccountBasic() {
		Account Account = new Account(2, "PuppLover87", "DebbieDaschound234");
		boolean result = aserv.deleteAccount(Account);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(10)
	void testDeleteAccountNegative() {
		Account Account = new Account(17, "PuppH8r87", "DebbieDoxenEvil");
		boolean result = aserv.deleteAccount(Account);
		Assertions.assertEquals(false, result);
	}

}
