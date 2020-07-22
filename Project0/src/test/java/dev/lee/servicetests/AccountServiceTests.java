package dev.lee.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import dev.lee.entities.Account;
import dev.lee.entities.Customer;
import dev.lee.exceptions.NegativeBalanceException;
import dev.lee.services.AccountService;
import dev.lee.services.AccountServiceImpl;
import dev.lee.services.CustomerService;
import dev.lee.services.CustomerServiceImpl;
import dev.lee.utils.ConnectionUtil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class AccountServiceTests {

	private static AccountService aserv = new AccountServiceImpl();
	private static CustomerService cserv = new CustomerServiceImpl();
	
	@BeforeAll
	static void setUp() {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL bank_db.set_up_accdatabase";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@AfterAll
	static void tearDown() {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL bank_db.tear_down_database";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(1)
	void establishAccount() {
		Customer customer = new Customer(0, "testUser", "TestPassword");
		cserv.establishCustomer(customer);		
		
		Account test = new Account(0, 1, "established account", new BigDecimal("500.01"));
		try {
			aserv.establishAccount(test);
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
		}
		Assertions.assertNotEquals(0, test.getaID());
	}
	
	@Test
	@Order(1)
	void establishAccountNegative() {
		Account test = new Account(0, 1, "established account", new BigDecimal("-500.01"));
		
		assertThrows(NegativeBalanceException.class, ()-> {
			aserv.establishAccount(test);
		});	
	}
	
	@Test
	@Order(1)
	void getAccountById() {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL bank_db.populate_database";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Account test = aserv.getAccountById(1);
		Assertions.assertEquals(1, test.getaID());
	}
	
	@Test
	@Order(2)
	void getAccountsByAccountName() {
		Set<Account> accounts = aserv.getAccountsByAccountName("savings");
		Assertions.assertEquals(2, accounts.size());
	}
	
	@Test
	@Order(2)
	void getAllAccounts() {
		Set<Account> accounts = aserv.getAllAccounts();
		Assertions.assertEquals(13, accounts.size());
	}
	
	@Test
	@Order(2)
	void getAccountsByCustomerId() {
		Set<Account> accounts = aserv.getAccountsByCustomerId(1);
		Assertions.assertEquals(3,  accounts.size());
		
		Set<Account> acc2 = aserv.getAccountsByCustomerId(3);
		Assertions.assertEquals(7, acc2.size());
		
	}
	
	@Test
	@Order(2)
	void getAccountsFilteredByBalance() {
		Set<Account> lessThan = aserv.getAccountsFilteredByBalance(-1, "-1", "4324.2342");
		Assertions.assertEquals(7, lessThan.size());
		
		Set<Account> greaterThan = aserv.getAccountsFilteredByBalance(-1, "4324.2342", "-1");
		Assertions.assertEquals(4, greaterThan.size());
		
		Set<Account> between = aserv.getAccountsFilteredByBalance(3, "1000.00", "5000.00");
		Assertions.assertEquals(3, between.size());
	}
	
	@Test
	@Order(2)
	void updateAccount() {
		Account account = aserv.getAccountById(4);
		account.setAccountName("updated account name");
		try {
			aserv.updateAccount(account);
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
		}
		
		Assertions.assertEquals("updated account name", aserv.getAccountById(4).getAccountName());
	}
	
	@Test
	@Order(2)
	void updateAccountNegative() {
		Account account = aserv.getAccountById(4);
		account.setBalance(new BigDecimal("-300"));
		assertThrows(NegativeBalanceException.class, ()-> {
			aserv.updateAccount(account);
		});	
	}

	@Test
	@Order(4)
	void deleteAccountByObject() {
		Account account = aserv.getAccountById(5);
		Assertions.assertTrue(aserv.deleteAccount(account));
	}
	
	@Test
	@Order(4)
	void deleteAccountByObjectNegative() {
		Account NonexistentAccount = new Account();
		Assertions.assertFalse(aserv.deleteAccount(NonexistentAccount));
	}
	
	@Test
	@Order(4)
	void deleteAccountByAId() {
		Assertions.assertTrue(aserv.deleteAccount(6));
	}
	
	@Test
	@Order(4)
	void deleteAccountByAIdNegative() {
		Assertions.assertFalse(aserv.deleteAccount(99));
	}
	
	@Test
	@Order(4)
	void changeAccountName() {
		Account account = aserv.getAccountById(7);
		aserv.changeAccountName(account, "changed account name");
		Assertions.assertEquals("changed account name", aserv.getAccountById(7).getAccountName());
	}
	
	@Test
	@Order(3)
	void addToBalance() {
		Account account = aserv.getAccountById(7);
		BigDecimal originalAmount = account.getBalance();
		BigDecimal expected = originalAmount.add(new BigDecimal(234.32));
		
		try {
			aserv.addToBalance(account, new BigDecimal(234.32));
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
		}
		
		
		Assertions.assertEquals(expected, account.getBalance());
	}
	
	@Test
	@Order(3)
	void addToBalanceNegativeBalanceException() {
		assertThrows(NegativeBalanceException.class, ()-> {
			Account account = aserv.getAccountById(3);
			aserv.addToBalance(account, new BigDecimal(-99999999));
		});	
	}
}
