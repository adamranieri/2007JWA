package dev.laurent.daos;

import java.util.Set;

import dev.laurent.entities.Customer;

//Data Access Object is designed to perform CRUD operations on the entity
//You should only be dealing with Customer objects in the Customer DAO. Don't this DAO to try and save anything else!

//You want your method signatures to be as object centric as possible
public interface CustomerDAO {
	
	//Create
	Customer createCustomer(Customer customer); // think of this as a save method
		
	//Read
	Customer getCustomerById(int id);
	Set<Customer> getAllCustomers();
		
	//Update
	Customer updateCustomer(Customer customer);
		
	//Delete
	boolean deleteCustomer(int id);
	
	
	
}
