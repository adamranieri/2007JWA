package dev.kyle.daos;

import java.util.Set;

import dev.kyle.entities.Customer;

public interface CustomerDAO {
	  	  
	  // Create
	  Customer createCustomer(Customer c);
	  
	  // Read
	  Customer getCustomerById(int cid);
	  Set<Customer> getAllCustomers();
	  
	  // Update
	  Customer updateCustomer(Customer c);
	  
	  // Delete
	  boolean deleteCustomer(int cid);
}
