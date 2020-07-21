package dev.noah.daos;

import java.util.Set;

import dev.noah.entities.Account;

public interface AccountDAO {

	// Create
	Account createAccount(Account account);
	
	// Read
	Account getAccount(int id);
	Set<Account> getAllAccounts();
	Set<Account> getAllAccountsByCId(int id);
	
	// Update
	Account updateAccount(Account account);
	
	// Destroy
	Boolean deleteAccount(int id);
	
}
