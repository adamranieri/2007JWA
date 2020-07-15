package dev.kusch.daos;

import java.util.Set;

import dev.kusch.entities.Account;

public class AccountDAOLocal implements AccountDAO {
	
	// Make this class in the singleton design pattern
	private static AccountDAOLocal dao = null;
	
	private AccountDAOLocal() {
		
	}
	
	public static AccountDAO getAccountDAO() {
		if (dao == null) {
			dao = new AccountDAOLocal();
			return dao;
		} else {
			return dao;
		}
	}

	@Override
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccountById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account updateAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAccount(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

}
