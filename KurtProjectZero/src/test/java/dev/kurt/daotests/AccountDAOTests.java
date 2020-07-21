package dev.kurt.daotests;

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

import dev.kurt.daos.AccountDAO;
import dev.kurt.daos.AccountDAOMaria;
import dev.kurt.daos.CustomerDAO;
import dev.kurt.daos.CustomerDAOMaria;
import dev.kurt.entities.Account;
import dev.kurt.entities.Customer;
import dev.kurt.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class AccountDAOTests {

	private static AccountDAO accDao = AccountDAOMaria.getAccountDAOMaria(); 
	private static CustomerDAO cusDao = CustomerDAOMaria.getCustomerDAOMaria();

	@BeforeAll 
	static void setUp() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_kurtbankdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// Accounts cannot exist without a customer 
			Customer customer = new Customer(0,"Kurt","Password");
			cusDao.createCustomer(customer);
		}
		
	}
	
	
	@Test
	@Order(1)
	void createAccount() {
		Account check = new Account(0,"Checkings",2005.76,1);
		accDao.createAccount(check);
		Assertions.assertNotEquals(0, check.getaId());
	}
	
	@Test
	@Order(2)
	void getAccountById() {
		Account check = accDao.getAccountById(1);
		Assertions.assertNotNull(check);
		Assertions.assertEquals(1, check.getaId());
	}
	
	@Test
	@Order(3)
	void getAllAccounts() {
		Account save = new Account(0,"Savings",13,1);
		accDao.createAccount(save);
		Set<Account> accs = accDao.getAllAccounts();
		Assertions.assertEquals(2, accs.size());
		
	}
	
	@Test
	@Order(4)
	void getAccountsByCustomerId() {
		Set<Account> accs = accDao.getAccountsByCustomerId(1);
		for(Account acc: accs) {
			Assertions.assertEquals(cusDao.getCustomerById(1).getcId(), acc.getcId());
		}
	}
		
	@Test
	@Order(5)
	void updateAccount() {
		Account acc = accDao.getAccountById(1);
		String accNamePrev = acc.getAccountName();
		acc.setAccountName("Illegal laundered money from the ozarks");
		acc = accDao.updateAccount(acc);
		Assertions.assertNotEquals(accNamePrev, acc.getAccountName());
	}
	
		
	@Test
	@Order(6)
	void deleteAccount() {
		boolean result = accDao.deleteAccount(1);
		Assertions.assertEquals(true, result);
	
	}
	
	@AfterAll
	static void tearDown() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_kurtbankdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
