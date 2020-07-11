package dev.noah.daos;

import java.util.Set;

import dev.noah.entities.Account;
import dev.noah.entities.Customer;

public interface CustomerDAO {

	
	// Create
	Customer createCustomer(Customer customer, String username, String password);
	Customer addAccount(Account account);
	
	// Read
	Customer getCustomerByCId(int id);
	Set<Customer> getAllCustomers(Customer customer);
	
	
	// Update
	Customer updateUsername(Customer customer);
	Customer updatePassword(Customer customer);
	
	
	
	
	// Delete
	boolean deleteCustomer(Customer customer);
	boolean removeAccount(int id);
	
}
