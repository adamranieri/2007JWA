package dev.dave.servicestests;

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

import dev.dave.entities.Accounts;
import dev.dave.entities.Customers;
import dev.dave.exceptions.NegativeBalanceException;
import dev.dave.exceptions.WithdrawException;
import dev.dave.services.AccountsServices;
import dev.dave.services.AccountsServicesImpl;
import dev.dave.services.CustomersServices;
import dev.dave.services.CustomersServicesImpl;
import dev.dave.utils.ConnectionUtil;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)

class AccountsServicesTests {
	
	private static AccountsServices aserv = AccountsServicesImpl.getAccountsServices();
	private static CustomersServices cserv = CustomersServicesImpl.getCustomersServices();

	// to set up our DB tables
	
		@BeforeAll
		static void setUp() {
			
			try(Connection conn = ConnectionUtil.getConnection()){
				String sql = "CALL set_up_bank_db";
				CallableStatement cs = conn.prepareCall(sql);
				cs.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	@Test
	@Order(1)
	void openAccount() {
		Customers babyface = new Customers(0, "babyface07", "Produce101");
		cserv.CustomerSignUp(babyface);
		Accounts revenue = new Accounts(0, 80000, "Annual Revenue", babyface.getcID());
		aserv.openAccount(revenue);
		Assertions.assertNotEquals(0, revenue.getaID());
	}
	
	@Test
	@Order(2)
	void pullUpAccount() {
		Accounts revenue = aserv.pullUpAccount(1);
		Assertions.assertEquals(1, revenue.getaID());
	}
	
	@Test
	@Order(3)
	void listAccounts() {
		// first, we create a couple more accounts for existing customer babyface
		Accounts investment = new Accounts(0, 100000, "Recording Contracts", 1);
		aserv.openAccount(investment);
		Accounts retirement = new Accounts(0, 50000, "Retirement Funds", 1);
		aserv.openAccount(retirement);
		Set<Accounts> allbabyfaceaccs = aserv.listAccounts(1); //now, we use our service to pull all the accounts associated to cID 1
		Assertions.assertEquals(3, allbabyfaceaccs.size()); // we expect that to be three, as babyface has three accounts under him
	}
	
	@Test
	@Order(4)
	void balanceInquiry() {
		Accounts revenue = aserv.pullUpAccount(1);
		aserv.balanceInquiry(revenue);
		Assertions.assertEquals(80000, revenue.getBalance());
	}
	
	@Test
	@Order(5)
	void updateCustomer() {
		Accounts retirement = aserv.pullUpAccount(3);
		retirement.setAccountname("la retraite");
		retirement = aserv.updateAccount(retirement);
		Assertions.assertEquals("la retraite", retirement.getAccountname());
	}
	
	@Test
	@Order(6)
	void makeDepositNegative() {
		Accounts revenue = aserv.pullUpAccount(1);
		try {
			aserv.makeDeposit(revenue, -30000); // we'll surround the method call with a try-catch block
		} catch (WithdrawException e) {
			e.printStackTrace(); // exception should be thrown in this case
		}
		Assertions.assertEquals(80000, revenue.getBalance()); // balance should remain the same
	}
	
	@Test
	@Order(7)
	void makeDeposit() {
		Accounts revenue = aserv.pullUpAccount(1);
		try {
			aserv.makeDeposit(revenue, 30000); // in this case we're surrounding the method call with a try-catch block
		} catch (WithdrawException e) {
			e.printStackTrace(); // it should throw an exception in this case
		}
		Assertions.assertEquals(110000, revenue.getBalance()); // balance should be increased
	}
	
	@Test
	@Order(8)
	void withdraw() {
		Accounts revenue = aserv.pullUpAccount(1); 
		try {
			aserv.withdraw(revenue, 10000); // calling withdraw method
		} catch (NegativeBalanceException e) { 
			e.printStackTrace();
		} 
		Assertions.assertEquals(100000, revenue.getBalance()); 
	}
	
	@Test
	@Order(9)
	void withdrawNegative() { 
		Accounts revenue = aserv.pullUpAccount(1); 
		try {
			aserv.withdraw(revenue, 200000); //it should throw an exception this time
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
		} 
		Assertions.assertEquals(100000, revenue.getBalance()); // balance shouldn't change
	}
	
	@Test
	@Order(10)
	void cancelAccount() {
		boolean isCanceled = aserv.cancelAccount(1);
		Assertions.assertEquals(true, isCanceled);
	}
	
	@Test
	@Order(11)
	void cancelAllAccounts() {
		boolean result = aserv.cancelAllAccounts(1); // we call the method to delete all remaining accounts from customer with cID 1: babyface
		Assertions.assertEquals(true, result); // the returned value should be true as expected, meaning something was deleted
		Customers babyface = cserv.GetCustInfo(1);
		Assertions.assertEquals(null, babyface);// babyface shouldn't exist if all his accounts were deleted
	}
	
	// to drop the tables after testing is done with
	
		@AfterAll
		static void tearDown() {
			
			try(Connection conn = ConnectionUtil.getConnection()){
				String sql = "CALL tear_down_bank_db";
				CallableStatement cs = conn.prepareCall(sql);
				cs.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
}
