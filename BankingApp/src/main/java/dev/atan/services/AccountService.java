package dev.atan.services;

import java.util.List;
import dev.atan.entities.Account;
import dev.atan.exceptions.NegativeBalance;

public interface AccountService {
	
	Account createAccount(Account account) throws NegativeBalance;
	Account getAccountById(int id);
	Account updateAccount(Account account) throws NegativeBalance;
	boolean deleteAccountById(int id);
	List<Account> getAllAccounts();
	List<Account> getAccountsByBalanceLessThan(int lessThan);
	List<Account> getAccountsByBalanceGreaterThan(int greaterThan);
	
	
	Account renameAccount(Account account, String newName);
	List<Account> getAccountsByCustomerId(int id);
}
