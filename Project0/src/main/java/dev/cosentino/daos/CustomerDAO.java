package dev.cosentino.daos;

import java.util.Set;

import dev.cosentino.resources.Customer;

public interface CustomerDAO {

	Customer createCustomer(Customer customer);
	
	Customer getCustomerById(int id);
	Customer getCustomerByUsername(String username);
	Set<Customer> getAllCustomers();
	
	Customer updateCustomer(Customer customer);
	
	boolean deleteCustomer(int id);
}
