package dev.kusch.daos;

import java.util.Set;

import dev.kusch.entities.Account;

public interface AccountDAO {
		
		// Create
		Account createAccount(Account account);
		
		// Read
		Account getAccountById(int id);
		Set<Account> getAllAccounts();
		
		// Update
		Account updateAccount(Account account);
		
		// Delete
		boolean deleteAccount(int id);
}
