package dev.atan.services;

import java.util.List;
import dev.atan.daos.AccountDAO;
import dev.atan.daos.AccountDAOMaria;
import dev.atan.entities.Account;
import dev.atan.exceptions.NegativeBalance;


public class AccountServiceImpl implements AccountService {

	private static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();
	
	@Override
	public Account createAccount(Account account) throws NegativeBalance {
		if(account.getaBalance()<0) {
			throw new NegativeBalance();
		}
		return adao.createAccount(account);
	}

	@Override
	public Account renameAccount(Account account, String newName) {
		account.setaName(newName);
		adao.updateAccount(account);
		return account;
	}


	@Override
	public Account getAccountById(int id) {
		return adao.getAccountById(id); 
	}

	@Override
	public Account updateAccount(Account account) throws NegativeBalance {
		if(account.getaBalance()<0) {
			throw new NegativeBalance();
		}
		return adao.updateAccount(account);
	}

	@Override
	public boolean deleteAccountById(int id) {	
		return adao.deleteAccount(id);
	}

	@Override
	public List<Account> getAllAccounts() {
		return adao.getAllAccounts();
	}

	@Override
	public List<Account> getAccountsByCustomerId(int id) {
		return adao.getAccountsByCustomerId(id);
	}

	@Override
	public List<Account> getAccountsByBalanceLessThan(int lessThan) {
		return adao.getAccountsByBalanceLessThan(lessThan);
	}

	@Override
	public List<Account> getAccountsByBalanceGreaterThan(int greaterThan) {
		return adao.getAccountsByBalanceGreaterThan(greaterThan);
	}


}