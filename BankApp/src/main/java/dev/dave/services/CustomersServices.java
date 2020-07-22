package dev.dave.services;

import java.util.Set;

import dev.dave.entities.Customers;

public interface CustomersServices {

	//CRUD like operations offered as services for end user 
	
	Customers CustomerSignUp(Customers customer); // Create-like operation
	
	Customers GetCustInfo(int cID); // Read-like operation
	
	Customers SearchCustomerByName(String username);
	
	Customers updateCustomer (Customers customer); // update operation
	
	Customers ChangeCustName (Customers customer, String newUsername); // update-like operation

	Customers ChangePassword (Customers customer, String newPassword); // update-like operation
	
	boolean DeleteProfile (int cID); // delete-like operation
	
}
