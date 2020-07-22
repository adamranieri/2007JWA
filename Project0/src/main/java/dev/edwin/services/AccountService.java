package dev.edwin.services;

import java.util.List;

import dev.edwin.entities.Account;
import dev.edwin.exceptions.NegativeBalanceException;
import dev.edwin.exceptions.NegativeValueException;

public interface AccountService 
{

//	Basic CRUD Ops
	Account openNewAccount(Account account);
	Account getAccountById(int aId);
	List<Account> getAccountsByCustomerId(int id);
	List<Account> getAllAccounts();
	Account updateAccount(Account account);
	boolean closeAccount(Account account);
	boolean closeAccount(int aId);
	
	
//	Business 
	
//	Deposit Money into clients account
	Account depositToAccount(Account account, Double amount) throws NegativeValueException;
	
//	Withdraw money
	Account withdrawFromAccount(Account account, Double amount) throws NegativeBalanceException;
	
//	Transfer Money Between Accounts
//	List<Account> transferMoney(Account fromAccount, Account toAccount, Double amount) throws NegativeBalanceException, NegativeValueException;
	
//	Get all accounts ABOVE said amount
	List<Account> getAccountsWithBalanceAbove(int cid, Double amount);
	
//	Get all account BELOW said amount
	List<Account> getAccountsWithBalanceBelow(int cid, Double amount);
	
// 	Get accounts WITHIN RANGE
	List<Account> getAccountsWithBalanceWithinRange(int cid, Double lowerLimit, Double upperLimit);
	
}
