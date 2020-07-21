package dev.noah.services;

import java.util.HashSet;
import java.util.Set;

import dev.noah.daos.AccountDAO;
import dev.noah.daos.AccountDAOMaria;
import dev.noah.daos.CustomerDAO;
import dev.noah.daos.CustomerDAOMaria;
import dev.noah.entities.Account;
import dev.noah.entities.Customer;

public class CustomerServiceImpl implements CustomerService {

	private static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();
	private static AccountDAO adao = AccountDAOMaria.getAccountDaoMaria();
	
	@Override
	public Customer createCustomer(Customer cus) {		
		return cdao.createCustomer(cus);
	}

	@Override
	public Set<Customer> getAllCustomers() {		
		return cdao.getAllCustomers();
	}

	@Override
	public Customer getCustomerByCId(int id) {
		Customer customer = cdao.getCustomerBycId(id);
		Set<Account> accounts = adao.getAllAccountsByCId(id);
		customer.setAccounts(accounts);
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer cus) {
		return cdao.updateCustomer(cus);
	}

	

	@Override
	public Boolean deleteCustomerByCId(int id) {
		return cdao.deleteCustomer(id);
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		Set<Customer> customers = cdao.getAllCustomers();
		for(Customer customer: customers) {
			if(customer.getUsername().contains(username)) {
				customer.setAccounts(adao.getAllAccountsByCId(customer.getcId()));
				return customer;
				}
			}	
		return null;
	}
		
	

}
