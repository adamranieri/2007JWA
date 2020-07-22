package dev.winder.services;

import java.util.Set;

import dev.winder.entities.Customer;

public interface CustomerService {
	
	
	
	//Create
	Customer createNewCustomer(Customer customer);
	
	//Read
	Set<Customer> getAllCustomersInBranch();
	Customer getCustomerByCustomerId(int id);
	Customer getCustomerByUsername(String username);
	//Update
	Customer updateCustomerInfo(Customer customer);

	//Delete
	boolean deleteCustomerById(int id);
	
	
	
	
	
	

}
