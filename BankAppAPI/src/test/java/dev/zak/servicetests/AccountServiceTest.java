package dev.zak.servicetests;

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

import dev.zak.entities.Account;
import dev.zak.entities.Customer;
import dev.zak.services.AccountService;
import dev.zak.services.AccountServiceInterface;
import dev.zak.services.CustomerService;
import dev.zak.services.CustomerServiceInterface;
import dev.zak.utilities.ConnectionUtility;


@TestMethodOrder(OrderAnnotation.class)
class AccountServiceTest {
	
	public static AccountServiceInterface aServ =  new AccountService();
	public static CustomerServiceInterface cServ =  new CustomerService();
	public static Error err =  new Error();
	
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
	void createCustomerAccount() {
		Customer c = new Customer(0,"Jon","Jon_password");
		cServ.createCustomer(c);
		Account a = new Account(0, 1,"Jon account",3000);
		aServ.createAccount(c.getcId(), a);
		Assertions.assertNotEquals(0, a.getaId());
	}

	@Test
	@Order(2)
	void createAccount() {
		int cid = 1;
		Account a = new Account(0, cid,"Jon account 2",3000);
		aServ.createAccount(cid, a);
		Assertions.assertNotEquals(0, a.getaId());
	}

	@Test
	@Order(3)
	void createAccountNegativeBalance(){
		int cid = 2;
		Account a = new Account(0, cid,"Jon account 3",-3000);
		aServ.createAccount(cid, a);
		Assertions.assertEquals(0, a.getaId());
	}
	
	@Test
	@Order(4)
	void getAccountById() {
		int cid = 1;
		int aid = 2;
		Account a = aServ.getAccountById(cid, aid);
		Assertions.assertEquals(2, a.getaId());
	}
	
	@Test
	@Order(5)
	void getAccountByIdNegative() {
		int cid =1;
		Account a = aServ.getAccountById(cid, 2000);
		Assertions.assertEquals(null, a);
	}
	
	@Test
	@Order(6)
	void getAllAccountsByCustomerId() {
		int cid = 1;
		Account a = new Account(0, cid, "Kevin account", 2000);
		aServ.createAccount(cid, a);
		Set<Account> accounts = aServ.getAllAccountsByCustomerId(cid);
		Assertions.assertEquals(3, accounts.size());
	}
	
	@Test
	@Order(7)
	void getAllAccountsByCustomerIdNegative() {
		int cid = 4;
		Set<Account> accounts = aServ.getAllAccountsByCustomerId(cid);
		Assertions.assertEquals(0, accounts.size());
	}
	
	@Test
	@Order(8)
	void updateAccountName() {
		int cid = 1;
		int aid = 3;
		Account a = aServ.getAccountById(cid, aid);
		a.setAccountName("Zakaria account");
		a = aServ.updateAccount(cid, a);
		Assertions.assertEquals("Zakaria account", a.getAccountName());
	}
	
	@Test
	@Order(9)
	void updateAccountBalance() {
		int cid = 1;
		int aid = 3;
		float balance = 4000;
		Account a = aServ.getAccountById(cid, aid);
		a.setBalance(balance);
		a = aServ.updateAccount(cid, a);
		Assertions.assertEquals(balance, a.getBalance());
	}
	
	@Test
	@Order(10)
	void updateAccountNegativeBalanceException() {
		int cid = 1;
		int aid = 3;
		float balance = -7000;
		Account a = aServ.getAccountById(cid, aid);
		a.setBalance(balance);
		a = aServ.updateAccount(cid, a);
		// if new balance is negative (no update in the db) make it zero in the object and return it
		Assertions.assertNotEquals(-7000, a.getBalance());
	}
	
	@Test
	@Order(11)
	void deleteAccount() {
		int cid = 1;
		int aid = 3;
		boolean result = aServ.deleteAccountById(cid,aid);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(13)
	void deleteAccountNegative() {
		int cid = 1;
		int aid = 11;
		boolean result = aServ.deleteAccountById(cid,aid);
		Assertions.assertEquals(false, result);
	}
	
	@Test
	@Order(14)
	void transferMoney() throws SQLException {
		boolean isTransferred = aServ.transferMoney(1, 2, 1500);
		Assertions.assertEquals(true, isTransferred);
	}
	
	@Test
	@Order(15)
	void transferMoneyAccountNotFound() throws SQLException {
		boolean isTransferred = aServ.transferMoney(1, 7, 1500);
		Assertions.assertEquals(false, isTransferred);
	}
	
	@Test
	@Order(16)
	void transferMoneyAmountInsufficientBalance() throws SQLException {
		// Account 1 as only 1500
		boolean isTransferred = aServ.transferMoney(1, 2, 5000);
		Assertions.assertEquals(false, isTransferred);
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
