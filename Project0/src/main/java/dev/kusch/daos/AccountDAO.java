package dev.kusch.daos;

import java.util.Set;

import dev.kusch.entities.Account;

public interface AccountDAO {
		
		// Create
		Account createAccount(Account account);
		
		// Read
		Account getAccountById(int id);
		Set<Account> getAllAccounts();
		Set<Account> getAccountByCustomerId(int id);
		Set<Account> getAccountWithBalanceBetween(int id, double lowerBound, double upperBound);
		
		// Update
		Account updateAccount(Account account);
		
		// Delete
		boolean deleteAccount(int id);
}
