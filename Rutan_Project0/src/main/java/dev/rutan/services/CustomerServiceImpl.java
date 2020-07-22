package dev.rutan.services;

import java.util.HashSet;
import java.util.Set;

import dev.rutan.daos.AccountDAO;
import dev.rutan.daos.AccountDAOMaria;
import dev.rutan.daos.CustomerDAO;
import dev.rutan.daos.CustomerDAOMaria;
import dev.rutan.entities.Account;
import dev.rutan.entities.Customer;

public class CustomerServiceImpl implements CustomerService{

	private static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();
	private static AccountDAO adao = AccountDAOMaria.getAccountDAO();
	
	@Override
	public Customer createCustomer(Customer customer) {
		customer = cdao.createCustomer(customer);
		Set<Account> accounts = adao.getAllAccountsByCustomerId(customer.getcId());
		customer.setAccounts(accounts);
		return customer;
	}

	@Override
	public Customer getCustomerById(int cId) {
		Customer customer = cdao.getCustomerById(cId);
		Set<Account> accounts = adao.getAllAccountsByCustomerId(cId);
		customer.setAccounts(accounts);
		return customer;
	}

	@Override
	public Set<Customer> getAllCustomers() {
		Set<Customer> customers = cdao.getAllCustomers();
		for (Customer customer: customers) {
			Set<Account> accounts = adao.getAllAccountsByCustomerId(customer.getcId());
			customer.setAccounts(accounts);
		}
		return customers;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		customer = cdao.updateCustomer(customer);
		Set<Account> accounts = adao.getAllAccountsByCustomerId(customer.getcId());
		customer.setAccounts(accounts);
		return customer;
	}

	@Override
	public boolean deleteCustomer(int cId) {
		adao.deleteAllAccountsByCustomerId(cId);
		return cdao.deleteCustomer(cId);
	}

	@Override
	public Set<Customer> getAllCustomersByUsername(String username) {
		Set<Customer> custsWithUsername = new HashSet<Customer>();
		
		for (Customer customer: cdao.getAllCustomers()) {
			if(customer.getUsername().equals(username)) {
				Customer temp = customer;
				Set<Account> accounts = adao.getAllAccountsByCustomerId(temp.getcId());
				temp.setAccounts(accounts);
				custsWithUsername.add(temp);
			}
		}
		
		return custsWithUsername;
	}

}
