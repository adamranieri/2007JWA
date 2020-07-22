package dev.kyle.services;

import java.util.Set;

import dev.kyle.entities.Account;
import dev.kyle.exceptions.NegativeBalanceException;

public interface AccountService {
	
	// CRUD-Like ops
	Account addAccount(Account a);
	Account getAccountById(int aid);
	Set<Account> getAllAccounts();
	Account updateAccount(Account a);
	boolean deleteAccount(int aid);
	
	// Higher Level Ops
	Account changeName(Account a, String newName);
	Account changeBalance(Account a, double newBal) throws NegativeBalanceException;
	Set<Account> balanceGreaterThan(int num);
	Set<Account> balanceLessThan(int num);
}
