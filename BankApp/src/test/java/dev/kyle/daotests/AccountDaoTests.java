package dev.kyle.daotests;

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

import dev.kyle.daos.AccountDAO;
import dev.kyle.daos.AccountDAOMaria;
import dev.kyle.daos.CustomerDAO;
import dev.kyle.daos.CustomerDAOMaria;
import dev.kyle.entities.Account;
import dev.kyle.entities.Customer;
import dev.kyle.utils.ConnectionUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountDaoTests {
	
	private static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();
	private static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();
	
	@BeforeAll
	static void setUp() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "CALL set_up_bank";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
			Customer kyle = new Customer(0, "Kylerv", "Password");
			cdao.createCustomer(kyle);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test 
	@Order(1)
	void createAccountTest() {
		Account checking = new Account(0, 1, "Kyles Checking Account",0.0);
		
		adao.createAccount(checking);
		Assertions.assertNotEquals(0, checking.getAid());
	}
	
	@Test
	@Order(2)
	void getAccountByIdTest() {
		Account checking = adao.getAccountById(1);

		Assertions.assertEquals(1, checking.getAid());
	}
	
	@Test
	@Order(3)
	void getAllAccountsTest() {
		Account savings = new Account(0, 1, "Kyles Saving Account",50.0);
		adao.createAccount(savings);
		
		Set<Account> Accounts = adao.getAllAccounts();
		
		Assertions.assertEquals(2, Accounts.size());
	}
	
	@Test
	@Order(4)
	void updateAccountTest() {
		Account checking = adao.getAccountById(1);
		checking.setAccName("Kyle's Super Checking Account");
		adao.updateAccount(checking);
		
		Assertions.assertEquals("Kyle's Super Checking Account", checking.getAccName());
		
	}
	
	@Test
	@Order(5)
	void deleteAccountTest() {
		boolean res = adao.deleteAccount(1);
		Assertions.assertEquals(true, res);
	}
	
	@Test
	@Order(6)
	void deleteAccountNegativeTest() {
		boolean res = adao.deleteAccount(10);
		Assertions.assertEquals(false, res);
	}
	
	@AfterAll
	static void tearDown() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "CALL tear_down_bank";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
