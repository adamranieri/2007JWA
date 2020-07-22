package dev.cosentino.services;

import java.util.Set;

import dev.cosentino.exceptions.CustomerDoesNotExistException;
import dev.cosentino.resources.Account;
import dev.cosentino.resources.Customer;

public interface CustomerService {

	//Create
	Customer createNewCustomer(Customer customer);
	
	//Read
	Customer getCustomerByAccountId(int id);
	Customer getCustomerByAccountId(Account account);
	Customer getCustomerById(int id);
	Customer getCustomerByUsername(String username);
	Set<Customer> getAllCustomers();
	
	//update
	Customer updateCustomer(Customer customer) throws CustomerDoesNotExistException;
	
	//delete
	boolean deleteCustomer(int id);
	boolean deleteCustomer(Customer customer);
}
