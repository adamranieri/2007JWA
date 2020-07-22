package dev.schneider.services;

import java.sql.SQLDataException;
import java.util.Set;

import dev.schneider.entities.Customer;

public interface CustomerService {
	//crud operations
	Customer createCustomer(Customer customer);
	Set<Customer> getAllCustomers();
	Customer getCustomerByID(int id) ;
	Customer updateCustomer(Customer customer);
	boolean deleteCustomer(int id);
	//higher level
	Customer getCustomerbyUsername(String username);
}
