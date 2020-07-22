package dev.lee.daos;

import java.util.Set;

import dev.lee.entities.Account;
import dev.lee.exceptions.NegativeBalanceException;

public interface AccountDAO {

	//CREATE
	Account createAccount(Account account) throws NegativeBalanceException;
	
	//READ
	Account getAccountByAccountId(int aId);
	Set<Account> getAccountsByCustomerId(int id);
	Set<Account> getAllAccounts();	
	Set<Account> getAccountsFilteredByBalance(int cId, String lower, String upper);
	
	//UPDATE
	Account updateAccount(Account account) throws NegativeBalanceException;
	
	//DELETE
	boolean deleteAccount(int aId);
}
