package dev.rutan.services;

import java.util.Set;

import dev.rutan.entities.Account;
import dev.rutan.exceptions.NegativeBalanceException;

public interface AccountService {

	// CRUD
	Account createAccount(Account account);
	
	Account getAccountById(int aId);
	Set<Account> getAllAccounts();
	Set<Account> getAllAccountsByCustomerId(int cId);
	
	Account updateAccount(Account account);
	
	boolean deleteAccount(int aId);
	
	// Higher Level
	
	Set<Account> getAccountsGreaterThan(int balance, int cId);
	Set<Account> getAccountsLessThan(int balance, int cId);
	Set<Account> getAccountsBetween(int lowerBalance, int upperBalance, int cId);
	boolean deleteAccountsByCustomerId(int cId);
	
}
