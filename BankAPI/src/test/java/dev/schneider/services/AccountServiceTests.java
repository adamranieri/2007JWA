package dev.schneider.services;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.schneider.entities.Account;
import dev.schneider.entities.Customer;
import dev.schneider.exceptions.NegativeBalanceException;
import dev.schneider.utils.ConnectionUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


class AccountServiceTests {
	
	private static AccountService aserv = new AccountServiceImpl();
	private static CustomerService cserv = new CustomerServiceImpl();
	
	
	@BeforeAll
	static void setup() {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	@Order(1)
	void createAccount() {
		Customer customer = new Customer(0, "myusername", "mypass");
		cserv.createCustomer(customer);	
		Account account = new Account(0,1,"savings",5); 
		aserv.addAccount(account);
		Assertions.assertNotEquals(0, account.getAcctID());
		}
	
	@Test
	@Order(2)
	void getAccountsByCustomer() {
		Set<Account> accounts = aserv.getAccountsByCustomer(1);
		Assertions.assertEquals(1, accounts.size());
		}
	
	@Test
	@Order(3)
	void getAccountsLessThan() {
		Set<Account> accounts = aserv.getAccountsByCustomer(1);
		Set<Account> accountsLess = aserv.balanceLessThan(accounts, 500);
		Assertions.assertEquals(1, accountsLess.size());
	}
	
	@Test
	@Order(4)
	void getAccountsGreaterThan() {
		Set<Account> accounts = aserv.getAccountsByCustomer(1);
		Set<Account> accountsGreater = aserv.balanceGreaterThan(accounts, 500);
		Assertions.assertEquals(0, accountsGreater.size());

	}
	
	@Test
	@Order(5)
	void getAccount() {
		Account account = aserv.getAccountById(1);
		Assertions.assertEquals(5, account.getBalance());
		}
	
	@Test
	@Order(6)
	void updateAccount() throws NegativeBalanceException {
		Account account = aserv.getAccountById(1);
		account.setAcctName("checking");
		account = aserv.updateAccount(account);
		Assertions.assertEquals("checking", account.getAcctName());
		}

	@Test
	@Order(7)
		
	void updateAccountNegative() throws NegativeBalanceException {
		Exception e = assertThrows(NegativeBalanceException.class, ()->{
			Account account = aserv.getAccountById(1);
			account.setBalance(-1);;
			aserv.updateAccount(account);
		});
		Assertions.assertEquals(e.getMessage(), "Balance cannot be negative");	
	}
	
	@Test
	@Order(8)
	void deleteAccount() {
		boolean result = aserv.deleteAccount(1);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(9)
	void deleteAccountNegative() {
		boolean result = aserv.deleteAccount(13243);
		Assertions.assertEquals(false, result);
	}
	
	@AfterAll
	static void tearDown() {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
