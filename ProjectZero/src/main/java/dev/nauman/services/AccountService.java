package dev.nauman.services;

import java.util.Set;

import dev.nauman.entities.Account;

public interface AccountService {

	Account createAccount(Account account);
	
	Account getAccountByAId(int aId);
	Set<Account> getAllAccounts();
	Set<Account> getAccountsByCId(int cId);
	Set<Account> getAllAccountsWithBalanceLessThan(double amount);
	Set<Account> getAllAccountsWithBalanceGreaterThan(double amount);
	Set<Account> getAccountsByCIdWithBalanceLessThan(int cId, double amount);
	Set<Account> getAccountsByCIdWithBalanceGreaterThan(int cId, double amount);
	Account getCustomerAccountByAId(int cId, int aId);
	
	double checkBalance(int aId);
	
	Account updateAccount(Account account);
	Account deposit(Account account, double amount);
	Account withdraw(Account account, double amount);
	
	boolean customerOwnedAccount(int cId, Account account);
	
	boolean deleteAccountByAId(int aId);
	boolean deleteAccountsByCId(int cId);
}