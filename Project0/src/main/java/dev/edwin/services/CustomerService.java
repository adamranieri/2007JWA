package dev.edwin.services;

import java.util.List;

import dev.edwin.entities.Customer;

public interface CustomerService 
{
//	CRUD Ops
	
	Customer signUpNewCustomer(Customer customer);
	Customer getCustomerById(int cId);
	List<Customer> getAllCustomers();
	List<Customer> searchByUsername(String username);
	Customer updateCustomer(Customer customer);
	boolean deleteCustomer(int cId);
	boolean deleteCustomer(Customer customer);
}
