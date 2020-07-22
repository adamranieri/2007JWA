package dev.cosentino.services;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter;

import dev.cosentino.daos.AccountDAO;
import dev.cosentino.daos.AccountDAOMaria;
import dev.cosentino.daos.CustomerDAO;
import dev.cosentino.daos.CustomerDAOMaria;
import dev.cosentino.exceptions.CustomerDoesNotExistException;
import dev.cosentino.exceptions.NegativeBalanceException;
import dev.cosentino.resources.Account;
import dev.cosentino.resources.Customer;

public class AccountServiceImpl implements AccountService{

	private static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();
	private static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();
	
	@Override
	public Account createAccount(Account account) throws CustomerDoesNotExistException{
		Customer customer = cdao.getCustomerById(account.getCustomerId());
		if(customer == null)
			throw new CustomerDoesNotExistException();
		return adao.createAccount(account);
	}

	@Override
	public Account getAccountByAccountId(int id) {
		Account account = adao.getAccountById(id);
		if(account == null) {
			return null;
		}
		return account;
	}

	@Override
	public Account getAccountByAccountId(Account account) {
		return getAccountByAccountId(account.getAccountId());
	}

	@Override
	public Set<Account> getAllAccounts() {
		return adao.getAllAccounts();
	}

	@Override
	public Set<Account> getAccountsByCustomerId(int id) throws CustomerDoesNotExistException {
		return adao.getAccountsByCustomerId(id);
	}

	@Override
	public Account updateAccount(Account account) {
		return adao.updateAccount(account);
	}

	@Override
	public boolean deleteAccount(Account account) {
		return deleteAccount(account.getAccountId());
	}

	@Override
	public boolean deleteAccount(int id) {
		Account account = adao.getAccountById(id);
		if(account == null)
			return false;
		Customer customer = cdao.getCustomerById(account.getCustomerId());
		if(customer == null)
			return false;
		return adao.deleteAccount(id);
	}

	@Override
	public Account updateBalance(Account account, float amount) throws NegativeBalanceException{
		float balance = account.getBalance() + amount;
		if(balance < 0) {
			throw new NegativeBalanceException();
		}
		
		account.setBalance(balance);
		adao.updateAccount(account);
		return account;
	}

	@Override
	public Set<Account> getBalanceGreaterThan(Customer customer, float amount) {
		Set<Account> accounts = new HashSet<Account>();
		
		for(Account account : adao.getAccountsByCustomerId(customer.getCustomerId())) {
			if(account.getBalance() > amount) {
				accounts.add(account);
			}
		}
		
		return accounts;
		
	}

	@Override
	public Set<Account> getBalanceLessThan(Customer customer, float amount) {
		Set<Account> accounts = new HashSet<Account>();
		
		for(Account account : adao.getAccountsByCustomerId(customer.getCustomerId())) {
			if(account.getBalance() < amount) {
				accounts.add(account);
			}
		}
		
		return accounts;
	}
	
	@Override
	public Set<Account> getBalanceBetween(Customer customer, float lower, float upper){
		Set<Account> accounts = new HashSet<Account>();
		
		for(Account account : adao.getAccountsByCustomerId(customer.getCustomerId())) {
			if(account.getBalance() < lower && account.getBalance() > upper) {
				accounts.add(account);
			}
		}
		
		return accounts;
	}

}
