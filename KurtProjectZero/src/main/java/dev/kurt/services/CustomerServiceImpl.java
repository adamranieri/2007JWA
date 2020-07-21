package dev.kurt.services;

import java.util.Set;

import dev.kurt.daos.AccountDAO;
import dev.kurt.daos.AccountDAOMaria;
import dev.kurt.daos.CustomerDAO;
import dev.kurt.daos.CustomerDAOMaria;
import dev.kurt.entities.Account;
import dev.kurt.entities.Customer;



public class CustomerServiceImpl implements CustomerService {
	
	private static CustomerDAO cDao = CustomerDAOMaria.getCustomerDAOMaria(); // we already have our basic CRUD operations
	private static AccountDAO accDao = AccountDAOMaria.getAccountDAOMaria();

	@Override
	public Customer createCustomer(Customer customer) {
		return cDao.createCustomer(customer);
	}

	@Override
	public Customer getCustomerById(int id) {
		Customer customer = cDao.getCustomerById(id);
		Set<Account> accounts = accDao.getAccountsByCustomerId(id);
		if(customer == null) {
			return null;
		}
		customer.setCustomerAccounts(accounts);
		return customer;
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		Customer customer = cDao.getCustomerByUsername(username);
		Set<Account> accounts = accDao.getAccountsByCustomerId(customer.getcId());
		customer.setCustomerAccounts(accounts);
		return customer;
	}

	@Override
	public Set<Customer> getAllCustomers() {
		return cDao.getAllCustomers();
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return cDao.updateCustomer(customer);
	}

	@Override
	public boolean deleteCustomerById(int id) {
		return cDao.deleteCustomer(id);
	}
}
