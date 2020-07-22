package dev.rutan.services;

import java.util.HashSet;
import java.util.Set;

import dev.rutan.daos.AccountDAO;
import dev.rutan.daos.AccountDAOMaria;
import dev.rutan.entities.Account;
import dev.rutan.exceptions.NegativeBalanceException;

public class AccountServiceImpl implements AccountService{

	private static AccountDAO adao = AccountDAOMaria.getAccountDAO();
	
	@Override
	public Account createAccount(Account account) {
		try {
			if(account.getBalance() >= 0)
				return adao.createAccount(account);
			else {
				NegativeBalanceException negBalance = new NegativeBalanceException();
				throw negBalance;
			}
		} catch(Exception e) {
			return account;
		}
	}

	@Override
	public Account getAccountById(int aId) {
		return adao.getAccountById(aId);
	}

	@Override
	public Set<Account> getAllAccounts() {
		return adao.getAllAccounts();
	}

	@Override
	public Set<Account> getAllAccountsByCustomerId(int cId) {
		return adao.getAllAccountsByCustomerId(cId);
	}

	@Override
	public Account updateAccount(Account account) {
		try {
			if (account.getBalance() > 0)
				return adao.updateAccount(account);
			else {
				NegativeBalanceException negBalance = new NegativeBalanceException();
				throw negBalance;
			}
		} catch (Exception e) {
			return account;
		}
	}

	@Override
	public boolean deleteAccount(int aId) {
		return adao.deleteAccount(aId);
	}

	@Override
	public Set<Account> getAccountsGreaterThan(int balance, int cId) {
		Set<Account> accounts = new HashSet<Account>();
		
		for(Account account : adao.getAllAccountsByCustomerId(cId)) {
			if (account.getBalance() > balance)
				accounts.add(account);
		}
		
		return accounts;
	}

	@Override
	public Set<Account> getAccountsLessThan(int balance, int cId) {
		Set<Account> accounts = new HashSet<Account>();
		
		for(Account account : adao.getAllAccountsByCustomerId(cId)) {
			if (account.getBalance() < balance)
				accounts.add(account);
		}
		
		return accounts;
	}

	@Override
	public Set<Account> getAccountsBetween(int lowerBalance, int upperBalance, int cId) {
		Set<Account> accounts = new HashSet<Account>();
		
		for(Account account : adao.getAllAccountsByCustomerId(cId)) {
			if (account.getBalance() < upperBalance && account.getBalance() > lowerBalance)
				accounts.add(account);
		}
		
		return accounts;
	}

	@Override
	public boolean deleteAccountsByCustomerId(int cId) {
		return adao.deleteAllAccountsByCustomerId(cId);
	}

}
