package dev.rutan.servicetests;

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

import dev.rutan.entities.Account;
import dev.rutan.entities.Customer;
import dev.rutan.services.AccountService;
import dev.rutan.services.AccountServiceImpl;
import dev.rutan.services.CustomerService;
import dev.rutan.services.CustomerServiceImpl;
import dev.rutan.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class AccountServiceTests {

	private static AccountService aserv = new AccountServiceImpl();
	private static CustomerService cserv = new CustomerServiceImpl();
	
	@BeforeAll
	static void setUp() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_bank_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void createAccount() {
		Customer customer = new Customer(0, "George", "password");
		Account account = new Account(0, 1, "Savings", 500);
		cserv.createCustomer(customer);
		aserv.createAccount(account);
		Assertions.assertNotEquals(0, account.getaId());
	}

	@Test
	@Order(2)
	void getAccountById() {
		Account account = aserv.getAccountById(1);
		Assertions.assertEquals(1, account.getaId());
		Assertions.assertEquals(500, account.getBalance());
	}
	
	@Test
	@Order(3)
	void getAllAccounts() {
		Account account = new Account(0, 1, "Checking", 800);
		Account account2 = new Account(0, 1, "Retirement Fund", 500);
		Account account3 = new Account(0, 1, "Retirement Fund", 700);
		aserv.createAccount(account);
		aserv.createAccount(account2);
		aserv.createAccount(account3);
		Set<Account> accounts = aserv.getAllAccounts();
		Assertions.assertEquals(4, accounts.size());
	}
	
	@Test
	@Order(4)
	void getAllAccountsByCustomerId() {
		Customer customer = new Customer(0, "George", "password");
		Account account = new Account(0, 2, "Savings", 300);
		cserv.createCustomer(customer);
		aserv.createAccount(account);
		
		Set<Account> accounts = aserv.getAllAccountsByCustomerId(1);
		Assertions.assertEquals(4, accounts.size());
		accounts = aserv.getAllAccountsByCustomerId(2);
		Assertions.assertEquals(1, accounts.size());
	}
	
	@Test
	@Order(5)
	void updateAccount() {
		Account account = new Account(1, 1, "All the savings", 600);
		aserv.updateAccount(account);
		Assertions.assertEquals("All the savings", account.getAccountName());
		Assertions.assertEquals(600, account.getBalance());
	}
	
	@Test
	@Order(6)
	void deleteAccount() {
		boolean result = aserv.deleteAccount(2);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(7)
	void getAllAccountsGreaterThan() {
		Set<Account> accounts = aserv.getAccountsGreaterThan(500, 1);
		Assertions.assertEquals(2, accounts.size());
	}
	
	@Test
	@Order(8)
	void getAllAccountsLessThan() {
		Set<Account> accounts = aserv.getAccountsLessThan(700, 1);
		Assertions.assertEquals(2, accounts.size());
	}
	
	@Test
	@Order(9)
	void getAllAccountsBetween() {
		Set<Account> accounts = aserv.getAccountsBetween(500, 700, 1);
		Assertions.assertEquals(1, accounts.size());
	}
	
	@Test
	@Order(10)
	void deleteAllAccountsByCustomerId() {
		boolean result = aserv.deleteAccountsByCustomerId(1);
		Assertions.assertEquals(true, result);
		Set<Account> accounts = aserv.getAllAccounts();
		Assertions.assertEquals(1, accounts.size());
	}
	
	@Test
	@Order(11)
	void createAccountWithNegativeBalance() {
		Account account = new Account(0, 2, "Savings", -500);
		aserv.createAccount(account);
		Assertions.assertEquals(0, account.getaId());
	}
	
	@Test
	@Order(12)
	void updateAccountWihtNegativeBalance() {
		Account account = new Account(4, 2, "Savings", -500);
		aserv.updateAccount(account);
		account = aserv.getAccountById(4);
		Assertions.assertNotEquals(-500, account.getaId());
	}
	
	@AfterAll
	static void endTest() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
