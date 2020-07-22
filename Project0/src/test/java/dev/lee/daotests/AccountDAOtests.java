package dev.lee.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.lee.daos.AccountDAO;
import dev.lee.daos.AccountDAOMaria;
import dev.lee.entities.Account;
import dev.lee.exceptions.NegativeBalanceException;
import dev.lee.utils.ConnectionUtil;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class AccountDAOtests {

	public static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();
	
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
	void createAccount() {
		Account testAccount = new Account(0, 1, "Savings", new BigDecimal("999.23"));
		
		try {
			testAccount = adao.createAccount(testAccount);
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
		}
		Assertions.assertNotEquals(0, testAccount.getaID());
	}
	
	@Test
	@Order(1)
	void createAccountNegative() {
		Account testAccount = new Account(0, 1, "Savings", new BigDecimal("-999.23"));
		
		assertThrows(NegativeBalanceException.class, ()-> {
			adao.createAccount(testAccount);
		});
	}
	
	@Test
	@Order(2)
	void populateDatabase() {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL bank_db.populate_database";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	void getAccountByAccountId() {		
		Account testAccount = adao.getAccountByAccountId(3);
		Assertions.assertEquals(3, testAccount.getaID());
	}
	
	@Test
	@Order(3)
	void getAccountsByCustomerId() {
		Set<Account> allAccounts = adao.getAllAccounts();
		Set<Account> filteredAccounts = new HashSet<Account>();
		
		for(Account account: allAccounts) {
			if(account.getcID() == 3) {
				filteredAccounts.add(account);
			}
		}
		Set<Account> daoCustAccounts = adao.getAccountsByCustomerId(3);
		Assertions.assertEquals(filteredAccounts.size(), daoCustAccounts.size());
	}

	@Test
	@Order(3)
	// Type 1
	void getAccountsFilteredByCidBalanceGreater() {
		Set<Account> accounts = adao.getAllAccounts();
		Set<Account> lowerAccounts = new HashSet<Account>();
		
		BigDecimal num = new BigDecimal("500.00");
		
		for(Account account: accounts) {
			if(account.getBalance().compareTo(num) == 1 && account.getcID() == 3) {
				lowerAccounts.add(account);
			}
		}
		
		Set<Account> daoLowerAccounts = new HashSet<Account>();
		
		daoLowerAccounts = adao.getAccountsFilteredByBalance( 3 , "500", "-1");
		
		Assertions.assertEquals(lowerAccounts.size(), daoLowerAccounts.size());
	}
	
	@Test
	@Order(3)
	// Type 2
	void getAccountsFilteredByCidBalanceLess() {
		Set<Account> accounts = adao.getAllAccounts();
		Set<Account> lowerAccounts = new HashSet<Account>();
		
		BigDecimal num = new BigDecimal("500.00");
		
		for(Account account: accounts) {
			if(account.getBalance().compareTo(num) == -1 && account.getcID() == 3) {
				lowerAccounts.add(account);
			}
		}
		
		Set<Account> daoLowerAccounts = new HashSet<Account>();
		
		daoLowerAccounts = adao.getAccountsFilteredByBalance(3, "-1", "500");
		
		Assertions.assertEquals(lowerAccounts.size(), daoLowerAccounts.size());
	}
	
	@Test
	@Order(3)
	//Type 3
	void getAccountsFilteredByCidBalanceBetween() {
		Set<Account> accounts = adao.getAllAccounts();
		Set<Account> lowerAccounts = new HashSet<Account>();
		
		BigDecimal lower = new BigDecimal("2000");
		BigDecimal upper = new BigDecimal("5000.00");
		
		
		for(Account account: accounts) {
			if(account.getBalance().compareTo(lower) == 1 && account.getBalance().compareTo(upper) == -1 && account.getcID() == 3) {
				lowerAccounts.add(account);
			}
		}
		
		Set<Account> daoLowerAccounts = new HashSet<Account>();
		
		daoLowerAccounts = adao.getAccountsFilteredByBalance(3, "2000.0", "5000.0");
		
		Assertions.assertEquals(lowerAccounts.size(), daoLowerAccounts.size());
	}
	
	@Test
	@Order(3)
	//Type 4
	void getAccountsFilteredByCid() {
		Set<Account> accounts = adao.getAllAccounts();
		Set<Account> lowerAccounts = new HashSet<Account>();
		
		
		for(Account account: accounts) {
			if(account.getcID() == 3) {
				lowerAccounts.add(account);
			}
		}
		
		Set<Account> daoLowerAccounts = new HashSet<Account>();
		
		daoLowerAccounts = adao.getAccountsFilteredByBalance(3, "-1", "-1");
		
		Assertions.assertEquals(lowerAccounts.size(), daoLowerAccounts.size());
	}
	
	@Test
	@Order(3)
	//Type 5
	void getAccountsFilteredByBalanceGreater() {
		Set<Account> accounts = adao.getAllAccounts();
		Set<Account> lowerAccounts = new HashSet<Account>();
		
		BigDecimal lower = new BigDecimal("1100");

		
		
		for(Account account: accounts) {
			if(account.getBalance().compareTo(lower) == 1) {
				lowerAccounts.add(account);
			}
		}
		
		Set<Account> daoLowerAccounts = new HashSet<Account>();
		
		daoLowerAccounts = adao.getAccountsFilteredByBalance(-1, "1100.0", "-1");
		
		Assertions.assertEquals(lowerAccounts.size(), daoLowerAccounts.size());
	}
	
	@Test
	@Order(3)
	//Type 6
	void getAccountsFilteredByBalanceLess() {
		Set<Account> accounts = adao.getAllAccounts();
		Set<Account> lowerAccounts = new HashSet<Account>();
		
		BigDecimal upper = new BigDecimal("5000");

		for(Account account: accounts) {
			if(account.getBalance().compareTo(upper) == -1) {
				lowerAccounts.add(account);
			}
		}
		
		Set<Account> daoLowerAccounts = new HashSet<Account>();
		
		daoLowerAccounts = adao.getAccountsFilteredByBalance(-1, "-1", "5000.0");
		
		Assertions.assertEquals(lowerAccounts.size(), daoLowerAccounts.size());
	}
	
	@Test
	@Order(3)
	//Type 7
	void getAccountsFilteredByBalanceBetween() {
		Set<Account> accounts = adao.getAllAccounts();
		Set<Account> lowerAccounts = new HashSet<Account>();
		
		BigDecimal lower = new BigDecimal("2000");
		BigDecimal upper = new BigDecimal("10000");

		for(Account account: accounts) {
			if(account.getBalance().compareTo(upper) == -1 && account.getBalance().compareTo(lower) == 1) {
				lowerAccounts.add(account);
			}
		}
		
		Set<Account> daoLowerAccounts = new HashSet<Account>();
		
		daoLowerAccounts = adao.getAccountsFilteredByBalance(-1, "2000", "10000.0");
		Assertions.assertEquals(lowerAccounts.size(), daoLowerAccounts.size());
	}
	
	@Test
	@Order(3)
	//Type 8
	void getAccountsFilteredByBalanceNoConditions() {
		Set<Account> accounts = adao.getAllAccounts();
		
		Set<Account> daoAccounts = adao.getAccountsFilteredByBalance(-1, "-1", "-1");
		Assertions.assertEquals(accounts.size(), daoAccounts.size());
	}
	
	@Test
	@Order(3)
	void getAllAccounts() {
		Set<Account> accounts = adao.getAllAccounts();
		Assertions.assertEquals(13, accounts.size());
	}
	
	@Test
	@Order(4)
	void updateAccount() {
		Account account = adao.getAccountByAccountId(1);
		account.setAccountName("updated savings");
		try {
			account = adao.updateAccount(account);
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
		}

		Assertions.assertEquals("updated savings", account.getAccountName());
	}
	
	@Test
	@Order(4)
	void updateAccountNegative() {
		Account account = adao.getAccountByAccountId(2);
		account.setBalance(new BigDecimal("-99"));
		
		assertThrows(NegativeBalanceException.class, ()-> {
			adao.createAccount(account);
		});
	}
	
	@Test
	@Order(5)
	void deleteAccount() {
		boolean result = adao.deleteAccount(1);
		
		Assertions.assertTrue(result);
	}
	
	@Test
	@Order(5)
	void deleteAccountNegative() {
		boolean result = adao.deleteAccount(999);
		
		Assertions.assertFalse(result);
	}
	
}
