package dev.rutan.daos;

import java.util.Set;

import dev.rutan.entities.Account;

public interface AccountDAO {

	// create
	Account createAccount(Account account);
	
	// read
	Account getAccountById(int aId);
	Set<Account> getAllAccounts();
	Set<Account> getAllAccountsByCustomerId(int cId);
	
	// update 
	Account updateAccount(Account account);
	
	// delete
	boolean deleteAccount(int aId);
	boolean deleteAllAccountsByCustomerId(int cId);
}
