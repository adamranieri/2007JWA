package dev.kurt.services;

import java.util.Set;

import dev.kurt.entities.Account;
import dev.kurt.entities.Customer;
import dev.kurt.exceptions.NegativeBalanceException;

public interface AccountService {
	
	Account openAccount(Account account) throws NegativeBalanceException;
	
	Account getAccountById(int id);
	Set<Account> getAccountsByCustomerId(int id);
	Set<Account> getAccountsByCustomerId(Customer customer);
	Set<Account> getAllAccounts();
	Set<Account> getAccountsByBalanceLessThan(int num, int id);
	Set<Account> getAccountsByBalanceGreaterThan(int num, int id);
	Set<Account> getAccountsBetweenBalances(int greatQ, int lessQ, int id);
	
	
	Account updateAccount(Account account) throws NegativeBalanceException;
	
	
	boolean deleteAccountById(int id);

	
	
}
