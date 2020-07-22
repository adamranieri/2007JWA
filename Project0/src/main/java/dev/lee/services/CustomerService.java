package dev.lee.services;

import java.util.Set;

import dev.lee.entities.Customer;

public interface CustomerService {
	
	Customer establishCustomer(Customer customer);
	
	Customer getCustomerById(int cId);
	Customer getCustomerByUsername(String username);
	
	// Gets all customers without account arrays
	Set<Customer> getAllCustomersWithoutAccounts();
	// Gets all customers with account arrays
	Set<Customer> getAllCustomers();
	
	Customer updateCustomer(Customer customer);
	
	boolean deleteCustomer(int cId);
	boolean deleteCustomer(Customer customer);
	
	Customer changeUsername(Customer customer, String newUsername);
	Customer changePassword(Customer customer, String newPassword);
	
	
	
}
