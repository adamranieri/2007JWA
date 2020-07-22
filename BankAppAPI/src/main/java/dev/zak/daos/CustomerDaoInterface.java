package dev.zak.daos;

import java.util.Set;

import dev.zak.entities.Customer;

public interface CustomerDaoInterface {

	public Customer createCustomer(Customer c);
	
	public Customer getCustomerById(int id);
	
	public Set<Customer> getAllCustomers();
	
	public Customer updateCustomer(Customer c);
	
	public boolean deleteCustomer(int id);
	
	public Set<Customer> getCustomerByUserName(String userName);
}
