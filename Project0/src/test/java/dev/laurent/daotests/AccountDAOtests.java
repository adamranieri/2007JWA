package dev.laurent.daotests;

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

import dev.laurent.daos.AccountDAO;
import dev.laurent.daos.AccountDAOMaria;
import dev.laurent.entities.Account;
import dev.laurent.daos.CustomerDAO;
import dev.laurent.daos.CustomerDAOMaria;
import dev.laurent.entities.Customer;
import dev.laurent.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class AccountDAOtests {
	
	private static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria(); 
	private static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();

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
	void createAccount() {
		Customer testCustomer = new Customer(0,"username1", "password1");
		cdao.createCustomer(testCustomer);
		System.out.println(testCustomer);
		Account testAccount = new Account(0,1,"Test Account",10.25);
		adao.createAccount(testAccount);
		System.out.println(testAccount);
		Assertions.assertEquals("Test Account", testAccount.getAccName());
	}
	
	@Test
	@Order(2)
	void getAllAccounts() {
		Set<Customer> customers = cdao.getAllCustomers();
		Assertions.assertEquals(1,customers.size());
	}
	
	@Test
	@Order(3)
	void getAccountById() {
		Account testAccount = adao.getAccountById(1);
		Assertions.assertNotNull(testAccount);
		Assertions.assertEquals(1, testAccount.getAccId());
	}

	@Test
	@Order(4)
	void getAccountsByCustomerId() {		
		Set<Account> accounts = adao.getAccountsByCustomerId(1);
		Assertions.assertEquals(1, accounts.size());		
	}
	
	@Test
	@Order(5)
	void updateAccount() {
		Account testAccount = adao.getAccountById(1);
		testAccount.setAccName("UpdatedUsername");
		testAccount = adao.updateAccount(testAccount); //saves the changes to that customer
		Assertions.assertEquals("UpdatedUsername", testAccount.getAccName());	
	}
	
	@Test
	@Order(6)
	void deleteAccountById() {
		boolean result = adao.deleteAccountById(1);
		Assertions.assertEquals(true, result);
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