package dev.atan.daos;

import java.util.List;

import dev.atan.entities.Account;
import dev.atan.entities.Customer;

public interface CustomerDAO {
	
	//Create
	Customer createCustomer(Customer customer); 
	
	//Read
	Customer getCustomerById(int id);
	Customer getCustomerByUserName(String userName);
	List<Customer> getAllCustomers();
	List<Account> getOpenAccountsPerCustomer(int id);
	
	//Update
	Customer updateCustomer(Customer customer);
	Customer updateCustomerAccounts(Customer customer);
	
	//Delete
	boolean deleteCustomerById(int id);
	boolean deleteCustomerByUserName(String userName);

	

}
