package dev.kyle.services;

import java.util.HashSet;
import java.util.Set;

import dev.kyle.daos.AccountDAO;
import dev.kyle.daos.AccountDAOMaria;
import dev.kyle.entities.Account;

import dev.kyle.exceptions.NegativeBalanceException;

public class AccountServiceImpl implements AccountService {

	// get Account CRUD ops
	private static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();
	
	@Override
	public Account addAccount(Account a) {
		return adao.createAccount(a);
	}

	@Override
	public Account getAccountById(int aid) {
		return adao.getAccountById(aid);
	}

	@Override
	public Account changeName(Account a, String newName) {
		a.setAccName(newName);
		adao.updateAccount(a);
		return a;
	}

	@Override
	public Account changeBalance(Account a, double newBal) throws NegativeBalanceException {
		if(newBal <  0) {
			if((newBal + a.getBalance()) < 0) {
				throw new NegativeBalanceException();
			}
		}
		
		a.setBalance(a.getBalance() + newBal);
		return a;
	}

	@Override
	public Set<Account> getAllAccounts() {
		return adao.getAllAccounts();
	}

	@Override
	public Account updateAccount(Account a) {
		return adao.updateAccount(a);
	}

	@Override
	public boolean deleteAccount(int aid) {
		return adao.deleteAccount(aid);
	}

	@Override
	public Set<Account> balanceGreaterThan(int num) {
		Set<Account> accountsGreater = new HashSet<Account>();
		
		for(Account a : adao.getAllAccounts()) {
			if(a.getBalance() > num ) {
				accountsGreater.add(a);
			}
		}
		return accountsGreater;
	}

	@Override
	public Set<Account> balanceLessThan(int num) {
		Set<Account> accountsLess = new HashSet<Account>();
		
		for(Account a : adao.getAllAccounts()) {
			if(a.getBalance() < num) {
				accountsLess.add(a);
			}
		}
		return accountsLess;
	}
	
}
