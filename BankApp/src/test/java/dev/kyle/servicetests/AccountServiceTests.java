package dev.kyle.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.kyle.daos.CustomerDAO;
import dev.kyle.daos.CustomerDAOMaria;
import dev.kyle.entities.Account;
import dev.kyle.entities.Customer;
import dev.kyle.exceptions.NegativeBalanceException;
import dev.kyle.services.AccountService;
import dev.kyle.services.AccountServiceImpl;
import dev.kyle.utils.ConnectionUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountServiceTests {

	private static AccountService aserv = new AccountServiceImpl();
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
	void testAddAccount() {
		Account checking = new Account(1, 1, "Kyle's Checking 3", 0.0);
		aserv.addAccount(checking);
		Assertions.assertNotEquals(0, checking.getAid());
	}
	
	@Test
	@Order(2)
	void testChangeBalance() throws NegativeBalanceException {
		Account checking = aserv.getAccountById(1);
		aserv.changeBalance(checking, 25);
		Assertions.assertEquals(25, checking.getBalance());
	}
	
	@Test
	@Order(3)
	void testRenameAccount() {
		Account checking = aserv.getAccountById(1);
		aserv.changeName(checking, "Savings");
		Assertions.assertEquals("Savings", checking.getAccName());
	}

	@Test
	@Order(4)
	void testNegativeBalance() {
		Exception e = assertThrows(NegativeBalanceException.class, ()-> {
			Account checking = aserv.getAccountById(1);
			aserv.changeBalance(checking, -1000);
		});
		
		Assertions.assertEquals("Balance cannot be below 0!", e.getMessage());
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