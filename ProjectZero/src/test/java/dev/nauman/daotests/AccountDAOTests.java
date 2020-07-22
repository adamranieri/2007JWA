package dev.nauman.daotests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.nauma.utils.ConnectionUtil;
import dev.nauman.daos.AccountDAO;
import dev.nauman.daos.AccountDAOImpl;
import dev.nauman.daos.CustomerDAO;
import dev.nauman.daos.CustomerDAOImpl;
import dev.nauman.entities.Account;
import dev.nauman.entities.Customer;
import dev.nauman.exceptions.NegativeBalanceException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class AccountDAOTests {

	private static AccountDAO adao = AccountDAOImpl.getAccountDAOImpl();
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
		
		adao.createAccount(account1);
		adao.createAccount(account2);
		adao.createAccount(account3);
		adao.createAccount(account4);
		adao.createAccount(account5);
		
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
			adao.createAccount(account);
		});
		Assertions.assertEquals("The balance cannot be less than 0.", e.getMessage());
	}
	@Test
	@Order(3)
	void getAccountByAIdTest() {
		Account account = adao.getAccountByAId(2);
		
		Assertions.assertEquals(2, account.getaId());
	}
	@Test
	@Order(4)
	void getAllAccountsTest() {
		Set<Account> accounts = adao.getAllAccounts();
		
		Assertions.assertEquals(5, accounts.size());
	}
	@Test
	@Order(5)
	void getAccountsByCIdPositiveTest() {
		Set<Account> accounts1 = adao.getAccountsByCId(1);
		Set<Account> accounts2 = adao.getAccountsByCId(2);
		
		Assertions.assertEquals(4, accounts1.size());
		Assertions.assertEquals(1, accounts2.size());
	}
	@Test
	@Order(6)
	void getAccountsByCIdNegativeTest(){
		Set<Account> accounts = adao.getAccountsByCId(3);
		
		Assertions.assertEquals(0, accounts.size());
	}
	@Test
	@Order(7)
	void updateAccountPositiveTest() throws NegativeBalanceException{
		Account account = new Account(2,2,"Future Tution", 25000);
		account = adao.updateAccount(account);
		
		Assertions.assertEquals("Future Tution", account.getAccountName());
	}
	@Test
	@Order(8)
	void updateAccountNegativeTest() {
		
		Exception e = assertThrows(NegativeBalanceException.class, () ->{
			Account account = new Account(2,2,"Future Tution", -25000);
			account = adao.updateAccount(account);
		});
		Assertions.assertEquals("The balance cannot be less than 0.", e.getMessage());
	}
	@Test
	@Order(9)
	void deleteAccountByIdPositiveTest() {
		boolean reuslt = adao.deleteAccountByAId(1);
		
		Assertions.assertEquals(true, reuslt);
	}
	@Test
	@Order(10)
	void deleteAccountByIdNegativeTest() {
		boolean reuslt = adao.deleteAccountByAId(55);
		
		Assertions.assertEquals(false, reuslt);
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