package dev.noah.daos;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import dev.noah.entities.Account;
import dev.noah.entities.Customer;

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

	public Customer createCustomer(Customer customer, String username, String password) {
		
		try {
			username.charAt(6);
			password.charAt(6);
			customer.setcId(customerCounter);
			customerCounter++;
			return customer;
		}catch(Exception e){
			System.out.println("Username or password is not at least 6 characters");
			return null;

		}
	}

	public Customer addAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer getCustomerByCId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Customer> getAllCustomers(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer updateUsername(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer updatePassword(Customer customer) {
		// TODO Auto-generated method stub
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
