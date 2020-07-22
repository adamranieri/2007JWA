package dev.cosentino.daos;

import java.util.Set;

import dev.cosentino.resources.Account;

public interface AccountDAO {
	
	Account createAccount(Account account);
	
	Account getAccountById(int id);
	Set<Account> getAccountsByCustomerId(int id);
	Set<Account> getAllAccounts();
	
	Account updateAccount(Account account);
	
	boolean deleteAccount(int id);

}
