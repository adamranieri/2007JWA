package dev.edwin.daos;

import java.util.List;

import dev.edwin.entities.Account;

public interface AccountDAO 
{
//	CRUD Operations
	
//	CREATE
	Account createAccount(Account account);
	
//	READ by 1 or All
	Account getAccountById(int aId);
	List<Account> getAccountsByCustomerId(int cId);	
	List<Account> getAllAccounts();
	
	
//	UPDATE
	Account updateAccount(Account account);
	
//	DELETE
	boolean deleteAccount(int aId);
	
}
