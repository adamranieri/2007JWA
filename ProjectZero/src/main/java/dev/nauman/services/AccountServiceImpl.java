package dev.nauman.services;

import java.util.HashSet;
import java.util.Set;

import dev.nauman.daos.AccountDAO;
import dev.nauman.daos.AccountDAOImpl;
import dev.nauman.entities.Account;
import dev.nauman.exceptions.NegativeBalanceException;

public class AccountServiceImpl implements AccountService{

	private static AccountDAO adao = AccountDAOImpl.getAccountDAOImpl();
	
	@Override
	public Account createAccount(Account account) {
		return adao.createAccount(account);
	}
	@Override
	public Set<Account> getAllAccounts() {
		return adao.getAllAccounts();
	}
	@Override
	public Account getAccountByAId(int aId) {
		return adao.getAccountByAId(aId);
	}
	@Override
	public Account updateAccount(Account account) {
		return adao.updateAccount(account);
	}
	@Override
	public boolean deleteAccountByAId(int aId) {
		return adao.deleteAccountByAId(aId);
	}
	@Override
	public Set<Account> getAccountsByCId(int cId) {
		return adao.getAccountsByCId(cId);
	}
	@Override
	public double checkBalance(int aId) {
		return adao.getAccountByAId(aId).getBalance();
	}
	@Override
	public Account deposit(Account account, double amount) {
		
		try {
			account.setBalance(account.getBalance()+amount);
			return adao.updateAccount(account);
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Account withdraw(Account account, double amount) {
		
		try {
			account.setBalance(account.getBalance()-amount);
			return adao.updateAccount(account);
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Set<Account> getAllAccountsWithBalanceLessThan(double amount) {
		Set<Account> allAccounts = this.getAllAccounts();
		Set<Account> accounts = new HashSet<Account>();
		
		for(Account account : allAccounts) {
			if(account.getBalance() < amount) {
				accounts.add(account);
			}
		}
		return accounts;
	}
	@Override
	public Set<Account> getAllAccountsWithBalanceGreaterThan(double amount) {
		Set<Account> allAccounts = this.getAllAccounts();
		Set<Account> accounts = new HashSet<Account>();
		
		for(Account account : allAccounts) {
			if(account.getBalance() > amount) {
				accounts.add(account);
			}
		}
		return accounts;
	}
	@Override
	public Set<Account> getAccountsByCIdWithBalanceLessThan(int cId, double amount) {
		Set<Account> accountsWithCId = this.getAccountsByCId(cId);
		Set<Account> accounts = new HashSet<Account>();
		
		for(Account account : accountsWithCId) {
			if(account.getBalance() < amount) {
				accounts.add(account);
			}
		}
		return accounts;
	}
	@Override
	public Set<Account> getAccountsByCIdWithBalanceGreaterThan(int cId, double amount) {
		Set<Account> accountsWithCId = this.getAccountsByCId(cId);
		Set<Account> accounts = new HashSet<Account>();
		
		for(Account account : accountsWithCId) {
			if(account.getBalance() > amount) {
				accounts.add(account);
			}
		}
		return accounts;
	}
	@Override
	public boolean deleteAccountsByCId(int cId) {
		Set<Account> accounts = this.getAccountsByCId(cId);
		
		if(accounts.size() == 0)
			return false;
		for(Account account : accounts) {
			if(!this.deleteAccountByAId(account.getaId())) {
				return false;
			}
		}
		return true;
	}
	@Override
	public Account getCustomerAccountByAId(int cId, int aId) {
		Account account = this.getAccountByAId(aId);
		
		if(this.customerOwnedAccount(cId, account)) {
			return account;
		}
		return null;
	}
	@Override
	public boolean customerOwnedAccount(int cId, Account account) {
		Set<Account> accounts = this.getAccountsByCId(cId);
		
		for(Account a: accounts) {
			if(a.equals(account)) {
				return true;
			}
		}
		return false;
	}
}