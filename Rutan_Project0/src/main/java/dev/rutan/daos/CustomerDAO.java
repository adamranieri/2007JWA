package dev.rutan.daos;

import java.util.Set;

import dev.rutan.entities.Customer;

public interface CustomerDAO {

	// create
	Customer createCustomer(Customer customer);
		
	// read
	Customer getCustomerById(int cId);
	Set<Customer> getAllCustomers();
		
	// update 
	Customer updateCustomer(Customer customer);
		
	// delete
	boolean deleteCustomer(int cId);
	
}
