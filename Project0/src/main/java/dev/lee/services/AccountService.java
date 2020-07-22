package dev.lee.services;

import java.math.BigDecimal;
import java.util.Set;

import dev.lee.entities.Account;
import dev.lee.exceptions.NegativeBalanceException;

public interface AccountService {

	//CREATE
	Account establishAccount(Account account) throws NegativeBalanceException;
	
	//READ
	Account getAccountById(int aId);
	Set<Account> getAccountsByAccountName(String accountName);
	Set<Account> getAllAccounts();
	Set<Account> getAccountsByCustomerId(int cId);
	Account getAccountByCidandAid(int cId, int aId);
	Set<Account> getAccountsFilteredByBalance(int cId, String lower, String upper);
	
	//UPDATE
	Account updateAccount(Account account) throws NegativeBalanceException;
	
	//DELETE
	boolean deleteAccount(Account account);
	boolean deleteAccount(int aId);
	
	Account changeAccountName(Account account, String newName);
	Account addToBalance(Account account, BigDecimal amount) throws NegativeBalanceException;
	
}
