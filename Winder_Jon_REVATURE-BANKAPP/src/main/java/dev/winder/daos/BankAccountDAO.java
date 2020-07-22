package dev.winder.daos;

import java.util.Set;

import dev.winder.bankappexceptions.InsufficientFundsException;
import dev.winder.entities.BankAccount;
import dev.winder.entities.Customer;

public interface BankAccountDAO {
	
	
	BankAccount createBankAccount(BankAccount bankAccount);
	//get bank account by aID
	BankAccount getBankAccountByAid(int id);
	
	//get all the bank accounts at this branch
	Set<BankAccount> getAllBankAccountsInBranch();
	//get all of the individuals accounts
	Set<BankAccount> getAllCurrentCustomerAccounts(int id);
	//only updates the name
	BankAccount updateBankAccount(BankAccount bankAccount);
	
	BankAccount returnBalanceByAid(int aid);
	//deletes individual account by aid
	Boolean deleteBankAccount(int aid);
	//removes every account with cid
	Boolean removeThisCustomersAccounts(int cid);
	

	
}
