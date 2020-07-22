package dev.nauman.services;

import java.util.Set;

import dev.nauman.daos.CustomerDAO;
import dev.nauman.daos.CustomerDAOImpl;
import dev.nauman.entities.Customer;

public class CustomerServiceImpl implements CustomerService {

	private static CustomerDAO cdao = CustomerDAOImpl.getCustomerDAOImpl();
	
	@Override
	public Customer createCustomer(Customer customer) {
		return cdao.createCustomer(customer);
	}
	@Override
	public Customer getCustomerByCId(int cId) {
		return cdao.getCustomerById(cId);
	}
	@Override
	public Set<Customer> getAllCustomers() {
		return cdao.getAllCustomers();
	}
	@Override
	public boolean deleteCustomerByCId(int cId) {
		return cdao.deleteCustomerById(cId);
	}
	@Override
	public Customer updateCustomer(Customer customer) {
		return cdao.updateCustomer(customer);
	}
	@Override
	public boolean deleteCustomerByCustomer(Customer customer) {
		return this.deleteCustomerByCId(customer.getcId());
	}
	@Override
	public Customer changePassword(int cId, String password) {
		Customer customer = this.getCustomerByCId(cId);
		customer.setPassword(password);
		return this.updateCustomer(customer);
	}
	@Override
	public Customer changeUsername(int cId, String username) {
		Customer customer = this.getCustomerByCId(cId);
		customer.setUsername(username);
		return this.updateCustomer(customer);
	}
}
