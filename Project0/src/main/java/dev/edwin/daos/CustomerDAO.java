package dev.edwin.daos;

import java.util.List;

import dev.edwin.entities.Customer;

public interface CustomerDAO 
{
//	CRUD Operations
	
//	CREATE
	Customer createCustomer(Customer customer);
	
//	READ by 1 or All
	Customer getCustomerById(int cId);
	List<Customer> getCustomers();
	List<Customer> getCustomerByUsername(String username);
	
	
//	UPDATE
	Customer updateCustomer(Customer customer);
	
//	DELETE
	boolean deleteCustomer(int cId);
	
	
}
