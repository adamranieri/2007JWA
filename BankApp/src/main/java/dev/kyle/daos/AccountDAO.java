package dev.kyle.daos;

import java.util.Set;

import dev.kyle.entities.Account;

public interface AccountDAO {
	
	// Create
	Account createAccount(Account a);
	
	// Read
	Account getAccountById(int aid);
	Set<Account> getAllAccounts();
	Set<Account> getAccountsByCustomerId(int id);
	
	// Update
	Account updateAccount(Account a);
	
	// Delete
	boolean deleteAccount(int aid);

}
