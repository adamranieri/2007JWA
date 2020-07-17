package dev.kusch.daos;

import java.util.Set;

import dev.kusch.entities.Customer;

public interface CustomerDAO {

	// Create
	Customer createCustomer(Customer customer);
	
	// Read
	Customer getCustomerById(int id);
	Set<Customer> getAllCustomers();
	Customer getCustomerByUser(String username);
	
	// Update
	Customer updateCustomer(Customer customer);
	
	// Delete
	boolean deleteCustomer(int id);
	
}
