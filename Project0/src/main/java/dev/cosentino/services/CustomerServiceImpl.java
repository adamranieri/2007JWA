package dev.cosentino.services;

import java.util.Set;

import dev.cosentino.daos.AccountDAO;
import dev.cosentino.daos.AccountDAOMaria;
import dev.cosentino.daos.CustomerDAO;
import dev.cosentino.daos.CustomerDAOMaria;
import dev.cosentino.exceptions.CustomerDoesNotExistException;
import dev.cosentino.resources.Account;
import dev.cosentino.resources.Customer;

public class CustomerServiceImpl implements CustomerService {

	public static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();
	public static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();
	
	@Override
	public Customer createNewCustomer(Customer customer) {
		return cdao.createCustomer(customer);
	}

	@Override
	public Customer getCustomerByAccountId(int id) {
		Account account = adao.getAccountById(id);
		Customer customer = cdao.getCustomerById(account.getCustomerId());
		return customer;
	}

	@Override
	public Customer getCustomerByAccountId(Account account) {
		return getCustomerByAccountId(account.getAccountId());
	}

	@Override
	public Customer getCustomerById(int id) {
		return cdao.getCustomerById(id);
	}

	@Override
	public Set<Customer> getAllCustomers() {
		return cdao.getAllCustomers();
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerDoesNotExistException{
		return cdao.updateCustomer(customer);
	}

	@Override
	public boolean deleteCustomer(int id) {
		Set<Account> accounts = adao.getAccountsByCustomerId(id);
		//dont delete customer if they have existing accounts
		if(accounts.size() == 0)
			return cdao.deleteCustomer(id);
		else
			return false;
	}

	@Override
	public boolean deleteCustomer(Customer customer) {
		return deleteCustomer(customer.getCustomerId());
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		return cdao.getCustomerByUsername(username);
	}

}
