package dev.noah.daos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dev.noah.entities.Account;
import dev.noah.entities.Customer;
import dev.noah.exceptions.CustomerCreationException;

public class CustomerDAOLive implements CustomerDAO {

	private static CustomerDAOLive cDAO = null;
	private Map<Integer, Customer> customer_table = new HashMap<Integer, Customer>(); // Temp database
	private int customerCounter = 1;

	private CustomerDAOLive() {

	};

	public static CustomerDAO getCustomerDAO() {
		if (cDAO == null) {
			cDAO = new CustomerDAOLive();
			return cDAO;
		} else {
			return cDAO;
		}
	}

	public Customer createCustomer(Customer customer) throws CustomerCreationException {

		// Making sure that we have at least five characters for the username and password
		if(customer.getUsername().length() < 4 || customer.getPassword().length() < 4) {
			throw new CustomerCreationException();
		}

		customer.setcId(customerCounter);
		customer_table.put(customerCounter, customer);
		customerCounter++;

		return customer;

	}

	public Customer addAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer getCustomerByCId(int id) {
		
		return customer_table.get(id);
	}

	public Set<Customer> getAllCustomers() {
		Set<Customer> customers = new HashSet<Customer>(customer_table.values());
		return customers;
	}

	public Customer updateCustomer(Customer customer) {
		customer_table.put(customer.getcId(), customer);
		return null;
	}

	

	public boolean deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeAccount(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
