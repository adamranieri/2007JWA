package dev.atan.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dev.atan.entities.Account;


public class AccountDAOImpl implements AccountDAO{
	
	private static AccountDAOImpl adao = null;
	
	private AccountDAOImpl() {
		
	};
	
	public static AccountDAOImpl getAccountDAO() {
		
		if(adao == null) {
			adao = new AccountDAOImpl();
			return adao;
		}else {
			return adao;
		}
		
	}

	
	private Map<Integer, Account> account_table = new HashMap<Integer, Account>();
	
	private int counter = 1;
	
	@Override
	public Account createAccount(Account account) {
		account.setaID(counter);
		this.account_table.put(account.getaID(), account);
		this.counter++;
		return account;
	}

	@Override
	public Account getAccountById(int id) {
		return account_table.get(id);
	}
	
	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<Account>(account_table.values());
		return accounts;
	}

	@Override
	public Account updateAccount(Account account) {
		account_table.put(account.getcID(), account);
		return account;
	}

	@Override
	public boolean deleteAccount(int id) {
		Account a = account_table.remove(id);
		if(a != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Account> getAccountsByCustomerId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountsByBalanceLessThan(int lessThan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountsByBalanceGreaterThan(int greaterThan) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
