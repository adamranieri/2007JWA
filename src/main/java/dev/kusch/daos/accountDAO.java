package dev.kusch.daos;

import dev.kusch.entities.Account;

public interface accountDAO {
		
		// Create
		Account createAccount(Account account);
		
		// Read
		Account getAccountById(int id);
		
		// Update
		Account updateAccount(Account account);
		
		// Delete
		boolean deleteAccount(int id);
}
