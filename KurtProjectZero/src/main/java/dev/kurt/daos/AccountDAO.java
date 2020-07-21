package dev.kurt.daos;

import java.util.Set;

import dev.kurt.entities.Account;

public interface AccountDAO {

	//c
	Account createAccount(Account account);
	//r
	Account getAccountById(int id);
	Set<Account> getAllAccounts();
	Set<Account> getAccountsByCustomerId(int id);
	
	//u
	Account updateAccount(Account account);
	
	//d
	boolean deleteAccount(int id);
}
