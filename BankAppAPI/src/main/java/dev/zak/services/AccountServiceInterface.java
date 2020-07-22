package dev.zak.services;

import java.sql.SQLException;
import java.util.Set;

import dev.zak.entities.Account;
import dev.zak.entities.Transaction;

public interface AccountServiceInterface {
	
	public Account createAccount(int cid, Account a);
	
	public Account updateAccount(int cid, Account a);
	
	public Set<Account> getAllAccounts();
	public Account getAccountById(int cid,int id);
	public Account getAccountById(int id);
	public boolean deleteAccountById(int cid,int id);
	boolean deleteAccountById(int id);

	public Set<Account> getAccountLessThan(int cid, int balance);
	public Set<Account> getAccountGreaterThan(int cid, int balance);
	public Set<Account> getAccountBalanceBtween(int cid, int balance1, int balance2);
	public Set<Account> getAllAccountsByCustomerId(int cid);
	public boolean transferMoney(int aIdFrom, int aIdTo, float amout) throws SQLException;

	
}
