package dev.dave.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dev.dave.daos.AccountsDAO;
import dev.dave.daos.AccountsDAODB;
import dev.dave.daos.CustomersDAO;
import dev.dave.daos.CustomersDAODB;
import dev.dave.entities.Accounts;
import dev.dave.entities.Customers;

public class CustomersServicesImpl implements CustomersServices {
	
	private static CustomersDAO cdao = CustomersDAODB.getCustomersDAODB(); //our services can use our DAOS for the CRUD-like operations
	private static AccountsDAO adao = AccountsDAODB.getAccountsDAODB();

	// Making services singleton
	
	private static CustomersServicesImpl custserv = null; // static copy of itself
	
	//private constructor
	
	private CustomersServicesImpl() {

	};
	
	// a public static method responsible for getting us an instance of the class
	// this will ensure an instance of the class is only created once...
	
	public static CustomersServices getCustomersServices() {
		
		if (custserv == null) 
		{
			custserv = new CustomersServicesImpl();
			return custserv;
		}
		else 
		{
			return custserv;
		}
	}
	
	// the idea of using DAOS in services is to make some CRUD-like operations available for 
	// the user as a service and when it makes sense for the business logic for the end user to 
	// be able to run this kind of processing on their own
	
	// this method is a create-like operation so we return the result of the Customers DAO that creates a customer
	
	@Override
	public Customers CustomerSignUp(Customers customer) { 
		return cdao.createCustomer(customer); 
	}

	@Override
	public Customers GetCustInfo(int cID) { // this method is a read-like operation
		Customers customer = cdao.getCustByID(cID); // to fetch customer by cID
		Set<Accounts> accounts = adao.getAllAccountsByCustomer(cID); // we need to return all accounts by this customer
		if(customer == null) { // if the customer by ID is not found then our method returns null
			return null;
		}
		customer.setAccounts(accounts); // if customer is not null then we set the accounts to complete our customer object
		return customer; // we return customer, with all fields now populated
	}
	
	// another read-like operation
	
	@Override
	public Customers SearchCustomerByName(String username) {
		Customers customer = cdao.getCustByUsername(username);
		return customer;
	}
	
	// next three methods below are update-like operations
	
	@Override
	public Customers updateCustomer(Customers customer) {
		return cdao.updateCustomer(customer);
	}
	
	@Override
	public Customers ChangeCustName(Customers customer, String newUsername) {
		customer.setUsername(newUsername); // this method is for user to change their username, it calls the field setter
		cdao.updateCustomer(customer); // then it calls the cdao's update method to save changes 
		return customer; // it returns the customer with a modified username
	}

	@Override
	public Customers ChangePassword(Customers customer, String newPassword) {
		customer.setPassword(newPassword); // this method is for the user to update their password
		cdao.updateCustomer(customer);
		return customer; // it returns the customer with a new password
	}
	
	// below method is a delete-like operation

	@Override
	public boolean DeleteProfile(int cID) {
		return cdao.deleteCustomer(cID); //it calls for the cdao's method to delete the customer
	}
}
