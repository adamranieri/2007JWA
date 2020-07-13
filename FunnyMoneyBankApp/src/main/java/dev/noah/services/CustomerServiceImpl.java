package dev.noah.services;

import java.util.HashSet;
import java.util.Set;

import dev.noah.daos.CustomerDAO;
import dev.noah.daos.CustomerDAOLive;
import dev.noah.entities.Customer;
import dev.noah.exceptions.CustomerCreationLengthException;

public class CustomerServiceImpl implements CustomerService {

	private static CustomerDAO cdao = CustomerDAOLive.getCustomerDAO();

	@Override
	public Customer createCustomer(String username, String password) throws CustomerCreationLengthException {

		Customer customer = new Customer(0,username,password,null);
		
		// Making sure that we have at least five characters for the username and password
		if (username.length() < 4 || password.length() < 4) {
			throw new CustomerCreationLengthException();
		}
		cdao.createCustomer(customer);
		return customer;
	}

	@Override
	public Set<Customer> getAllCustomers() {

		Set<Customer> getcuss = cdao.getAllCustomers();

		return getcuss;
	}

	@Override
	public Customer getCustomerByCId(int id) {
		return cdao.getCustomerByCId(id);
	}

	@Override
	public Customer changeCustomerUsernameById(int id, String username) {
		Customer cus = cdao.getCustomerByCId(id);
		cus.setUsername(username);
		return cdao.updateCustomer(cus);
	}

	@Override
	public Customer changeCustomerPasswordById(int id, String password) {
		Customer cus = cdao.getCustomerByCId(id);
		cus.setPassword(password);
		return cdao.updateCustomer(cus);
	}

	@Override
	public Boolean deleteCustomerByCId(int id) {
		return cdao.deleteCustomer(id);
	}

}
