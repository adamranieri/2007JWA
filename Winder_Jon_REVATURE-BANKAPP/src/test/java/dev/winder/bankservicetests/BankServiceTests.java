package dev.winder.bankservicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


import dev.winder.bankappexceptions.InsufficientFundsException;
import dev.winder.entities.BankAccount;
import dev.winder.entities.Customer;
import dev.winder.entities.Transaction;
import dev.winder.services.BankServiceImpl;
import dev.winder.services.BankServices;
import dev.winder.services.CustomerService;
import dev.winder.services.CustomerServiceImpl;

import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class BankServiceTests {

	private static BankServices bserv = new BankServiceImpl();
	
	private static CustomerService cserv = new CustomerServiceImpl();
	
	

	
	@Test
	@Order(1)
	public void createBankAccount() {
		
		Customer customer = new Customer(0,"George Lucas","9123");
		cserv.createNewCustomer(customer);
		BankAccount bankAccount = new BankAccount(0, customer.getCustomerName(),268312.56,customer.getCustomerId());
		bserv.createBankAccount(bankAccount);
		Assertions.assertNotEquals(0, bankAccount.getAccountId());		

	}//end J-Unit test
	
	@Test
	@Order(2)
	public void getIndividualAccountByAid() {
		
		//test the last account we created to see if we can retrieve it from DB
		BankAccount bankAccount = bserv.getBankAccountByAid(2);
		
		Assertions.assertNotNull(bankAccount);
		Assertions.assertEquals(2, bankAccount.getAccountId());
		
	}//end J-Unit test
	
	
	@Test
	@Order(3)
	public void getAccountByUsername() {
		
		Customer customer = cserv.getCustomerByCustomerId(3);
		
		Customer customernamecheck = cserv.getCustomerByUsername(customer.getCustomerName());
		
		Assertions.assertEquals("Joey S", customernamecheck.getCustomerName());
	}
	
	
	@Test
	@Order(4)
	public void getAllBankAccountsInBranch() {
		Set<BankAccount>bankAccounts = bserv.getAllBankAccountsInBranch();
		Assertions.assertEquals(2, bankAccounts.size());
	}//end J-Unit test
	
	@Test
	@Order(5)
	public void getAllAccountsOfCustomer() {
		
		Customer customer = cserv.getCustomerByCustomerId(3);
		BankAccount bankAccount = new BankAccount(0,"Savings",128436.72,customer.getCustomerId());
		bserv.createBankAccount(bankAccount);
		customer.addToCurrentCustomerAccounts(bankAccount);
		Set<BankAccount>bankAccounts = bserv.getAllCurrentCustomerAccounts(customer.getCustomerId());
		Assertions.assertEquals(2,bankAccounts.size());
	}//end J-Unit test
	
	
	
	//BankAccount updateBankAccountName(BankAccount bankAccount,String name);
	
	@Test
	@Order(6)
	public void updateBankAccountName() {
		
		BankAccount bank = bserv.getBankAccountByAid(4);
		bserv.updateBankAccountName(bank, "Movie Budget");
		Assertions.assertEquals("Movie Budget", bank.getAccountName());
		
	}//end J-Unit test
	
	//BankAccount returnBalanceByAid(int aid)
	
	@Test
	@Order(7)
	public void returnBalanceByAid() {
		
		BankAccount bank = bserv.getBankAccountByAid(4);
		BankAccount bankValCheck = new BankAccount();
		bankValCheck =bserv.returnBalanceByAid(bank.getAccountId());
		Assertions.assertEquals(268312.56,bankValCheck.getAccountBalance());
		
	}//end J-Unit test
	
	@Test
	@Order(8)
	//delete individual account
	public void deleteBankAccount() {
		
		Boolean accountByIdDeleted = false;
		BankAccount bankAccount = bserv.getBankAccountByAid(5);
		accountByIdDeleted = bserv.deleteBankAccount(bankAccount.getAccountId());
		Assertions.assertEquals(true, accountByIdDeleted);
		
	}//end J-Unit test
	
	//remove every account with a specific c-id, then remove the customer from the customer table
	@Test
	@Order(9)
	public void removeThisCustomersAccounts() {
		
		Boolean allThisCustomersAcctDel = false;
		Customer noLonger = cserv.getCustomerByCustomerId(4);
		allThisCustomersAcctDel = bserv.removeThisCustomersAccounts(noLonger.getCustomerId());
		Assertions.assertEquals(true, allThisCustomersAcctDel);
		
	}//end J-Unit test
	
	@Test
	@Order(10)
	//Positive check
	public void depositFunds() throws InsufficientFundsException {
		
		BankAccount bank = bserv.getBankAccountByAid(2);
		Transaction transaction = new Transaction(0,bank.getAccountBalance(),0,bank.getAccountId());
		Transaction assertCheck = new Transaction();
		assertCheck = bserv.depositFunds(transaction, 278.89);
		Assertions.assertEquals(50761.56, assertCheck.getFinalBalance());
		
		}//end J-Unit test
	
	
	@Test
	@Order(11) //negative depositFunds check
	//throw an exception object through a lambda expression assign it to depositAssertCheck then call the getMessage()
	public void negativeDepositFunds() throws InsufficientFundsException {
			
			Exception depositAssertCheck = assertThrows(InsufficientFundsException.class, ()->{
				BankAccount bank = bserv.getBankAccountByAid(2);
				Transaction transaction = new Transaction(0,bank.getAccountBalance(),0,bank.getAccountId());
				Transaction assertCheck = new Transaction();
				assertCheck = bserv.depositFunds(transaction, -1236.67);
					
			});
			
			Assertions.assertEquals("Due to insufficient funds, your transaction could not be completed", depositAssertCheck.getMessage());
		}//end J-Unit test
	
	
	@Test
	@Order(12)
	
	public void withdrawalFunds() throws InsufficientFundsException {
		
		BankAccount bank = bserv.getBankAccountByAid(2);
		Transaction transaction = new Transaction(0,bank.getAccountBalance(),0,bank.getAccountId());
		Transaction assertCheck = new Transaction();
		assertCheck = bserv.withdrawal(transaction, 14282.42);
		Assertions.assertEquals(36479.14, assertCheck.getFinalBalance());
	}//end J-Unit test
	
	@Test
	@Order(13)
	public void overdrawal() throws InsufficientFundsException {
		
		Exception depositAssertCheck = assertThrows(InsufficientFundsException.class, ()->{
			BankAccount bank = bserv.getBankAccountByAid(2);
			Transaction transaction = new Transaction(0,bank.getAccountBalance(),0,bank.getAccountId());
			Transaction assertCheck = new Transaction();
			assertCheck = bserv.withdrawal(transaction, bank.getAccountBalance()+100);
				
		});
		
		Assertions.assertEquals("Due to insufficient funds, your transaction could not be completed", depositAssertCheck.getMessage());
	}//end J-Unit test
	
	
	

}
