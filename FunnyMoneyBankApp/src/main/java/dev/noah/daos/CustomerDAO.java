package dev.noah.daos;

import java.util.Set;

import dev.noah.entities.Account;
import dev.noah.entities.Customer;
import dev.noah.exceptions.CustomerCreationException;

public interface CustomerDAO {

	
	// Create
	Customer createCustomer(Customer customer) throws CustomerCreationException;
	Customer addAccount(Account account);
	
	// Read
	Customer getCustomerByCId(int id);
	Set<Customer> getAllCustomers();
	
	
	// Update
	Customer updateCustomer(Customer customer);

	
	
	
	
	// Delete
	boolean deleteCustomer(Customer customer);
	boolean removeAccount(int id);
	
}
