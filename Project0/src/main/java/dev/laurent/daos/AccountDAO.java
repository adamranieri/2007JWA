package dev.laurent.daos;

import java.util.Set;

import dev.laurent.entities.Account;

public interface AccountDAO {
	
	//Create
	Account createAccount(Account account);
	
	//Read
	Set<Account> getAllAccounts();
	Account getAccountById(int id);
	Set<Account> getAccountsByCustomerId(int id);
	
	//Update
	Account updateAccount(Account account);
	
	//Delete
	boolean deleteAccountById(int id);
}
