package dev.kusch.services;

import java.util.Set;

import dev.kusch.entities.Account;

public interface AccountServices {

	// CRUD like operations
	
	// Create
	Account startAccount(Account account);
	
	// Read
	Set<Account> getAllAccounts();
	Account getAccount(int aid);
	Account getAccount(Account account);
	Account getAccountByCustomer(int cid);
	Set<Account> getAccountsLessThan(int bound, int cid);
	Set<Account> getAccountsGreaterThan(int bound, int cid);
	Set<Account> getAccountsBetween(int bound, int cid);
	
	// Update
	Account updateAccount(Account account);
	
	// Delete
	boolean deleteAccount(Account account);
	boolean deleteAccount(int id);
}
