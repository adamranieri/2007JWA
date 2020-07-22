package dev.winder.daos;

import java.util.Set;

import dev.winder.entities.Customer;

public interface CustomerDAO {
	
	//Create
	Customer createCustomer(Customer customer);
	
	//Read
	Set<Customer> getAllCustomers();
	Customer getCustomerByCustomerId(int id);
	Customer getCustomerByUsername(String username);
	
	//Update
	Customer updateCustomer(Customer customer);

	//Delete
	boolean deleteCustomerById(int id);
	
	

}
