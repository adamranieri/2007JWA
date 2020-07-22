package dev.atan.services;

import java.util.List;

import dev.atan.entities.Customer;

public interface CustomerService {

	//Create
		Customer createCustomer(Customer customer);
		
	//Read	
		Customer getCustomerById(int id);
		List<Customer> getAllCustomers();
		Customer getCustomerByUserName(String userName);
		
		
	//Update
		Customer updateCustomer(Customer customer);
		Customer renameCustomer(String oldName, String newName);
		Customer changePassword(Customer customer, String password);
		
		
	//Delete
		boolean deleteCustomerById(int id);
		boolean deleteCustomerByUserName(String userName);
		
		

		
		
}
