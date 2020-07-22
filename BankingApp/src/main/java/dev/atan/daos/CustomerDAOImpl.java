package dev.atan.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.atan.entities.Account;
import dev.atan.entities.Customer;

public class CustomerDAOImpl implements CustomerDAO{
	

	private static CustomerDAOImpl dao = null;
	
	private CustomerDAOImpl() {
		
	};
	
	public static CustomerDAOImpl getCustomerDAO() {
		
		if(dao == null) {
			dao = new CustomerDAOImpl();
			return dao;
		}else {
			return dao;
		}
		
	}
	

	private Map<Integer, Customer> customer_table = new HashMap<Integer,Customer>();
	
	private int counter = 1;

	public Customer createCustomer(Customer customer) {
		customer.setcID(counter);
		this.customer_table.put(customer.getcID(), customer);
		this.counter++;	
		return customer;
	}

	public Customer getCustomerById(int id) {
		return customer_table.get(id);
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>(customer_table.values());
		return customers;
	}

	public Customer updateCustomer(Customer customer) {
		customer_table.put(customer.getcID(), customer);
		return customer;
	}

	public boolean deleteCustomerById(int id) {
		Customer c = customer_table.remove(id);
		
		if(c != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteCustomerByUserName(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer getCustomerByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomerAccounts(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getOpenAccountsPerCustomer(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
