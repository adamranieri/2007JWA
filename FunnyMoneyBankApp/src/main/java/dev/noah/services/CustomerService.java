package dev.noah.services;

import java.util.Set;

import dev.noah.entities.Customer;

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
	Customer createCustomer(Customer cus);
	
	//Read
	Set<Customer> getAllCustomers();
	Customer getCustomerByCId(int i);
	Customer getCustomerByUsername(String username);
	
	//Update
	Customer updateCustomer(Customer customer);

	//Destroy
	Boolean deleteCustomerByCId(int id);
	
}
