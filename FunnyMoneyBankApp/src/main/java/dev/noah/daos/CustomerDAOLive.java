package dev.noah.daos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dev.noah.entities.Account;
import dev.noah.entities.Customer;
import dev.noah.exceptions.CustomerCreationLengthException;

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

	@Override
	public Customer createCustomer(Customer customer) {

				
		customer.setcId(customerCounter);
		customer_table.put(customerCounter, customer);
		customerCounter++;

		return customer;

	}

	@Override
	public Customer getCustomerByCId(int id) {
		
		return customer_table.get(id);
	}

	@Override
	public Set<Customer> getAllCustomers() {
		Set<Customer> customers = new HashSet<Customer>(customer_table.values());
		return customers;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		customer_table.put(customer.getcId(), customer);
		return customer;
	}

	

	public boolean deleteCustomer(int id) {
		
		Customer cus = customer_table.remove(id);
		
		if(cus == null) {
			return false;
		}
		else {
		return true;
		}
	}

}
