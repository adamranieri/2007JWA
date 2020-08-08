package dev.alsabea.services;

import java.util.List;

import dev.alsabea.entities.Account;
import dev.alsabea.exceptions.NegativeBalanceException;

public interface AccountServices {

	int create( Account t) throws NegativeBalanceException;
	
	boolean delete(int id);
	
	List<Account> retrieveAllAccounts(int custID);
	
	Account retrieveById(int id);
	
	boolean update ( Account t) throws NegativeBalanceException;
	
	List<Account> balanceLessThan(int balance, List<Account> list) throws NegativeBalanceException;;

	List<Account> balanceGreaterThan(int balance, List<Account> list) throws NegativeBalanceException;;
	
	
	
}