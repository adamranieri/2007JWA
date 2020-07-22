package dev.schneider.daotests;

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

import dev.schneider.dao.AccountDAO;
import dev.schneider.dao.AccountDAOImpl;
import dev.schneider.dao.CustomerDAO;
import dev.schneider.dao.CustomerDAOImpl;
import dev.schneider.entities.Account;
import dev.schneider.entities.Customer;
import dev.schneider.utils.ConnectionUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class AccountDAOTests {

	public static AccountDAO adao = AccountDAOImpl.getAccountDAO();
	public static CustomerDAO cdao = CustomerDAOImpl.getCustomerDAO();

	
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
	
	//create
	@Test
	@Order(1)
	void createAccount() {
		Customer customer = new Customer(0, "usernameguy", "passguy"); 
		cdao.createCustomer(customer);
		Account account = new Account(0,1,"savings",5); 
		adao.createAccount(account);
		Assertions.assertNotEquals(0, account.getAcctID());
	}
	
	
	//read
	
	@Test
	@Order(2)
	void getAccountsByCustomer() {
		Set<Account> accounts = adao.getAllCustomerAccounts(1);
		Assertions.assertEquals(1, accounts.size());
	}
	
	@Test
	@Order(3)
	void getAccountByAccountID() {
		Account account = adao.getAccountByAcctID(1);
		Assertions.assertEquals(5, account.getBalance());
	}
	
	
	//update
	@Test
	@Order(4)
	void updateAccountName() {
		Account account = adao.getAccountByAcctID(1);
		account.setAcctName("checking");
		account = adao.updateAccount(account);
		Assertions.assertEquals("checking", account.getAcctName());
	}
	
	@Test
	@Order(5)
	void updateAccountBalance() {
		Account account = adao.getAccountByAcctID(1);
		account.setBalance(100000);
		account = adao.updateAccount(account);
		Assertions.assertEquals(100000, account.getBalance());
	}
	
	//delete
	
	@Test
	@Order(6)
	void deleteAccount() {
		boolean result = adao.deleteAccount(1);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(7)
	void deleteAccountNegative() {
		boolean result = adao.deleteAccount(13243);
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
