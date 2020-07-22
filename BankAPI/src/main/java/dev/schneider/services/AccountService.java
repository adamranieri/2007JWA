package dev.schneider.services;

import java.util.Set;

import dev.schneider.entities.Account;
import dev.schneider.exceptions.NegativeBalanceException;


public interface AccountService {
	//CRUD like operation
	Account addAccount(Account account);
	Set<Account> getAccountsByCustomer(int cID);
	Account getAccountById(int id);
	Account updateAccount(Account account) throws NegativeBalanceException;
	boolean deleteAccount(int id);
	
	Set<Account> balanceLessThan(Set<Account> bigSet, int num);
	Set<Account> balanceGreaterThan(Set<Account> bigSet, int num);
}
