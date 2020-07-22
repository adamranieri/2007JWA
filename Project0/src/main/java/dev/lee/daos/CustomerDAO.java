package dev.lee.daos;

import java.util.Set;

import dev.lee.entities.Customer;

public interface CustomerDAO {

	//CREATE
	Customer createCustomer(Customer customer);
	
	//READ
	Customer getCustomerById(int cId);
	Customer getCustomerByUsername(String username);
	Set<Customer> getAllCustomers();
	
	//UPDATE
	Customer updateCustomer(Customer customer);
	
	//DELETE
	boolean deleteCustomer(int cId);
}
