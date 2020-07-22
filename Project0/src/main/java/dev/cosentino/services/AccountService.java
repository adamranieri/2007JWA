package dev.cosentino.services;

import java.util.Set;

import dev.cosentino.exceptions.CustomerDoesNotExistException;
import dev.cosentino.exceptions.NegativeBalanceException;
import dev.cosentino.resources.Account;
import dev.cosentino.resources.Customer;

public interface AccountService {

	//create
	Account createAccount(Account account) throws CustomerDoesNotExistException;
	
	//read
	Account getAccountByAccountId(int id);
	Account getAccountByAccountId(Account account);
	Set<Account> getAllAccounts();
	Set<Account> getAccountsByCustomerId(int id) throws CustomerDoesNotExistException;
	
	//update
	Account updateAccount(Account account);
	
	//delete
	boolean deleteAccount(Account account);
	boolean deleteAccount(int id);

	Account updateBalance(Account account, float amount) throws NegativeBalanceException;
	Set<Account> getBalanceGreaterThan(Customer customer, float amount);
	Set<Account> getBalanceLessThan(Customer customer, float amount);
	Set<Account> getBalanceBetween(Customer customer, float lower, float upper);
}
