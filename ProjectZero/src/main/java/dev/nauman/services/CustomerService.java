package dev.nauman.services;


import java.util.Set;

import dev.nauman.entities.Customer;

public interface CustomerService {

	Customer createCustomer(Customer customer);
	
	Customer getCustomerByCId(int cId);
	Set<Customer> getAllCustomers();
	
	Customer updateCustomer(Customer customer);
	
	Customer changePassword(int cId, String password);
	Customer changeUsername(int cId, String username);
	
	boolean deleteCustomerByCId(int cId);
	boolean deleteCustomerByCustomer(Customer customer);
}