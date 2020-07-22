package dev.schneider.services;

import java.sql.SQLDataException;
import java.util.Set;

import dev.schneider.dao.CustomerDAO;
import dev.schneider.dao.CustomerDAOImpl;
import dev.schneider.entities.Customer;

public class CustomerServiceImpl implements CustomerService {

	private static CustomerDAO cdao = CustomerDAOImpl.getCustomerDAO();
	
	@Override
	public Customer createCustomer(Customer customer) {
		return cdao.createCustomer(customer);
	}
	
	@Override
	public Set<Customer> getAllCustomers() {
		return cdao.getAllCustomer();
	}

	@Override
	public Customer getCustomerByID(int id)  {
		return cdao.getCustomerByID(id);
	}
	
	@Override
	public Customer getCustomerbyUsername(String username) {
		return cdao.getCustomerByUsername(username);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return cdao.updateCustomer(customer);
	}

	@Override
	public boolean deleteCustomer(int id) {
		return cdao.deleteCustomer(id);
	}

}
