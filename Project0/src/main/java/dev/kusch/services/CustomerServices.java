package dev.kusch.services;

import java.util.List;
import java.util.Set;

import dev.kusch.entities.Account;
import dev.kusch.entities.Customer;

public interface CustomerServices {

	// CRUD like operations
	Customer addCustomer(Customer customer);
	Set<Customer> getAllCustomers();
	Customer getCustomerById(int id);
	Customer getCustomerByUser(String username);
	Customer updateCustomer(Customer customer);
	boolean deleteCustomer(Customer customer);
}
