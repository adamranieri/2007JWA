package dev.nauman.daos;

import java.util.Set;

import dev.nauman.entities.Account;

public interface AccountDAO {

	Account createAccount(Account account);
	
	Account getAccountByAId(int aId);
	Set<Account> getAllAccounts();
	Set<Account> getAccountsByCId(int id);
	
	Account updateAccount(Account account);
	
	boolean deleteAccountByAId(int aId);
}