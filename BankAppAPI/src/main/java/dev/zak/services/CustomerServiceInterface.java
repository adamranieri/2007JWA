package dev.zak.services;

import java.util.Set;

import dev.zak.entities.Customer;


public interface CustomerServiceInterface {
	public Customer createCustomer(Customer c);
	
	public Customer updateCustomer(Customer c);
	
	public Set<Customer> getAllCustomers();
	public Customer getCustomerById(int id);
	
	public int deleteCustomerById(int id);
	public boolean deleteCustomerByIdWithAllAccount(int id);
	
	public Set<Customer> getCustomerByUserName(String userName);
}
