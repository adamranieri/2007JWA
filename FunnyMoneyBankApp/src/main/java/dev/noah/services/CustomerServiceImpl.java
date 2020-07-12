package dev.noah.services;

import java.util.Set;

import dev.noah.entities.Customer;
import dev.noah.exceptions.CustomerCreationLengthException;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public Customer createCustomer(Customer customer) throws CustomerCreationLengthException {
		// Making sure that we have at least five characters for the username and password
				if(customer.getUsername().length() < 4 || customer.getPassword().length() < 4) {
					throw new CustomerCreationLengthException();
				}
		return null;
	}

	@Override
	public Set<Customer> getAllCustomers(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer changeCustomerUsername(Customer customer, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer changeCustomerPassword(Customer customer, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer deleteCustomer(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
