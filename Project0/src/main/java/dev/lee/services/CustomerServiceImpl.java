package dev.lee.services;

import java.util.Set;

import dev.lee.daos.AccountDAO;
import dev.lee.daos.AccountDAOMaria;
import dev.lee.daos.CustomerDAO;
import dev.lee.daos.CustomerDAOMaria;
import dev.lee.entities.Account;
import dev.lee.entities.Customer;

public class CustomerServiceImpl implements CustomerService{

	private static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();
	private static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();
	
	@Override
	public Customer establishCustomer(Customer customer) {
		return cdao.createCustomer(customer);
	}

	@Override
	public Customer getCustomerById(int cId) {
		Customer customer = cdao.getCustomerById(cId);
		if (customer != null) {
			Set<Account> accounts = adao.getAccountsByCustomerId(cId);
			customer.setAccounts(accounts);
		}

		return customer;
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		Customer customer = cdao.getCustomerByUsername(username);
		if (customer != null) {
			int cId = customer.getcID();
			Set<Account> accounts = adao.getAccountsByCustomerId(cId);
			customer.setAccounts(accounts);
		}
		return customer;
	}

	@Override
	public Set<Customer> getAllCustomersWithoutAccounts() {
		return cdao.getAllCustomers();
	}

	@Override
	public Set<Customer> getAllCustomers() {
		Set<Customer> customers = cdao.getAllCustomers();
		for (Customer customer: customers) {
			int cId = customer.getcID();
			Set<Account> accounts = adao.getAccountsByCustomerId(cId);
			customer.setAccounts(accounts);
		}
		
		return customers;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		boolean exists = cdao.getCustomerById(customer.getcID()) != null;
		if (exists) {
			int cId = customer.getcID();
			Set<Account> accounts = adao.getAccountsByCustomerId(cId);
			customer.setAccounts(accounts);
			return cdao.updateCustomer(customer);
		}
		return null;
	}

	@Override
	public boolean deleteCustomer(int cId) {
		return cdao.deleteCustomer(cId);
	}

	@Override
	public boolean deleteCustomer(Customer customer) {
		int cId = customer.getcID();
		return cdao.deleteCustomer(cId);
	}

	@Override
	public Customer changeUsername(Customer customer, String newUsername) {
		customer.setUsername(newUsername);
		return cdao.updateCustomer(customer);
	}

	@Override
	public Customer changePassword(Customer customer, String newPassword) {
		customer.setPassword(newPassword);
		return cdao.updateCustomer(customer);
	}

	
	
}
