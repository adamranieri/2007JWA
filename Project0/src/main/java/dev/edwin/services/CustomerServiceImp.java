package dev.edwin.services;

import java.util.ArrayList;
import java.util.List;

import dev.edwin.daos.AccountDAO;
import dev.edwin.daos.AccountDAOImp;
import dev.edwin.daos.CustomerDAO;
import dev.edwin.daos.CustomerDAOImp;
import dev.edwin.entities.Account;
import dev.edwin.entities.Customer;

public class CustomerServiceImp implements CustomerService 
{

	private static CustomerDAO cDao = CustomerDAOImp.getCustomerDAO();
	private static AccountDAO aDao = AccountDAOImp.getAccountDAO();
	private static CustomerService cserv = null;
	
	private CustomerServiceImp() {
		super();
	}

	public static CustomerService getCustomerService()
	{
		if (cserv == null)
		{
			cserv = new CustomerServiceImp();
		}
		
		
		return cserv;
	}
	
	
	@Override
	public Customer signUpNewCustomer(Customer customer) {	
		return cDao.createCustomer(customer);
	}

	@Override
	public Customer getCustomerById(int cId) {
		Customer customer = cDao.getCustomerById(cId);
		List<Account> accounts = aDao.getAccountsByCustomerId(cId);
		
		if (customer == null)
			return null;
		
		customer.setAccounts(accounts);
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = cDao.getCustomers();
		
		if (customers == null)
			return null;

		List<Account> accounts = new ArrayList<Account>();
		for (Customer customer: customers)
		{
			accounts = aDao.getAccountsByCustomerId(customer.getcId());
			customer.setAccounts(accounts);
		}
		
		return customers;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return cDao.updateCustomer(customer);
	}

	@Override
	public boolean deleteCustomer(int cId) {
		return cDao.deleteCustomer(cId);
	}

	@Override
	public boolean deleteCustomer(Customer customer) {
		return this.deleteCustomer(customer.getcId());
	}


	@Override
	public List<Customer> searchByUsername(String username) {
		return cDao.getCustomerByUsername(username);
	}

}
