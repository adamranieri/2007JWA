package dev.noah.services;

import java.util.HashSet;
import java.util.Set;

import dev.noah.daos.CustomerDAO;
import dev.noah.daos.CustomerDAOLocal;
import dev.noah.entities.Customer;

public class CustomerServiceImpl implements CustomerService {

	private static CustomerDAO cdao = CustomerDAOLocal.getCustomerDAO();

	@Override
	public Customer createCustomer(Customer cus) {

		Customer customer = cus;
		
		return cdao.createCustomer(customer);
	}

	@Override
	public Set<Customer> getAllCustomers() {
		return cdao.getAllCustomers();
	}

	@Override
	public Customer getCustomerByCId(int id) {
		return cdao.getCustomerByCId(id);
	}

	@Override
	public Customer updateCustomer(Customer cus) {
		return cdao.updateCustomer(cus);
	}

	

	@Override
	public Boolean deleteCustomerByCId(int id) {
		return cdao.deleteCustomer(id);
	}

}
