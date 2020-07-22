package dev.zak.daos;

import java.util.Set;

import dev.zak.entities.Account;

public interface AccountDaoInterface {

	public Account createAccount(int cid, Account a);
	
	public Account getAccountById(int cid, int id);
	
	public Set<Account> getAllAccounts();
	
	public Account updateAccount(int cid, Account a);
	
	public boolean deleteAccount(int cid, int id);
	
	public Set<Account> getAccountLessThan(int cid, int balance);
	public Set<Account> getAccountGreaterThan(int cid, int balance);
	public Set<Account> getAccountBalanceBtween(int cid, int balance1, int balance2);
	public Set<Account> getAllAccountsByCustomerId(int cid);

	public Account getAccountById(int id);

	public boolean deleteAccount(int id);

	public Account updateAccount(Account a);
}
