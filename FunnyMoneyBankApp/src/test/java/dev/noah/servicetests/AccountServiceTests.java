package dev.noah.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import dev.noah.entities.Account;
import dev.noah.entities.Customer;
import dev.noah.services.AccountService;
import dev.noah.services.AccountServiceImpl;
import dev.noah.services.CustomerService;
import dev.noah.services.CustomerServiceImpl;
import dev.noah.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class AccountServiceTests {

	private static AccountService aserv =  new AccountServiceImpl();
	private static CustomerService cserv = new CustomerServiceImpl();
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


	@Test 
	@Order(1)
	void CreateAccount() {
		Customer customer = new Customer(0,"tttttttt", "Password");
		cserv.createCustomer(customer);
		Account acc = new Account(0,1,"jerry", 3000);
		acc = aserv.createAccount(acc);
		Assertions.assertNotEquals(0, aserv.getAccountById(1));
	}
	
	@Test
	@Order(2)
	void getAccount() {
		Account acc = aserv.getAccountById(1);
		Assertions.assertEquals(1, acc.getaId());
	}
	
	
	@Test
	@Order(3)
	void getAllAccountsGreaterThan() {
		Account account = new Account(0,1, "Jar jar",5000);
		aserv.createAccount(account);
		Set<Account> accounts = aserv.getAccountsGreaterThanBal(true, 4000,cserv.getCustomerByCId(1).getAccounts());
		Assertions.assertEquals(1, accounts.size());
	}
	
	@Test
	@Order(4) 
	void getAllAccountsLessThan(){
		Set<Account> accounts = aserv.getAccountsGreaterThanBal(false, 3500, cserv.getCustomerByCId(1).getAccounts());
		Assertions.assertEquals(1, accounts.size());
	}

	@Test
	@Order(5)
	void getAllAccountsGreaterThanAndLessThanBal() {
		Account acc = new Account(0,1,"REEEEEEEEEEE", 7000);
		aserv.createAccount(acc);
		acc = new Account(0,1,"SAOSKE", 1000);
		aserv.createAccount(acc);
		Set<Account> accounts = aserv.getBalancesByDifference(2000, 6000, cserv.getCustomerByCId(1).getAccounts());
		Assertions.assertEquals(2, accounts.size());
	}
	
	@Test
	@Order(6)
	void updateAccount() {
		Account acc = aserv.getAccountById(3);
		acc.setAccountName("SHORESEY");
		aserv.updateAccount(acc);
		Assertions.assertEquals("SHORESEY", aserv.getAccountById(3).getAccountName());
	}
	
	@Test
	@Order(7)
	void deleteAccount() {
		boolean result = aserv.deleteAccountById(1);
		Assertions.assertEquals(true, result);
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
