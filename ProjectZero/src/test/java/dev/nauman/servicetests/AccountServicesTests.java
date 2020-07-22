package dev.nauman.servicetests;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

import dev.nauma.utils.ConnectionUtil;
import dev.nauman.daos.CustomerDAO;
import dev.nauman.daos.CustomerDAOImpl;
import dev.nauman.entities.Account;
import dev.nauman.entities.Customer;
import dev.nauman.exceptions.NegativeBalanceException;
import dev.nauman.services.AccountService;
import dev.nauman.services.AccountServiceImpl;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
class AccountServicesTests {

	AccountService aserv = new AccountServiceImpl();
	private static CustomerDAO cdao = CustomerDAOImpl.getCustomerDAOImpl();

	@BeforeAll
	static void setUp() {

		try(Connection conn = ConnectionUtil.getConnection()){

			String sql = "CALL set_up_projectzerodb()";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();

		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		Customer customer1 = new Customer(0, "Gandalf The White","flyYouFools");
		Customer customer2 = new Customer(0, "Saruman The White","masterSauron");
		cdao.createCustomer(customer1);
		cdao.createCustomer(customer2);
	}
	
	@Test
	@Order(1)
	void createAccountPositiveTest() throws NegativeBalanceException {
		Account account1 = new Account(0,1,"My Checking",2500);
		Account account2 = new Account(0,1,"My Saving",100000);
		Account account3 = new Account(0,1,"Vacation Fund",1000);
		Account account4 = new Account(0,1,"Fututre Tution",8000);
		Account account5 = new Account(0,2,"My Checking",13000);

		aserv.createAccount(account1);
		aserv.createAccount(account2);
		aserv.createAccount(account3);
		aserv.createAccount(account4);
		aserv.createAccount(account5);

		Assertions.assertNotEquals(0, account1.getaId());
		Assertions.assertNotEquals(0, account2.getaId());
		Assertions.assertNotEquals(0, account3.getaId());
		Assertions.assertNotEquals(0, account4.getaId());
		Assertions.assertNotEquals(0, account5.getaId());
	}
	@Test
	@Order(2)
	void createAccountNegativeTest() throws NegativeBalanceException {
		Exception e = assertThrows(NegativeBalanceException.class, ()->{
			Account account = new Account(0,1,"My Saving",-50000);
			aserv.createAccount(account);
		});
		Assertions.assertEquals("The balance cannot be less than 0.", e.getMessage());
	}
	@Test
	@Order(3)
	void getAccountByAIdTest() {
		Account account = aserv.getAccountByAId(1);
		
		Assertions.assertEquals(1, account.getaId());
	}
	@Test
	@Order(4)
	void getAllAccountsTest() {
		Set<Account> accounts = aserv.getAllAccounts();
		
		Assertions.assertEquals(5, accounts.size());
	}
	@Test
	@Order(5)
	void getAccountByCIdTest() {
		Set<Account> accounts = aserv.getAccountsByCId(1);
		
		Assertions.assertEquals(4, accounts.size());
	}
	@Test
	@Order(6)
	void getAllAccountsWithBalanceLessThanTest() {
		Set<Account> accounts = aserv.getAllAccountsWithBalanceLessThan(3000);
		
		Assertions.assertEquals(2, accounts.size());
	}
	@Test
	@Order(7)
	void getAllAccountsWithBalanceGreaterThanTest() {
		Set<Account> accounts = aserv.getAllAccountsWithBalanceGreaterThan(3000);
		
		Assertions.assertEquals(3, accounts.size());
	}
	@Test
	@Order(8)
	void getAccountsByCIdWithBalanceLessThanTest() {
		Set<Account> accounts = aserv.getAccountsByCIdWithBalanceLessThan(1,3000);
		
		Assertions.assertEquals(2, accounts.size());
	}
	@Test
	@Order(9)
	void getAccountsByCIdWithBalanceGreaterThanTest() {
		Set<Account> accounts = aserv.getAccountsByCIdWithBalanceGreaterThan(1,3000);

		Assertions.assertEquals(2, accounts.size());
	}
	@Test
	@Order(10)
	void getCustomerAccountByAIdPositiveTest() {
		Account account = aserv.getCustomerAccountByAId(1, 3);
		
		Assertions.assertEquals(3, account.getaId());
	}
	@Test
	@Order(11)
	void getCustomerAccountByAIdNegativeTest() {
		Account account = aserv.getCustomerAccountByAId(2, 3);
		
		Assertions.assertEquals(null, account);
	}
	@Test
	@Order(12)
	void checkBalanceOfAccountTest() {
		double actual = aserv.checkBalance(1);
		
		Assertions.assertEquals(2500, actual);
	}
	@Test
	@Order(13)
	void updateAccountTest() throws NegativeBalanceException {
		Account account = new Account(2,2,"Adventure Fund", 25000);
		
		Assertions.assertEquals("Adventure Fund", account.getAccountName());
	}
	@Test
	@Order(14)
	void depositTest() {
		Account account = aserv.deposit(aserv.getAccountByAId(1), 300);
		double expectedNewBalance = 2800;
		
		Assertions.assertEquals(expectedNewBalance, account.getBalance());
	}
	@Test
	@Order(15)
	void withdrawTest() {
		Account account = aserv.withdraw(aserv.getAccountByAId(1), 300);
		double expectedNewBalance = 2500;
		
		Assertions.assertEquals(expectedNewBalance, account.getBalance());
	}
	@Test
	@Order(16)
	void customerOwnedAccountPositiveTest() {
		Account account = aserv.getAccountByAId(2);
		boolean result = aserv.customerOwnedAccount(1, account);
		
		Assertions.assertEquals(true, result);
	}
	@Test
	@Order(17)
	void customerOwnedAccountNegativeTest() {
		Account account = aserv.getAccountByAId(2);
		boolean result = aserv.customerOwnedAccount(2, account);
		
		Assertions.assertEquals(false, result);
	}
	@Test
	@Order(18)
	void deleteAccountByAIdPositiveText() {
		boolean result = aserv.deleteAccountByAId(1);
		
		Assertions.assertEquals(true, result);
	}
	@Test
	@Order(19)
	void deleteAccountByAIdNegativeText() {
		boolean result = aserv.deleteAccountByAId(44);
		
		Assertions.assertEquals(false, result);
	}
	@Test
	@Order(20)
	void deleteAccountByCIdPositiveTest() {
		boolean result = aserv.deleteAccountsByCId(1);
		
		Assertions.assertEquals(true, result);
	}
	@Test
	@Order(21)
	void deleteAccountByCIdNegativeTest() {
		boolean result = aserv.deleteAccountsByCId(44);
		
		Assertions.assertEquals(false, result);
	}

	@AfterAll
	static void tearDown() {

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_projectzerodb()";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();

		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}