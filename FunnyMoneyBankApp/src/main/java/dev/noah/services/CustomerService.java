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
	Customer createCustomer(String username, String password) throws CustomerCreationLengthException;
	
	//Read
	Set<Customer> getAllCustomers();
	Customer getCustomerByCId(int i);
	
	//Update
	Customer changeCustomerUsernameById(int id, String username);
	Customer changeCustomerPasswordById(int id, String password);

	//Destroy
	Boolean deleteCustomerByCId(int id);
	
}
