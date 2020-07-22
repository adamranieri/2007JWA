package dev.kyle.services;

import java.util.Set;

import dev.kyle.entities.Customer;

public interface CustomerService {
	
	// CRUD-Like Operations
	
	Customer addCustomer(Customer c);
	Customer getCustomerById(int cid);
	Customer updateCustomer(Customer c);
	boolean deleteCustomerById(int cid);
	Set<Customer> getAllCustomers();
	
	// Higher level Operations

	Customer changeUsername(Customer c, String newUsername);
	Customer changePassword(Customer c, String newPassword);
	Customer getCustomerByName(String name);
}
