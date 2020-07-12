package dev.noah.services;

import java.util.Set;

import dev.noah.entities.Customer;
import dev.noah.exceptions.CustomerCreationLengthException;

public interface CustomerService {

	//Create
	/*
	 * This method creates a customer
	 * @param id
	 * @param username
	 * @param password
	 * @param accounts
	 * @return
	 */
	Customer createCustomer(Customer customer) throws CustomerCreationLengthException;
	
	//Read
	Set<Customer> getAllCustomers(Customer customer);
	Customer getCustomer(Customer customer);
	
	//Update
	Customer changeCustomerUsername(Customer customer, String username);
	Customer changeCustomerPassword(Customer customer, String password);
	
	//Destroy
	Customer deleteCustomer(int id);
	
}
