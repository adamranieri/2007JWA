package dev.kusch.services;

import java.util.Set;

import dev.kusch.daos.CustomerDAO;
import dev.kusch.daos.CustomerDAOMaria;
import dev.kusch.entities.Account;
import dev.kusch.entities.Customer;

public class CustomerServicesImpl implements CustomerServices {

	private static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();
	
	@Override
	public Customer addCustomer(Customer customer) {
		return cdao.createCustomer(customer);
	}

	@Override
	public Set<Customer> getAllCustomers() {
		return cdao.getAllCustomers();
	}

	@Override
	public Customer getCustomer(int id) {
		return cdao.getCustomerById(id);
	}

	@Override
	public Customer getCustomer(Customer customer) {
		return this.getCustomer(customer.getcId());
	}

	@Override
	public Set<Customer> getCustomer(String username) {
		return cdao.getCustomerByUser(username);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return cdao.updateCustomer(customer);
	}

	@Override
	public boolean deleteCustomer(Customer customer) {
		return this.deleteCustomer(customer.getcId());
	}

	@Override
	public boolean deleteCustomer(int id) {
		return cdao.deleteCustomer(id);
	}
	
}
