package dev.rutan.services;

import java.util.Set;

import dev.rutan.entities.Customer;

public interface CustomerService {

	//CRUD
	Customer createCustomer(Customer customer);
	Customer getCustomerById(int cId);
	Set<Customer> getAllCustomers();
	Customer updateCustomer(Customer customer);
	boolean deleteCustomer(int cId);
	
	// Other
	Set<Customer> getAllCustomersByUsername(String username);
	 
}
