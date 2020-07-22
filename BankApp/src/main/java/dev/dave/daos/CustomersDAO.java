package dev.dave.daos;

import java.util.Set;

import dev.dave.entities.Customers;

public interface CustomersDAO {
	
	// CREATE operations
	
	Customers createCustomer (Customers customer); // this will be used to persist object customer
	
	// READ operations
	
	Customers getCustByID (int ID);
	
	Customers getCustByUsername (String username);
	
	Set<Customers> getAllCustomers (); 
	
	// UPDATE operations
	
	Customers updateCustomer (Customers customer);
		
	// DELETE operations
	
	boolean deleteCustomer (int ID);

}
