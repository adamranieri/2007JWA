package dev.cosentino.servicestests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.cosentino.exceptions.CustomerDoesNotExistException;
import dev.cosentino.exceptions.NegativeBalanceException;
import dev.cosentino.resources.Account;
import dev.cosentino.resources.Customer;
import dev.cosentino.services.AccountService;
import dev.cosentino.services.AccountServiceImpl;
import dev.cosentino.services.CustomerService;
import dev.cosentino.services.CustomerServiceImpl;
import dev.cosentino.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class AccountServiceTests {

	private static AccountService aserv = new AccountServiceImpl();
	private static CustomerService cserv = new CustomerServiceImpl();
	
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
	void createAccount() throws CustomerDoesNotExistException{
		Customer customer = new Customer(0,"newUser", "password");
		cserv.createNewCustomer(customer);
		Account account = new Account(0,"accountname",300,customer.getCustomerId());
		aserv.createAccount(account);
		Assertions.assertEquals(1, account.getAccountId());
	}
	
	@Test
	@Order(2)
	void getAccountByAccountId() {
		Account account = aserv.getAccountByAccountId(1);
		Assertions.assertEquals(1, account.getAccountId());
	}
	
	@Test
	@Order(3)
	void getAccountsByCustomerId() throws CustomerDoesNotExistException {
		Account account = new Account(0,"new account",30,1);
		aserv.createAccount(account);
		Set<Account> accounts = aserv.getAccountsByCustomerId(1);
		Assertions.assertEquals(2, accounts.size());
	}
	
	@Test
	@Order(4)
	void getAllAccounts() throws CustomerDoesNotExistException {
		Customer customer = new Customer(0,"another user","pass");
		cserv.createNewCustomer(customer);
		Account account = new Account(0,"my second account", 400,2);
		aserv.createAccount(account);
		Set<Account> accounts = aserv.getAllAccounts();
		Assertions.assertEquals(3, accounts.size());
	}
	
	@Test
	@Order(5)
	void updateAccount() {
		Account account = aserv.getAccountByAccountId(2);
		account.setAccountName("new account name");
		aserv.updateAccount(account);
		Assertions.assertEquals("new account name", account.getAccountName());
	}
	
	@Test
	@Order(6)
	void deleteAccount() {
		Account account = aserv.getAccountByAccountId(1);
		boolean result = aserv.deleteAccount(account);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(7)
	void deposit() throws NegativeBalanceException{
		Account account = aserv.getAccountByAccountId(2);
		aserv.updateBalance(account, 100);
		Assertions.assertEquals(130, account.getBalance());
	}
	
	@Test
	@Order(8)
	void withdraw() throws NegativeBalanceException{
		Account account = aserv.getAccountByAccountId(2);
		aserv.updateBalance(account, -10);
		Assertions.assertEquals(120, account.getBalance());
	}
	
	@Test
	@Order(9)
	void withdrawFail() throws NegativeBalanceException{
		Exception e = assertThrows(NegativeBalanceException.class,()->{
			Account account = aserv.getAccountByAccountId(2);
			aserv.updateBalance(account, -200);
		});
		
		Assertions.assertEquals("Negative balance not allowed", e.getMessage());
	}
	
	@Test
	@Order(10)
	void balanceGreaterThan() throws CustomerDoesNotExistException {
		Account accountA = new Account(0,"A",15,2);
		aserv.createAccount(accountA);
		Account accountB = new Account(0, "B", 100, 2);
		aserv.createAccount(accountB);
		Customer cust = cserv.getCustomerById(2);
		
		Set<Account> accounts = aserv.getBalanceGreaterThan(cust, 50);
		Assertions.assertEquals(2, accounts.size());
	}
	
	@Test
	@Order(11)
	void balanceLessThan() {
		Customer cust = cserv.getCustomerById(2);
		Set<Account> accounts = aserv.getBalanceLessThan(cust, 50);
		Assertions.assertEquals(1, accounts.size());
	}
	
	@Test
	@Order(12)
	void balanceBetween() {
		Customer cust = cserv.getCustomerById(2);
		Set<Account> accounts = aserv.getBalanceBetween(cust, 200, 40);
		Assertions.assertEquals(1, accounts.size());
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
