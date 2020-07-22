package dev.kyle.services;
import java.util.Set;

import dev.kyle.daos.AccountDAO;
import dev.kyle.daos.AccountDAOMaria;
import dev.kyle.daos.CustomerDAO;
import dev.kyle.daos.CustomerDAOMaria;
import dev.kyle.entities.Account;
import dev.kyle.entities.Customer;

public class CustomerServiceImpl implements CustomerService {
	
	// get Customer CRUD Ops
	private static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();
	private static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();

	@Override
	public Customer addCustomer(Customer c) {
		return cdao.createCustomer(c);
	}

	@Override
	public Customer getCustomerById(int cid)  {
		Customer c = cdao.getCustomerById(cid);
		Set<Account> accounts = adao.getAccountsByCustomerId(cid);
		if (c == null) {
			return null;
		}
		c.setAccounts(accounts);
		
		return c;
	}

	@Override
	public Customer changeUsername(Customer c, String newUsername) {
		c.setUsername(newUsername);
		cdao.updateCustomer(c);
		return c;
	}

	@Override
	public Customer changePassword(Customer c, String newPassword) {
		c.setPassword(newPassword);
		cdao.updateCustomer(c);
		return c;
	}


	@Override
	public Customer updateCustomer(Customer c) {
		return cdao.updateCustomer(c);
	}

	@Override
	public boolean deleteCustomerById(int cid) {
		return cdao.deleteCustomer(cid);
	}

	@Override
	public Set<Customer> getAllCustomers() {
		Set<Customer> customers = cdao.getAllCustomers();
		for (Customer c : customers) {
			c.setAccounts( adao.getAccountsByCustomerId(c.getCid()));
		}	
		return customers;	
	}

	@Override
	public Customer getCustomerByName(String name) {
		Customer match = null;	
		for (Customer c : cdao.getAllCustomers()) {
			if(c.getUsername().equals(name)) {
				c.setAccounts( adao.getAccountsByCustomerId(c.getCid() ));
				match = c;	
			}
		}
		return match;
	}
}
