package dev.noah.services;

import java.util.Set;

import dev.noah.entities.Account;

public interface AccountService {

	//Create
	Account createAccount(Account account);
	
	//Read
	Account getAccountById(int id);
	Set<Account> getAccountsGreaterThanBal(boolean operator, double amount,Set<Account> allAccs);
	Set<Account> getBalancesByDifference(double greaterThan, double lessThan, Set<Account> allAccs);
	Set<Account> getAllCustomerAccountsByCId(int id);
	
	//Update
	Account updateAccount(Account account);
	
	
	//Destroy
	Boolean deleteAccountById(int id);
	
}
