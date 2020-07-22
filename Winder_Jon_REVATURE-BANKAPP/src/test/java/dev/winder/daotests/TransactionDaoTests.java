package dev.winder.daotests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


import dev.winder.bankappexceptions.InsufficientFundsException;
import dev.winder.daos.BankAccountDAO;
import dev.winder.daos.BankAccountDAOMaria;
import dev.winder.daos.BankTransactionDAO;
import dev.winder.daos.BankTransactionDAOMaria;
import dev.winder.entities.BankAccount;
import dev.winder.entities.Transaction;

import org.junit.jupiter.api.Order;


@TestMethodOrder(OrderAnnotation.class)

class TransactionDaoTests {
	
	BankTransactionDAO tDao= BankTransactionDAOMaria.getBankTransactionDAOMaria();
	
	BankAccountDAO bDao = BankAccountDAOMaria.getBankAccountDAOMaria();
	
	@Test
	@Order(1)
	public void depositFunds() {
		
		BankAccount bankAccount = bDao.getBankAccountByAid(2);
		
		Transaction transaction = new Transaction(0,bankAccount.getAccountBalance(),0.00,bankAccount.getAccountId());
		
		Transaction check = new Transaction();
		check= tDao.depositFunds(transaction, 675.56);
		
		Assertions.assertEquals(51158.23, check.getFinalBalance());
		
	}
	
	@Test
	@Order(2)
	public void withdrawal() throws InsufficientFundsException {
		
		BankAccount bankAccount = bDao.getBankAccountByAid(2);
		
		Transaction transaction = new Transaction(0,bankAccount.getAccountBalance(),0.00,bankAccount.getAccountId());
		Transaction check = new Transaction();
		
		check =tDao.withdrawal(transaction, 675.56);
		
		Assertions.assertEquals(50482.67, check.getFinalBalance());
		
		
	}
	

	
	



}
