package dev.laurent.services;

import java.util.Set;

import dev.laurent.entities.Customer;
import dev.laurent.exceptions.NegativeBalanceException;
import dev.laurent.entities.Account;

public interface AccountService {
	// CRUD
	
	// Create
	Account addAccount(Account account);
	
	// Read
	Account getAccountById(int id);
	Set<Account> getAccountsByCustomerId(int id);
	Set<Account> getAccountsByCustomerId(Customer customer);
	Set<Account> getAllAccounts();
	
	// Update
	Account updateAccount(Account account);
	
	// Delete
	boolean deleteAccount(int id);
	boolean deleteAccount(Account account);

	// Higher Level Services
	Account increaseBalance(Account account, double amount) throws NegativeBalanceException;
	Account renameAccount(Account account, String newName);
	Set<Account> getAccountsByBalanceLessThan(double num);
	Set<Account> getAccountsByBalanceGreaterThan(double num);
}
