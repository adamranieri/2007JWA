package dev.noah.services;

import java.util.HashSet;
import java.util.Set;

import dev.noah.daos.AccountDAO;
import dev.noah.daos.AccountDAOMaria;
import dev.noah.daos.CustomerDAOMaria;
import dev.noah.entities.Account;

public class AccountServiceImpl implements AccountService {

	public static AccountDAO adao = AccountDAOMaria.getAccountDaoMaria();

	@Override
	public Account createAccount(Account account) {
		return adao.createAccount(account);
	}

	@Override
	public Account getAccountById(int id) {
		return adao.getAccount(id);
	}

	
	//Used for finding accounts greater than or less than an amount of money by changing the bool
	@Override
	public Set<Account> getAccountsGreaterThanBal(boolean isGreater, double amount, Set<Account> allAccs) {
		Set<Account> accounts = new HashSet<Account>();

		if (isGreater) {
			for (Account acc : allAccs) {

				// Adds account greater than amount to accounts set
				if (acc.getBalance() > amount) {
					accounts.add(acc);
				}
			}
		} else {
			for (Account acc : allAccs) {

				// Adds account greater than amount to accounts set
				if (acc.getBalance() < amount) {
					accounts.add(acc);
				}
			}
		}
		return accounts;

	}

	//returning accounts that fall within a range of money
	@Override
	public Set<Account> getBalancesByDifference(double greaterThan, double lessThan, Set<Account> allAccs) {
		Set<Account> accounts = new HashSet<Account>();
		for (Account acc : allAccs) {
			if (lessThan > acc.getBalance() && acc.getBalance() > greaterThan) { 
				accounts.add(acc);
			}
		}
		return accounts;
	}
	
	
	

	@Override
	public Account updateAccount(Account account) {
		return adao.updateAccount(account);
	}

	@Override
	public Boolean deleteAccountById(int id) {
		return adao.deleteAccount(id);
	}

	@Override
	public Set<Account> getAllCustomerAccountsByCId(int id) {
		return adao.getAllAccountsByCId(id);
	}


}
