package dev.rutan.daotests;

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

import dev.rutan.daos.AccountDAO;
import dev.rutan.daos.AccountDAOMaria;
import dev.rutan.daos.CustomerDAO;
import dev.rutan.daos.CustomerDAOMaria;
import dev.rutan.entities.Account;
import dev.rutan.entities.Customer;
import dev.rutan.utils.ConnectionUtil;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class AccountDaoTests {
	
	public static AccountDAO adao = AccountDAOMaria.getAccountDAO();
	public static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();

	@BeforeAll
	static void setUp() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_bank_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			Customer customer = new Customer(0, "George", "password");
			cdao.createCustomer(customer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Positive tests
	@Test
	@Order(1)
	void createAccount() {
		Account sampAcnt = new Account(0, 1, "Savings", 600); // all entities have an id of zero until you save/create them
		adao.createAccount(sampAcnt);
		Assertions.assertNotEquals(0, sampAcnt.getaId());
	}

	@Test
	@Order(2)
	void getAcntById() {
		Account sampAcnt = adao.getAccountById(1); // all entities have an id of zero until you save/create them
		Assertions.assertEquals(1, sampAcnt.getaId());
	}
	
	@Test
	@Order(3)
	void getAllAccounts() {
		Customer customer = new Customer(0, "George", "password");
		cdao.createCustomer(customer);
		
		Account sampAcnt = new Account(0, 1, "Savings", 800);
		Account sampAcnt2 = new Account(0, 2, "Savings", 50);// all entities have an id of zero until you save/create them

		adao.createAccount(sampAcnt);
		adao.createAccount(sampAcnt2);
		Set<Account> accounts = adao.getAllAccounts();
		Assertions.assertEquals(3, accounts.size());
	}
	
	@Test
	@Order(4)
	void getAllAccountsByCustomerId() {
		Set<Account> accounts = adao.getAllAccountsByCustomerId(1);
		Assertions.assertEquals(2, accounts.size());
	}
	
	@Test
	@Order(5)
	void updateSchool() {
		Account acc = adao.getAccountById(1);
		acc.setAccountName("Checking");
		adao.updateAccount(acc);
		Assertions.assertEquals("Checking", acc.getAccountName());
	}
	
	@Test
	@Order(6)
	void deleteAccount() {
		boolean result = adao.deleteAccount(1);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(7)
	void deleteAllAccountsByCustomerId() {
		boolean result = adao.deleteAllAccountsByCustomerId(1);
		Assertions.assertEquals(true, result);
	}
	
	@AfterAll
	static void endTest() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
