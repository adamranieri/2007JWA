package dev.kusch.daoTests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.kusch.daos.AccountDAO;
import dev.kusch.daos.AccountDAOMaria;
import dev.kusch.daos.CustomerDAO;
import dev.kusch.daos.CustomerDAOMaria;
import dev.kusch.entities.Account;
import dev.kusch.entities.Customer;
import dev.kusch.exceptions.NegativeBalanceException;
import dev.kusch.util.TestUtil;
import dev.kusch.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)// necessary to run tests in order 
class AccountDaoTests {

	public static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();
	public static CustomerDAO dao = CustomerDAOMaria.getCustomerDAOMaria();

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
	void getAllAccountsEmpty() {
		Set<Account> account = adao.getAllAccounts();
		Set<Account> emptyAcc = new HashSet<Account>();
		Assertions.assertEquals(emptyAcc, account);
	}
	
	@Test
	@Order(2)
	void testCreateBasic() {
		Customer testCust = new Customer(0, "John Smith", "password123");
		dao.createCustomer(testCust);
		
		Account testAcc = new Account(0, "VacationFund", 30.00, 1);
		adao.createAccount(testAcc);
		Assertions.assertNotEquals(0, testAcc.getaId());
	}

	@Test
	@Order(3)
	void getAccountByIdBasic() {
		Account getAcc = adao.getAccountById(1);
		Assertions.assertEquals(1, getAcc.getaId());
	}
	
	@Test
	@Order(4)
	void getAccountByIdNegative() {
		Account account = adao.getAccountById(2000);
		Assertions.assertNull(account);
	}
	
	@Test
	@Order(5)
	void getAllAccounts() {
		Account testAcc2 = new Account(0, "Retirement Fund", 1000, 1);
		adao.createAccount(testAcc2);

		Set<Account> fullAcc = adao.getAllAccounts();
		Assertions.assertEquals(2, fullAcc.size());
	}
	
	@Test
	@Order(6)
	void getAccountsByCustomerId() {
		Set<Account> accounts = adao.getAccountByCustomerId(1);
		Assertions.assertEquals(2, accounts.size());
	}
	
	@Test
	@Order(7)
	void getAccountsByBadCustomerId() {
		Set<Account> accounts = adao.getAccountByCustomerId(100000);
		Assertions.assertEquals(0, accounts.size());
	}
	
	@Test
	@Order(8)
	void testGetAccountWithLowerBound() {
		Set<Account> accounts = adao.getAccountWithBalanceBetween(1, 200.34, Double.MAX_VALUE);
		Account testAcc2 = new Account(2, "Retirement Fund", 1000, 1);
		Set<Account> expected = new HashSet<Account>();
		expected.add(testAcc2);
		Assertions.assertEquals(expected.size(), accounts.size());
	}
	
	@Test
	@Order(9)
	void testGetAccountWithUpperBound() {
		Set<Account> accounts = adao.getAccountWithBalanceBetween(1, 0, 800.00);
		Account testAcc = new Account(0, "VacationFund", 30.00, 1);
		Assertions.assertEquals(1, accounts.size());
	}
	
	@Test
	@Order(10)
	void testGetAccountWithBalanceBetween() {
		Set<Account> accounts = adao.getAccountWithBalanceBetween(1, 0, 2150.45);
		Assertions.assertEquals(2, accounts.size());
	}
	
	@Test
	@Order(11)
	void testGetAccountWithBalanceBetweenNegative() {
		Set<Account> accounts = adao.getAccountWithBalanceBetween(1, 700, 800);
		Set<Account> expected = new HashSet<Account>();
		Assertions.assertEquals(expected, accounts);
	}
	
	@Test
	@Order(12)
	void updateAccountBasic() {
		Account vacat = adao.getAccountById(2);
		vacat.setBalance(2000);
		try {
			vacat = adao.updateAccount(vacat);
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
		}
		Assertions.assertEquals(2000, vacat.getBalance());
	}
	
	@Test
	@Order(13)
	void updateAccountNegative() {
		Account updater = new Account(100000000, "Bad Acc", 1000, 1);
		try {
			updater = adao.updateAccount(updater);
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
		}
		Assertions.assertNull(updater);
	}
	
	@Test
	@Order(14)
	void testThrowsException() {
		Account testAcc = adao.getAccountById(1);
		Exception e = assertThrows(NegativeBalanceException.class, () -> {
			testAcc.setBalance(-900);
			adao.updateAccount(testAcc);
		});
	}

	@Test
	@Order(15)
	void deleteAccount() {
		boolean result = adao.deleteAccount(2);
		Assertions.assertEquals(true, result);
	}

	@Test
	@Order(16)
	void deleteAccountNegative() {
		boolean result = adao.deleteAccount(20);
		Assertions.assertEquals(false, result);
	}
}
