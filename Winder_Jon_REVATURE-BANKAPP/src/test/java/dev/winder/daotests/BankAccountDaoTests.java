package dev.winder.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import dev.winder.bankappexceptions.InsufficientFundsException;
import dev.winder.daos.BankAccountDAO;
import dev.winder.daos.BankAccountDAOMaria;
import dev.winder.daos.CustomerDAO;
import dev.winder.daos.CustomerDAOMaria;
import dev.winder.entities.BankAccount;
import dev.winder.entities.Customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;



@TestMethodOrder(OrderAnnotation.class)

class BankAccountDaoTests  {
	
	//create the singleton instance of Bankaccountdaomaria with the interface reference
	private static BankAccountDAO bDao= BankAccountDAOMaria.getBankAccountDAOMaria();
	
	//establish customers that exist into our BankDB
	private static CustomerDAO cDao = CustomerDAOMaria.getCustomerDAOMaria();
	
	
	@Test
	@Order(1)
	public void createBankAccount() {
		
		//point to the exisiting customer the bank is currently working with
		Customer customer = cDao.getCustomerByCustomerId(1);
		
	
		//create the bankaccount we are adding to BankDB
		BankAccount bankAccount = new BankAccount(0,"Checking",2800.00,customer.getCustomerId());
		
		bDao.createBankAccount(bankAccount);
		
		//add our successfully created bankaccount to this.customer set
		//	customer.addToCurrentCustomerAccounts(bankAccount);
		
		Assertions.assertNotNull(bankAccount);
		Assertions.assertEquals(1, bankAccount.getAccountId());
		
	}//end J-Unit 1. TEST WAS CONFIRMED AS PASSED BY JONATHAN WINDER
	
	@Test
	@Order(2)
	//get a specific bank account by id
	public void getBankAccountByAid() {
		
		//test the last account we created to see if we can retrieve it from DB
		BankAccount bankAccount = bDao.getBankAccountByAid(1);
		
		Assertions.assertNotNull(bankAccount);
		Assertions.assertEquals(1, bankAccount.getAccountId());
		
		
	}//end J-Unit 2.
	
	@Test
	@Order(3)
	public void getAllBankAccountsInBranch() {
		
		Customer customerTwo = new Customer(0,"Joey S","8746");
		
		cDao.createCustomer(customerTwo);
		
		BankAccount bankAccount = new BankAccount(0,"Checking",50482.67,customerTwo.getCustomerId());
		
		bDao.createBankAccount(bankAccount);
		
		customerTwo.addToCurrentCustomerAccounts(bankAccount);
		
		Set<BankAccount>bankAccounts = bDao.getAllBankAccountsInBranch();
		
		//customer 2 got deleted. So the current SQL_AUTO INCREMENT would be creating the third customer at the 
		//bank opening their account.
		
	
		Assertions.assertEquals(2,bankAccounts.size());
	}//End J-Unit 3. TEST WAS CONFIRMED AS PASSED BY JONATHAN WINDER
	
	@Test
	@Order(4)
	public void returnEveryAccountForCustomer() {
	
		Customer customer = cDao.getCustomerByCustomerId(1);

		BankAccount bankAccount = new BankAccount(0,"Savings",128436.72,customer.getCustomerId());

		bDao.createBankAccount(bankAccount);
		
		customer.addToCurrentCustomerAccounts(bankAccount);
		
		Set<BankAccount>customerAccounts = bDao.getAllCurrentCustomerAccounts(1);
		
		Assertions.assertEquals(2, customerAccounts.size());
		
	}//END J-Unit 4. TEST WAS CONFIRMED AS PASSED BY JONATHAN WINDER
	
	@Test
	@Order(5)
	//update the name of the account, transactions will update the balance
	public void updateAccount() {
		
		BankAccount bankAccount = bDao.getBankAccountByAid(3);
		
		bankAccount.setAccountName("Kyles College Account");
		
		bankAccount = bDao.updateBankAccount(bankAccount);
		
		Assertions.assertEquals("Kyles College Account", bankAccount.getAccountName());
		
	}//END J-Unit 5. TEST WAS CONFIRMED AS PASSED BY JONATHAN WINDER
	
	@Test
	@Order(6)
	//Delete a single account
	public void deleteAccountByAid() {
		
		Boolean accountByIdDeleted = false;
		
		BankAccount bankAccount = bDao.getBankAccountByAid(3);
		
		accountByIdDeleted = bDao.deleteBankAccount(bankAccount.getAccountId());
		
		Assertions.assertEquals(true, accountByIdDeleted);
		
	}//END J-Unit 6. TEST WAS CONFIRMED AS PASSED BY JONATHAN WINDER
	
	@Test
	@Order(7)
	public void deleteThisCustomersAccounts() {
		
		
		Boolean allThisCustomersAcctDel = false;
		
		Customer noLonger = cDao.getCustomerByCustomerId(1);
		
		allThisCustomersAcctDel = bDao.removeThisCustomersAccounts(noLonger.getCustomerId());
		
		Assertions.assertEquals(true, allThisCustomersAcctDel);
		
		cDao.deleteCustomerById(noLonger.getCustomerId());
	}
	
	@Test
	@Order(7)
	public void getBalance() {
		
		BankAccount bankAccount = bDao.returnBalanceByAid(2);
		
		Assertions.assertEquals(50482.67, bankAccount.getAccountBalance());
		
		
	}


	
	
	
	
	

	

}
