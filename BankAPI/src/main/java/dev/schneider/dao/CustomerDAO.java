package dev.schneider.dao;

import java.sql.SQLDataException;
import java.util.Set;

import dev.schneider.entities.Customer;

public interface CustomerDAO {
	
	//create
	Customer createCustomer(Customer customer);
	
	//read
	Set<Customer> getAllCustomer();
	Customer getCustomerByID(int cID);
	Customer getCustomerByUsername(String username);
	
	//update
	Customer updateCustomer(Customer customer);
	
	//delete
	boolean deleteCustomer(int cID);
}
