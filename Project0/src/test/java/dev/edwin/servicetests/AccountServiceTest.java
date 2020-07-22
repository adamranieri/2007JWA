package dev.edwin.servicetests;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.edwin.entities.Account;
import dev.edwin.exceptions.NegativeBalanceException;
import dev.edwin.exceptions.NegativeValueException;
import dev.edwin.services.AccountService;
import dev.edwin.services.AccountServiceImp;
import dev.edwin.utils.ConnectionUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class AccountServiceTest {
// Methods that need testing:
//	Account openNewAccount(Account account);
//	Account getAccountById(int aId);
//	List<Account> getAccountsByCustomerId(int id);
//	List<Account> getAllAccounts();
//	Account updateAccount(Account account);
//	boolean closeAccount(Account account);
//	boolean closeAccount(int aId);
//	Account depositToAccount(Account account, Double amount) throws NegativeValueException;
//	Account withdrawFromAccount(Account account, Double amount) throws NegativeBalanceException;
//	Account checkAccountBalance(Account account);
//	List<Account> transferMoney(Account a1, Account a2, Double amount) throws NegativeBalanceException, NegativeValueException;
//	List<Account> getAccountsWithBalanceAbove(Double amount);
//	List<Account> getAccountsWithBalanceBelow(Double amount);
//	List<Account> getAccountsWithBalanceWithinRange(Double lowerLimit, Double upperLimit);

	private static AccountService aserv = AccountServiceImp.getAccountService();
	@Test
	@Order(1)
	void testOpenNewAccount() {
		Account account = new Account(0,2,"Long term savings",1000.99);
		Assertions.assertNotEquals(0, (aserv.openNewAccount(account)).getaId());
	}
	
	@Test
	@Order(2)
	void testGetAccountById()
	{
		Assertions.assertEquals("Checking", (aserv.getAccountById(2)).getAccountName());
	}
	
	@Test
	@Order(3)
	void testGetAccountsByCustomerId(){
		Assertions.assertNotEquals(0, (aserv.getAccountsByCustomerId(2)).size());
	}
	
	@Test
	@Order(4)
	void testGetAllAccounts()
	{
		Assertions.assertNotEquals(0, (aserv.getAllAccounts()).size());
	}
	
	@Test
	@Order(5)
	void testUpdateAccount() 
	{
		Account account = aserv.getAccountById(6);
		account.setAccountName("A new account name");
		Assertions.assertNotEquals("New Account One", (aserv.updateAccount(account)).getAccountName() );
	}
	
	@Test
	@Order(5)
	void testCloseAccount() 
	{
		Account account = aserv.getAccountById(2);
		Assertions.assertNotEquals(false, aserv.closeAccount(account));
	}
	
	@Test
	@Order(7)
	void testCloseAccountById() 
	{
		Assertions.assertNotEquals(false, aserv.closeAccount(4) );
	}

	@Test
	@Order(8)
	void negativeTestDepositToAccount()
	{
		Exception e = assertThrows(NegativeValueException.class, ()->{
			 aserv.depositToAccount(aserv.getAccountById(1), -3000.99);
		});
		
		Assertions.assertEquals("** EXCEPTION: Do not input negative values. **", e.getMessage());
	}
	
	@Test
	@Order(9)
	void posititveTestDepositToAccount()
	{
		try {
			Account result = aserv.depositToAccount(aserv.getAccountById(1), 100.99);
			Assertions.assertNotEquals(100.99,result.getBalance());
		} catch (NegativeValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(10)
	void negativeTestWithdraw()
	{
		Exception e = assertThrows(NegativeBalanceException.class, ()->{
			aserv.withdrawFromAccount(aserv.getAccountById(5), -100000.99);
		});
		
		Assertions.assertEquals("** EXCEPTION: The resulting action will cause an account to be negative. **", e.getMessage());

	}
	
	@Test
	@Order(11)
	void posititveTestWithdraw()
	{
		try {
			Account result = aserv.withdrawFromAccount(aserv.getAccountById(5),10.55);
			Assertions.assertNotEquals(0,result.getBalance());
		} catch (NegativeBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Test
	@Order(13)
	void testGetAccountWithBalanceAbove()
	{
		Assertions.assertEquals(1, aserv.getAccountsWithBalanceAbove(1,200.00).size());
	}
	
	
	@Test
	@Order(14)
	void testGetAccountBelowAmount()
	{
		Assertions.assertEquals(0, aserv.getAccountsWithBalanceBelow(1,100.00).size());
	}
	
	@Test
	@Order(15)
	void testGetAccountsWithBalanceWithinRange()
	{
		Assertions.assertEquals(1, aserv.getAccountsWithBalanceWithinRange(1,100.00,200.00).size());
	}
	
	@AfterAll
	static void finalizeForProd() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_project";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
