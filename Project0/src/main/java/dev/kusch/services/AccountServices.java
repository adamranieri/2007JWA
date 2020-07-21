package dev.kusch.services;

import java.util.Set;

import dev.kusch.entities.Account;
import dev.kusch.exceptions.NegativeBalanceException;

public interface AccountServices {

	// CRUD like operations
	
	// Create
	Account startAccount(Account account);
	
	// Read
	Set<Account> getAllAccounts();
	Account getAccount(int aid);
	Account getAccount(Account account);
	Set<Account> getAccountsByCustomer(int cid);
	Set<Account> getAccountsLessThan(int bound, int cid);
	Set<Account> getAccountsGreaterThan(int bound, int cid);
	Set<Account> getAccountsBetween(int lowerBound, int upperBound, int cid);
	
	// Update
	Account updateAccount(Account account) throws NegativeBalanceException;
	
	// Delete
	boolean deleteAccount(Account account);
	boolean deleteAccount(int id);
}
