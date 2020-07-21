package dev.noah.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import dev.noah.daos.AccountDAO;
import dev.noah.daos.AccountDAOMaria;
import dev.noah.daos.CustomerDAO;
import dev.noah.daos.CustomerDAOMaria;
import dev.noah.entities.Account;
import dev.noah.entities.Customer;
import dev.noah.exceptions.NegativeBalanceException;
import dev.noah.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class AccountDAOTests {

	public AccountDAO adao = AccountDAOMaria.getAccountDaoMaria();
	public CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();	
	@BeforeAll
	static void initialSetup() { 
		try(Connection con = ConnectionUtil.getConnection()){
			String sql = "CALL create_bank_tables";
			CallableStatement cs = con.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Positive Tests
	
	@Test
	@Order(1)
	void createAccount() {
		Customer customer = new Customer(0, "CustomerName", "Customer name");
		cdao.createCustomer(customer);
		Account acc = new Account(0, 1, "account name", 30000);
		adao.createAccount(acc);
		Assertions.assertEquals(1, adao.getAccount(1).getaId());
	}

	@Test
	@Order(2)
	void getAccount() {
		Account acc = adao.getAccount(1);
		Assertions.assertEquals(1, acc.getaId());
	}

	@Test
	@Order(3)
	void getAllAccounts() {
		Account acc = new Account(0,1, "Garry", 2000);
		adao.createAccount(acc);
		acc = new Account(0,1,"JoJos funny money", 1000);
		adao.createAccount(acc);
		Assertions.assertEquals(3, adao.getAllAccounts().size());
	}

	@Test
	@Order(4)
	void updateAccount() throws NegativeBalanceException {
		Account acc = adao.getAccount(1);
		acc.setBalance(4000);
		acc = adao.updateAccount(acc);
		Assertions.assertEquals(4000, acc.getBalance());
	}

	@Test
	@Order(5)
	void deleteAccount() {
		boolean result = adao.deleteAccount(1);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(6)
	void getAllAccountsByCId() {
		Customer customer = new Customer(0,"Deku","adasaddadaad");
		cdao.createCustomer(customer);
		Account account = new Account(0,2,"Best Funny Money Account", 5000);
		adao.createAccount(account);
		account = new Account(0,2,"Hero savings", 8000);
		adao.createAccount(account);
		account = new Account(0,2,"Hero balance", 100000);
		adao.createAccount(account);
		Assertions.assertEquals(3, adao.getAllAccountsByCId(2).size());
	}
	
	//Negative Tests
	
	@Test
	@Order(7)
	void NegChangeBalance() {
		
		Exception e = assertThrows(NegativeBalanceException.class, ()->{ //this way prevents an intended error from popping up into console
			Account account = adao.getAccount(2);
			account.setBalance(-100000);
		});
		
		
		Assertions.assertEquals("You cannot have a negative balance", e.getMessage());
	}
	
	@AfterAll
	static void cleanup() {
		try(Connection con = ConnectionUtil.getConnection()){
			String sql = "CALL drop_bank_tables";
			CallableStatement cs = con.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
