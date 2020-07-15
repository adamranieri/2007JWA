package dev.kusch.services;

import java.util.Set;

import dev.kusch.entities.Account;

public interface AccountServices {

	// CRUD like operations
	Account startAccount(Account account);
	Set<Account> getAllAccounts();
	Account getAccountById(int aid, int cid);
	Account updateAccount(Account account);
	boolean deleteAccount(Account account);
	
	// more business logic
	Set<Account> getAccountsLessThan(int bound);
	Set<Account> getAccountsGreaterThan(int bound);
	
}
