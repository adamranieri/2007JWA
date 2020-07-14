package dev.kusch.daos;

import dev.kusch.entities.Customer;

public interface customerDAO {

	// Create
	Customer createCustomer(Customer customer);
	
	// Read
	Customer getCustomerById(int id);
	
	// Update
	Customer updateCustomer(Customer customer);
	
	// Delete
	boolean deleteCustomer(int id);
	
}
