package dev.kusch.services;

import java.util.List;
import java.util.Set;

import dev.kusch.entities.Account;
import dev.kusch.entities.Customer;

public interface CustomerServices {

	// CRUD like operations
	
	// Create
	Customer addCustomer(Customer customer);
	
	// Read
	Set<Customer> getAllCustomers();
	Customer getCustomer(int id);
	Customer getCustomer(Customer customer);
	Set<Customer> getCustomer(String username);
	
	// Update
	Customer updateCustomer(Customer customer);
	
	// Delete
	boolean deleteCustomer(Customer customer);
	boolean deleteCustomer(int id);
}
