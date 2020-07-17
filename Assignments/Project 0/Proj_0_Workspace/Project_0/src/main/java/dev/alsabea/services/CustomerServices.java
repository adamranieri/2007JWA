package dev.alsabea.services;

import java.util.List;

import dev.alsabea.entities.Account;
import dev.alsabea.entities.Customer;

public interface CustomerServices {

	
	int addCustomer(Customer c);
	
	Customer getCustomerById(int id);
	
	boolean deleteCustomer(int id);
	
	boolean updateCustomer(Customer c);
	
	List<Account> getCustomerAccounts(int i);
	
	
}
