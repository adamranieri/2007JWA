package dev.alsabea.services;

import java.util.List;

import dev.alsabea.entities.Account;
import dev.alsabea.entities.Customer;

public interface CustomerServices {
	
	int create( Customer t) ;
	
	boolean delete(int id);
	
	Customer retrieveById(int i);
	List<Customer> retrieveAll();
	List<Customer> retrieveByUsername(String username);
	
	boolean update ( Customer t);
	
	List<Account> getCustomerAccounts(int i);


	
	
}
