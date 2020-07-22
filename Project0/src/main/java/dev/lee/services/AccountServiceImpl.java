package dev.lee.services;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import dev.lee.daos.AccountDAO;
import dev.lee.daos.AccountDAOMaria;
import dev.lee.entities.Account;
import dev.lee.exceptions.NegativeBalanceException;

public class AccountServiceImpl implements AccountService{

	private static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();
	@Override
	public Account establishAccount(Account account) throws NegativeBalanceException {
		try {
			return adao.createAccount(account);
		}catch (NegativeBalanceException e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	@Override
	public Account getAccountById(int aId) {
		return adao.getAccountByAccountId(aId);
	}
	@Override
	public Set<Account> getAccountsByAccountName(String accountName) {
		Set<Account> accounts = adao.getAllAccounts();
		Set<Account> filteredAccounts = new HashSet<Account>();
		for(Account account: accounts) {
			if(account.getAccountName().compareTo(accountName) == 0) {
				filteredAccounts.add(account);
			}
		}
		return filteredAccounts;
	}
	@Override
	public Set<Account> getAllAccounts() {
		return adao.getAllAccounts();
	}
	
	@Override
	public Set<Account> getAccountsByCustomerId(int cId) {
		return adao.getAccountsByCustomerId(cId);
	}
	@Override
	public Set<Account> getAccountsFilteredByBalance(int cId, String lower, String upper) {
		return adao.getAccountsFilteredByBalance(cId, lower, upper);
	}
	@Override
	public Account updateAccount(Account account) throws NegativeBalanceException {
		return adao.updateAccount(account);
	}
	@Override
	public boolean deleteAccount(Account account) {
		int aId = account.getaID();
		return adao.deleteAccount(aId);
	}
	@Override
	public boolean deleteAccount(int aId) {
		return adao.deleteAccount(aId);
	}
	@Override
	public Account changeAccountName(Account account, String newName) {
		account.setAccountName(newName);
		try {
			return adao.updateAccount(account);
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Account addToBalance(Account account, BigDecimal amount) throws NegativeBalanceException {
		BigDecimal originalBalance = account.getBalance();
		BigDecimal newBalance = originalBalance.add(amount);
		if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
			throw new NegativeBalanceException();
		}
		account.setBalance(newBalance);
		return account;
	}

	@Override
	public Account getAccountByCidandAid(int cId, int aId) {
		Set<Account> accounts = adao.getAllAccounts();
		Account result = null;
		for(Account account: accounts) {
			if(account.getaID()== aId & account.getcID()== cId) {
				result = account;
				break;
			}
		}
		return result;
	}

}
