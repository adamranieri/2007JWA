package dev.atan.daotests;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import dev.atan.daos.AccountDAO;
import dev.atan.daos.AccountDAOMaria;
import dev.atan.daos.CustomerDAO;
import dev.atan.daos.CustomerDAOMaria;
import dev.atan.entities.Account;
import dev.atan.entities.Customer;
import dev.atan.exceptions.NegativeBalance;


@TestMethodOrder(OrderAnnotation.class)
class TestsAccountDAO {
	
	public static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();
	public static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();
	
	@BeforeAll
	static void setUp() {
		
		try(Connection conn = dev.atan.utility.ConnectionUtil.getConnection()){
			String sql = "CALL SET_UP_BANK_DB";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
	@Test
	@Order(1)
	void createCustomer1() {
		Customer john = new Customer(0, "John", "Pass", 0);
		cdao.createCustomer(john);
		Assertions.assertNotEquals(0, john.getcID());
		}
	
	@Test
	@Order(2)
	void createCustomer2() {
		Customer sia = new Customer(0, "Sia", "Pass2", 0);
		cdao.createCustomer(sia);
		Assertions.assertNotEquals(0, sia.getcID());
		}
	
	
	
	@Test
	@Order(5)
	void createAccount1() throws NegativeBalance {
		Account one = new Account(0, 1, "Checking", 100);
		adao.createAccount(one);
		Assertions.assertNotEquals(0, one.getaID());
		}
	
	@Test
	@Order(6)
	void createAccount2() throws NegativeBalance {
		Account two = new Account(0, 2, "Saving", 300);
		adao.createAccount(two);
		Assertions.assertNotEquals(0, two.getaID());
		}
	
	@Test
	@Order(7)
	void getAccountById() {		
		Account one = adao.getAccountById(1);
		Assertions.assertEquals(1, one.getaID());
	}
	
	@Test
	@Order(10)
	void getAllAccounts() {
		List<Account> accounts = adao.getAllAccounts();
		Assertions.assertEquals(2, accounts.size());
	}
	
	@Test
	@Order(20)
	void updateAccount() {
		Account one = adao.getAccountById(1);
		one.setaName("Investment");
		one = adao.updateAccount(one);
		Assertions.assertEquals("Investment", one.getaName());	
	}
	
	@Test
	@Order(30)
	void deleteAccount() {
		boolean result = adao.deleteAccount(1);
		Assertions.assertEquals(true, result);
	}
	
	@AfterAll
	static void clearDown() {
		
		try(Connection conn = dev.atan.utility.ConnectionUtil.getConnection()){
			String sql = "CALL CLEAR_BANK_DB";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	} 
	
	

}
