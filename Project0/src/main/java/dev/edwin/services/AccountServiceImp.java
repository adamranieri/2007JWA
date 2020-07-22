package dev.edwin.services;

import java.util.ArrayList;
import java.util.List;

import dev.edwin.daos.AccountDAO;
import dev.edwin.daos.AccountDAOImp;
import dev.edwin.entities.Account;
import dev.edwin.exceptions.NegativeBalanceException;
import dev.edwin.exceptions.NegativeValueException;

public class AccountServiceImp implements AccountService 
{
	private static AccountService aserv = null;
	private static AccountDAO aDao = AccountDAOImp.getAccountDAO();
	
	private AccountServiceImp()
	{
		super();
	}
	
	public static AccountService getAccountService()
	{
		if(aserv == null)
			aserv = new AccountServiceImp();
			
		return aserv;
	}

	@Override
	public Account openNewAccount(Account account) {
		return aDao.createAccount(account);
	}

	@Override
	public Account getAccountById(int aId) {
		return aDao.getAccountById(aId);
	}

	@Override
	public List<Account> getAllAccounts() {
		return aDao.getAllAccounts();
	}
	
	@Override
	public List<Account> getAccountsByCustomerId(int id) {
		return aDao.getAccountsByCustomerId(id);
	}
	

	@Override
	public Account updateAccount(Account account) {
		return aDao.updateAccount(account);
	}

	@Override
	public boolean closeAccount(Account account) {
		return closeAccount(account.getaId());
	}

	@Override
	public boolean closeAccount(int aId) {
		return aDao.deleteAccount(aId);
	}

	
//	Higher Level Business Logic
	@Override
	public Account depositToAccount(Account account, Double amount) throws NegativeValueException {
//		Should catch case: no negative values
		if (amount < 0)
			throw new NegativeValueException();
		
		account.setBalance(account.getBalance() + amount);
		return updateAccount(account);
	}

	@Override
	public Account withdrawFromAccount(Account account, Double amount) throws NegativeBalanceException {
//		Should catch case: amount NOT greater than account.balance
		if(amount > 0)
			amount = amount * (-1);
	
		
		if(amount < (account.getBalance())* -1)
			throw new NegativeBalanceException();
		

		
		account.setBalance(account.getBalance() + amount);
		return updateAccount(account);
	}

//	@Override
//	public List<Account> transferMoney(Account fromAccount, Account toAccount, Double amount) throws NegativeBalanceException, NegativeValueException {
//		List<Account> accounts = new ArrayList<Account>();
//		accounts.add(withdrawFromAccount(fromAccount, amount));
//		accounts.add(depositToAccount(toAccount, amount));
//		
//		return accounts;
//	}

	@Override
	public List<Account> getAccountsWithBalanceAbove(int cid,Double amount) {
		List<Account> accounts = new ArrayList<Account>();
		accounts = aDao.getAccountsByCustomerId(cid);
		List<Account> result = new ArrayList<Account>();
		
		for(Account account : accounts)
		{
			if(account.getBalance()>amount)
				result.add(account);
				
		}
		return result;
	}

	@Override
	public List<Account> getAccountsWithBalanceBelow(int cid,Double amount) {
		List<Account> accounts = new ArrayList<Account>();
		accounts = aDao.getAccountsByCustomerId(cid);
		List<Account> result = new ArrayList<Account>();
		
		for(Account account : accounts)
		{
			if(account.getBalance()<amount)
				result.add(account);
				
		}
		return result;
	}

	@Override
	public List<Account> getAccountsWithBalanceWithinRange(int cid,Double lowerLimit, Double upperLimit) {
		List<Account> accounts = new ArrayList<Account>();
		accounts = aDao.getAccountsByCustomerId(cid);
		List<Account> result = new ArrayList<Account>();
		
		for(Account account : accounts)
		{
			if(account.getBalance()>lowerLimit && account.getBalance()<upperLimit )
				result.add(account);
				
		}
		return result;
	}


	

}
