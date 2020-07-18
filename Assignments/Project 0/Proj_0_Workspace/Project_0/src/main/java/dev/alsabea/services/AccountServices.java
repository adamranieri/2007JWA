package dev.alsabea.services;

import java.util.List;

import dev.alsabea.entities.Account;
import dev.alsabea.entities.Customer;
import dev.alsabea.exceptions.NegativeBalanceException;

public interface AccountServices {

	int create( Account t) throws NegativeBalanceException;
	
	boolean delete(int id);
	
	Account retrieveById(int id);
	
	boolean update ( Account t) throws NegativeBalanceException;
	
	List<Account> balanceLessThan(int id, int balance);
	
	List<Account> balanceGreaterThan(int id, int balance);
	
	
	
}
