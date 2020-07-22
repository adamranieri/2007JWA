package dev.nauman.daos;


import java.util.Set;

import dev.nauman.entities.Customer;

public interface CustomerDAO {

	Customer createCustomer(Customer customer);
	
	Customer getCustomerById(int cId);
	Set<Customer> getAllCustomers();
	
	Customer updateCustomer(Customer customer);
	
	boolean deleteCustomerById(int cId);
}