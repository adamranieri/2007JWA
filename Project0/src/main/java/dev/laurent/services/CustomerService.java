package dev.laurent.services;

import java.util.Set;

import dev.laurent.entities.Customer;

public interface CustomerService {
	// CRUD
	
	// Create
	Customer addCustomer(Customer customer);
	
	// Read
	Customer getCustomerById(int id);
	
	// Update
	Customer updateCustomer(Customer customer);
	
	// Delete
	boolean deleteCustomerById(int id);
	
	Set<Customer> getAllCustomers();
	Customer getCustomerByUsername(String user);
}
