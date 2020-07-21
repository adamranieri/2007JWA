package dev.kurt.daos;

import java.util.Set;

import dev.kurt.entities.Customer;

public interface CustomerDAO {
	
	Customer createCustomer(Customer customer);
	
	Customer getCustomerById(int id);
	Customer getCustomerByUsername(String username);
	Set<Customer> getAllCustomers();
	
	Customer updateCustomer(Customer customer);
	
	boolean deleteCustomer(int id);

}
