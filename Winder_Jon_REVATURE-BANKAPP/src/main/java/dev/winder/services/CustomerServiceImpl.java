package dev.winder.services;

import java.util.Set;

import dev.winder.daos.CustomerDAO;
import dev.winder.daos.CustomerDAOMaria;
import dev.winder.entities.Customer;

public class CustomerServiceImpl implements CustomerService{
	
	private static CustomerDAO cDao = CustomerDAOMaria.getCustomerDAOMaria();
	
	

	@Override
	public Customer createNewCustomer(Customer customer) {
		
		return cDao.createCustomer(customer);
	}

	@Override
	public Set<Customer> getAllCustomersInBranch() {
		// TODO Auto-generated method stub
		return cDao.getAllCustomers();
	}

	@Override
	public Customer getCustomerByCustomerId(int id) {
		
		return cDao.getCustomerByCustomerId(id);
	}

	public Customer getCustomerByUsername(String username) {
		// TODO Auto-generated method stub
		return cDao.getCustomerByUsername(username);
	}

	@Override
	public Customer updateCustomerInfo(Customer customer) {
		
		return cDao.updateCustomer(customer);
	}

	@Override
	public boolean deleteCustomerById(int id) {

		return cDao.deleteCustomerById(id);
	}

}
