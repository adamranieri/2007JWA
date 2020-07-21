package dev.kurt.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import dev.kurt.daos.AccountDAO;
import dev.kurt.daos.AccountDAOMaria;
import dev.kurt.daos.TransactionDAO;
import dev.kurt.daos.TransactionDAOMaria;
import dev.kurt.entities.Account;
import dev.kurt.entities.Customer;
import dev.kurt.entities.Transaction;
import dev.kurt.exceptions.NegativeBalanceException;

public class AccountServiceImpl implements AccountService {

	
	private static AccountDAO acdao = AccountDAOMaria.getAccountDAOMaria();
	private static TransactionDAO traDao = TransactionDAOMaria.getTransactionDAOMaria();
	
	@Override
	public Account openAccount(Account account) throws NegativeBalanceException {
		if(account.getAccountBalance() < 0) {
			throw new NegativeBalanceException();
		}
		return acdao.createAccount(account);
	}

	@Override
	public Account getAccountById(int id) {
		return acdao.getAccountById(id);
	}

	@Override
	public Set<Account> getAccountsByCustomerId(int id) {
		return acdao.getAccountsByCustomerId(id);
	}

	@Override
	public Set<Account> getAccountsByCustomerId(Customer customer) {
		return this.getAccountsByCustomerId(customer.getcId());
	}

	@Override
	public Set<Account> getAllAccounts() {
		return acdao.getAllAccounts();
	}


	@Override
	public Account updateAccount(Account account) throws NegativeBalanceException {
		if(account.getAccountBalance() < 0)
		{
			throw new NegativeBalanceException();
		}
		return acdao.updateAccount(account);
	}

	@Override
	public boolean deleteAccountById(int id) {
		return acdao.deleteAccount(id);
	}

	@Override
	public Set<Account> getAccountsByBalanceLessThan(int num, int id) {
		Set<Account> smallAccounts = new HashSet<Account>();
		for(Account account : acdao.getAccountsByCustomerId(id)){
			if(account.getAccountBalance() < num) {
				smallAccounts.add(account);
			}
		}
		return smallAccounts;
	}

	@Override
	public Set<Account> getAccountsByBalanceGreaterThan(int num, int id) {
		Set<Account> bigAccounts = new HashSet<Account>();
		for(Account account : acdao.getAccountsByCustomerId(id)){

			if(account.getAccountBalance() > num) {
				bigAccounts.add(account);
			}
		}
		return bigAccounts;
	}

	@Override
	public Set<Account> getAccountsBetweenBalances(int greatQ, int lessQ, int id) {
		Set<Account> middleAccounts = new HashSet<Account>();
		for(Account account : acdao.getAccountsByCustomerId(id)) {
			
			if((account.getAccountBalance()) > greatQ && (account.getAccountBalance() < lessQ)){
				
				middleAccounts.add(account);
			}
		}
		return middleAccounts;
	}

	@Override
	public Account updateAccountByTransaction(Account account, Transaction transaction)
			throws NegativeBalanceException {
		if(transaction.getFinalBalance() < 0)
		{
			throw new NegativeBalanceException();
		}
		
		account.setAccountBalance(transaction.getFinalBalance());
		
		return acdao.updateAccount(account);
	}

	@Override
	public Account validateAccountHistory(Account account) {
		ArrayList<Transaction> transactions = traDao.getTransactionsByAccountId(account.getaId());
		double net = 0;
		for(Transaction t: transactions) {
			net += t.getFinalBalance() - t.getPrevBalance();
		}
		return null;
	}

	
}
