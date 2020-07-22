package dev.laurent.services;

import java.util.HashSet;
import java.util.Set;

import dev.laurent.daos.CustomerDAO;
import dev.laurent.daos.CustomerDAOMaria;
import dev.laurent.daos.AccountDAO;
import dev.laurent.daos.AccountDAOMaria;
import dev.laurent.entities.Customer;
import dev.laurent.entities.Account;

public class CustomerServiceImpl implements CustomerService {
	
	private static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria(); // we already have our basic CRUD operations
	private static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();
	
	@Override
	public Customer addCustomer(Customer customer) {
		return cdao.createCustomer(customer);
	}

	@Override
	public Customer getCustomerById(int id) {
		Customer customer = cdao.getCustomerById(id);
		Set<Account> accounts = adao.getAccountsByCustomerId(id);
		customer.setAccounts(accounts);
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer customer) {		
		return cdao.updateCustomer(customer);
	}

	@Override
	public boolean deleteCustomerById(int id) {	
		return cdao.deleteCustomer(id);
	}

	@Override
	public Set<Customer> getAllCustomers() {
		return cdao.getAllCustomers();
	}
	
	@Override
	public Customer getCustomerByUsername(String user) {
		Customer customerFound = null;	
		for (Customer customer : cdao.getAllCustomers()) {
			if(customer.getUsername().equals(user)) {
				customer.setAccounts( adao.getAccountsByCustomerId(customer.getcId()));
				customerFound = customer;	
			}
		}
		return customerFound;
	}
	

}
