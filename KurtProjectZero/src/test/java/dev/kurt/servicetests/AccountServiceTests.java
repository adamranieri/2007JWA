package dev.kurt.servicetests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.kurt.entities.Account;
import dev.kurt.entities.Customer;
import dev.kurt.exceptions.NegativeBalanceException;
import dev.kurt.services.AccountService;
import dev.kurt.services.AccountServiceImpl;
import dev.kurt.services.CustomerService;
import dev.kurt.services.CustomerServiceImpl;
import dev.kurt.utils.ConnectionUtil;


@TestMethodOrder(OrderAnnotation.class)
class AccountServiceTests {

	private static AccountService accServ = new AccountServiceImpl();
	private static CustomerService cusServ = new CustomerServiceImpl();
	
	@BeforeAll 
	static void setUp() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_kurtbankdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// Accounts cannot be added without a customer 
			Customer customer = new Customer(0, "JunitCustomer", "Test");
			cusServ.createCustomer(customer);
		}
	}
	
	
	@Test
	@Order(1)
	void openAccount() throws NegativeBalanceException{
		Account account = new Account(0,"Allowance money",20,1);
		accServ.openAccount(account);
		Account accountS = new Account(0,"Savings",9999,1);
		accServ.openAccount(accountS);
		Assertions.assertNotEquals(1, accountS.getaId());
	}
	
	@Test
	@Order(2)
	void getAccountById(){
		Account account = accServ.getAccountById(2);
		Assertions.assertEquals(9999,account.getAccountBalance());
	}
	
	@Test
	@Order(3)
	void getAccountsByCustomerId(){
		Set<Account> accounts = accServ.getAccountsByCustomerId(1);
		Assertions.assertEquals(2, accounts.size());
	}
	
	@Test
	@Order(3)
	void getAccountsByCustomerCustomer(){
		Customer customer = cusServ.getCustomerById(1);
		Set<Account> accounts = accServ.getAccountsByCustomerId(customer);
		Assertions.assertEquals(2, accounts.size());
	}
	
	
	@Test
	@Order(4)
	void getAllAccounts() throws NegativeBalanceException{
		Customer newCustomer = new Customer(0, "Trystin21", "Heygurl");
		cusServ.createCustomer(newCustomer);
		Account trystinsAccount = new Account(0,"Dirty moni", 100,2);
		accServ.openAccount(trystinsAccount);
		Set<Account> accounts = accServ.getAllAccounts();
		boolean isDifferent = false;
		
		for(Account acc: accounts) {
			if (acc.getcId() != 1){
				isDifferent = true;
			}
		}
		Assertions.assertEquals(true, isDifferent);
	}
	
	@Test
	@Order(5)
	void getAccountsByBalanceLessThan(){
		Set<Account> accounts = accServ.getAccountsByBalanceLessThan(200,1);
		Assertions.assertEquals(1, accounts.size());
	}
	
	@Test
	@Order(6)
	void getAccountsByBalanceGreaterThan(){
		Set<Account> accounts = accServ.getAccountsByBalanceGreaterThan(200,1);
		Assertions.assertEquals(1, accounts.size());
	}
	
	@Test
	@Order(7)
	void getAccountsBetweenBalances(){
		Set<Account> accounts = accServ.getAccountsBetweenBalances(10,25,1);
		Assertions.assertEquals(1, accounts.size());
	}
	
	@Test
	@Order(8)
	void updateAccount() throws NegativeBalanceException{
		Account account = accServ.getAccountById(1);
		double prevBalance = account.getAccountBalance();
		account.setAccountBalance(130);
		accServ.updateAccount(account);
		Assertions.assertNotEquals(prevBalance, account.getAccountBalance());
	}
	
	@Test
	@Order(9)
	void deleteAccountById(){
		boolean result = accServ.deleteAccountById(2);
		Assertions.assertEquals(result, true);
	}
	
	@Test
	@Order(10)
	void updateAccountZeroBalance(){
		
		Exception e = assertThrows(NegativeBalanceException.class, ()->{
			Account account = accServ.getAccountById(1);
			account.setAccountBalance(-20);
			accServ.updateAccount(account);
		});
		
		Assertions.assertEquals("Balance cannot be below Zero", e.getMessage());
		
	}
	
	void createAccountWithNegativeBalance() {
		
		Exception e = assertThrows(NegativeBalanceException.class, ()->{
			Account account = new Account(0,"This should break",-30,1);
			accServ.openAccount(account);
		});
		
		Assertions.assertEquals("Balance cannot be below Zero", e.getMessage());
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
