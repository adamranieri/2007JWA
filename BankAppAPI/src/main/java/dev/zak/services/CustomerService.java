package dev.zak.services;

import java.util.Set;

import dev.zak.daos.CustomerDaoMaria;
import dev.zak.daos.AccountDaoInterface;
import dev.zak.daos.AccountDaoMaria;
import dev.zak.daos.CustomerDaoInterface;
import dev.zak.entities.Account;
import dev.zak.entities.Customer;

public class CustomerService implements CustomerServiceInterface{


	private static CustomerDaoInterface cdao = CustomerDaoMaria.getCustomerDaoMaria();
	private static AccountDaoInterface adao = AccountDaoMaria.getAccountDaoMaria();
	
	public CustomerService() {
		// TODO Auto-generated constructor stub
	}

	public Customer createCustomer(Customer c) {
		return cdao.createCustomer(c);
	}

	public Customer updateCustomer(Customer c) {
		if(cdao.getCustomerById(c.getcId()) != null)
			return cdao.updateCustomer(c);
		return null;
	}

	public Set<Customer> getAllCustomers() {
		Set<Customer> customers = cdao.getAllCustomers();
		for(Customer c : customers) {
			c.setAccounts(adao.getAllAccountsByCustomerId(c.getcId()));
		}
		return customers;
	}

	public Customer getCustomerById(int id) {
		Customer c = cdao.getCustomerById(id);
		if (c != null) {
			c.setAccounts(adao.getAllAccountsByCustomerId(c.getcId()));	
		}
		return c;
	}

	public int deleteCustomerById(int id) {
		Customer c = cdao.getCustomerById(id);
		Set<Account> accounts = adao.getAllAccountsByCustomerId(id); 
		
		if(c!=null && (accounts==null || accounts.size()==0)) {
			cdao.deleteCustomer(id);
			return 1;
		}else if(accounts!=null && accounts.size()>0) {
			return -1;
		}
		else {
			return 0;
		}
	}

	public Set<Customer> getCustomerByUserName(String userName) {
		return cdao.getCustomerByUserName(userName);
	}

	@Override
	public boolean deleteCustomerByIdWithAllAccount(int id) {
		Customer c = cdao.getCustomerById(id);
		if(c!=null)
			return cdao.deleteCustomer(id);
		return false;
	}

}
