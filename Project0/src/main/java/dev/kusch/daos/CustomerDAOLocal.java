package dev.kusch.daos;

import java.util.Set;

import dev.kusch.entities.Customer;

public class CustomerDAOLocal implements CustomerDAO {
	
	// Make this class in the singleton design pattern
	private static CustomerDAOLocal dao = null;
	
	private CustomerDAOLocal() {
		
	}
	
	public static CustomerDAO getCustomerDAO() {
		if (dao == null) {
			dao = new CustomerDAOLocal();
			return dao;
		} else {
			return dao;
		}
	}

	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCustomer(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

}
