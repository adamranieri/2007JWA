package dev.laurent.services;

import java.util.HashSet;
import java.util.Set;

import dev.laurent.daos.AccountDAO;
import dev.laurent.daos.AccountDAOMaria;
import dev.laurent.entities.Customer;
import dev.laurent.exceptions.NegativeBalanceException;
import dev.laurent.entities.Account;

public class AccountServiceImpl implements AccountService {

	private static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();
	
	@Override
	public Account addAccount(Account account) {
		return adao.createAccount(account);
	}

	@Override
	public Account getAccountById(int id) {
		return adao.getAccountById(id);
	}

	@Override
	public Set<Account> getAccountsByCustomerId(int id) {
		return adao.getAccountsByCustomerId(id);
	}

	@Override
	public Set<Account> getAccountsByCustomerId(Customer customer) {
		return this.getAccountsByCustomerId(customer.getcId());
	}

	@Override
	public Set<Account> getAllAccounts() {
		return adao.getAllAccounts();
	}

	@Override
	public Account updateAccount(Account account) {
		return adao.updateAccount(account);
	}

	@Override
	public boolean deleteAccount(int id) {
		return adao.deleteAccountById(id);
	}

	@Override
	public boolean deleteAccount(Account account) {
		return this.deleteAccount(account.getAccId());
	}
	
	@Override
	public Account increaseBalance(Account account, double amount) throws NegativeBalanceException {
		if( (amount + account.getBalance()) < 0)
			throw new NegativeBalanceException();
		account.setBalance(account.getBalance() + amount);
		return account;
	}
	
	@Override
	public Account renameAccount(Account account, String newName) {
		account.setAccName(newName);
		adao.updateAccount(account);
		return account;
	}
	
	@Override
	public Set<Account> getAccountsByBalanceLessThan(double num) {	
		Set<Account> smallAccounts = new HashSet<Account>();	
		for(Account account : adao.getAllAccounts()) {	
			if(account.getBalance()<num) {
				smallAccounts.add(account);
			}
		}	
		return smallAccounts;
	}
	
	@Override
	public Set<Account> getAccountsByBalanceGreaterThan(double num) {	
		Set<Account> largeAccounts = new HashSet<Account>();	
		for(Account account : adao.getAllAccounts()) {	
			if(account.getBalance()>num) {
				largeAccounts.add(account);
			}
		}	
		return largeAccounts;
	}
	
}
