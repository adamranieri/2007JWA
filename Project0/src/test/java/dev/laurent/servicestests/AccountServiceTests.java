package dev.laurent.servicestests;

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

import dev.laurent.entities.Customer;
import dev.laurent.entities.Account;
import dev.laurent.exceptions.NegativeBalanceException;
import dev.laurent.services.CustomerService;
import dev.laurent.services.CustomerServiceImpl;
import dev.laurent.services.AccountService;
import dev.laurent.services.AccountServiceImpl;
import dev.laurent.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class AccountServiceTests {
	
	private static AccountService aserv = new AccountServiceImpl();
	private static CustomerService cserv = new CustomerServiceImpl();

	@BeforeAll
	static void setUp() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_bankdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void addAccount() {	
		Customer testCustomer = new Customer(0,"username1", "password1");
		cserv.addCustomer(testCustomer);
		System.out.println(testCustomer);
		Account testAccount = new Account(1,1,"TestAccount1",10.25);
		System.out.println(testAccount);
		aserv.addAccount(testAccount);
		System.out.println(testAccount);
		//Assertions.assertEquals("Test Account 1", testAccount.getAccName());
	}
	
	@Order(2)
	@Test
	void getAccountById() {
		Account testAccount = aserv.getAccountById(1);
		Assertions.assertNotNull(testAccount);
		Assertions.assertEquals(1, testAccount.getAccId());
	}
	
	@Order(3)
	@Test
	void getAccountsByCustomerId() {
		Set<Account> accounts = aserv.getAccountsByCustomerId(1);
		Assertions.assertEquals(1, accounts.size());	
	}
	
	@Order(4)
	@Test
	void getAllAccounts() {
		Set<Account> accounts = aserv.getAllAccounts();
		Assertions.assertEquals(1,accounts.size());
	}
	
	@Order(5)
	@Test
	void updateAccount() {
		Account testAccount = aserv.getAccountById(1);
		testAccount.setAccName("UpdatedUsername");
		testAccount = aserv.updateAccount(testAccount); //saves the changes to that customer
		Assertions.assertEquals("UpdatedUsername", testAccount.getAccName());	
	}
	
	@Order(6)
	@Test
	void deleteAccount() {
		Account testAccount = new Account(2,1,"TestAccount1",10.25);
		aserv.addAccount(testAccount);
		boolean result = aserv.deleteAccount(1);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(7)
	void increaseBalance() throws NegativeBalanceException {
		Account testAccount = new Account(3,1,"TestAccount1",10.25);
		aserv.addAccount(testAccount);
		aserv.increaseBalance(testAccount, 25.35);
		Assertions.assertEquals(35.6, testAccount.getBalance());
	}
	
	@Test
	@Order(8)
	void renameAccount() {
		Account testAccount = new Account(4,1,"TestAccount1",10.25);
		aserv.addAccount(testAccount);
		aserv.renameAccount(testAccount, "My Test Worked!");
		System.out.println(testAccount);
		Assertions.assertEquals("My Test Worked!",testAccount.getAccName());
	}

	// Negative test
	@Test
	@Order(9)
	void balanceIsNegative() {	
		Exception e = assertThrows(NegativeBalanceException.class, ()->{
			Account testAccount = new Account(5,1,"TestAccount1",10.25);
			aserv.addAccount(testAccount);
			aserv.increaseBalance(testAccount, -1000);		
		});
		Assertions.assertEquals("Account balance cannot be less than 0", e.getMessage());
	}
	
	@AfterAll
	static void tearDown() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_bankdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
}
