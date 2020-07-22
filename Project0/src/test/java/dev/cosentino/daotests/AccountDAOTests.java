package dev.cosentino.daotests;

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

import dev.cosentino.daos.AccountDAO;
import dev.cosentino.daos.AccountDAOMaria;
import dev.cosentino.daos.CustomerDAO;
import dev.cosentino.daos.CustomerDAOMaria;
import dev.cosentino.resources.Account;
import dev.cosentino.resources.Customer;
import dev.cosentino.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class AccountDAOTests {
	
	private static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();
	private static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();
	
	@BeforeAll
	static void setUp() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_projectdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void createAccount() {
		Customer customer = new Customer(0, "cust","pass");
		cdao.createCustomer(customer);
		Account account = new Account(0, "my first account", 0, customer.getCustomerId());
		adao.createAccount(account);
		Assertions.assertNotEquals(0, account.getAccountId());
	}
	
	@Test 
	@Order(2)
	void getAccountByID() {
		Account account = adao.getAccountById(1);
		Assertions.assertNotNull(account);
	}
	
	@Test
	@Order(3)
	void getAllAccounts() {
		Account acc = new Account(0, "account2", 0, 1);
		adao.createAccount(acc);
		Set<Account> accounts = adao.getAllAccounts();
		Assertions.assertEquals(2, accounts.size());
		}
	
	@Test
	@Order(4)
	void getAccountByCustomerId() {
		Set<Account> accounts = adao.getAccountsByCustomerId(1);
		Assertions.assertEquals(2, accounts.size());
	}
	
	@Test
	@Order(5)
	void updateAccount() {
		Account account = adao.getAccountById(1);
		account.setAccountName("new account");
		account = adao.updateAccount(account);
		Assertions.assertEquals("new account", account.getAccountName());
	}
	
	@Test
	@Order(6)
	void deleteAccountSuccess() {
		boolean result = adao.deleteAccount(1);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(7)
	void deleteAccountFail(){
		boolean result = adao.deleteAccount(1000);
		Assertions.assertEquals(false, result);
	}
	
	@AfterAll
	@Test
	static void tearDown() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_projectdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
