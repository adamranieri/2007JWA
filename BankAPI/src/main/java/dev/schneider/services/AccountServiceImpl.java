package dev.schneider.services;

import java.util.HashSet;
import java.util.Set;

import dev.schneider.dao.AccountDAO;
import dev.schneider.dao.AccountDAOImpl;
import dev.schneider.entities.Account;
import dev.schneider.exceptions.NegativeBalanceException;

public class AccountServiceImpl implements AccountService {
	
	private static AccountDAO adao = AccountDAOImpl.getAccountDAO();

	@Override
	public Account addAccount(Account account) {
		return adao.createAccount(account);
	}

	@Override
	public Set<Account> getAccountsByCustomer(int cID) {
		return adao.getAllCustomerAccounts(cID);
	}
	
	@Override
	public Account getAccountById(int id) {
		return adao.getAccountByAcctID(id);
	}

	@Override
	public Account updateAccount(Account account) throws NegativeBalanceException{
		if (account.getBalance() < 0) {
			throw new NegativeBalanceException();
		}
		return adao.updateAccount(account);
	}

	@Override
	public boolean deleteAccount(int id) {
		return adao.deleteAccount(id);
	}

	//higher level
	
	@Override
	public Set<Account> balanceLessThan(Set<Account> bigSet, int num) {
		Set<Account> smallAccounts = new HashSet<Account>();
		for(Account account : bigSet) {
			if (account.getBalance() < num) {
				smallAccounts.add(account);
			}
		}
		return smallAccounts;
	}

	@Override
	public Set<Account> balanceGreaterThan(Set<Account> bigSet, int num) {
		Set<Account> bigAccounts = new HashSet<Account>();
		for(Account account : bigSet) {
			if (account.getBalance() > num) {
				bigAccounts.add(account);
			}
		}
		return bigAccounts;
	}

}
