package dev.kurt.services;

import java.util.Set;

import dev.kurt.entities.Customer;

public interface CustomerService {
	
		Customer createCustomer(Customer customer);
		
		Customer getCustomerById(int id);
		Customer getCustomerByUsername(String username);
		Set<Customer> getAllCustomers();
		
		Customer updateCustomer(Customer customer);
		
		boolean deleteCustomerById(int id);
		
}
