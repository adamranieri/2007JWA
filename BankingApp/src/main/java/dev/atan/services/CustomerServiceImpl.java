package dev.atan.services;


import java.util.List;
import dev.atan.daos.CustomerDAO;
import dev.atan.daos.CustomerDAOMaria;
import dev.atan.entities.Customer;

public class CustomerServiceImpl implements CustomerService{

	private static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();
	
	//CREATE
	
	@Override
	public Customer createCustomer(Customer customer) {
		return cdao.createCustomer(customer);
	}
		
	
	//READ
	
	@Override
	public Customer getCustomerById(int id) {
		return cdao.getCustomerById(id); 
	}
	
	
	@Override
	public List<Customer> getAllCustomers() {
		return cdao.getAllCustomers();
	}
	
	@Override
	public Customer getCustomerByUserName(String userName) {
		return cdao.getCustomerByUserName(userName);
	}


	//Update

	@Override
	public Customer updateCustomer(Customer customer) {	
		Customer c = cdao.getCustomerById(customer.getcID());
		if(c == null) {
			return null;
		} else {
		return cdao.updateCustomer(customer);}
	}
	

	
	@Override
	public Customer renameCustomer(String oldName, String newName) {
		Customer customer = cdao.getCustomerByUserName(oldName);
		customer.setUserName(newName);
		cdao.updateCustomer(customer);
		return customer;
	}
	

	@Override
	public Customer changePassword(Customer customer, String password) {
		customer.setcPassword(password);
		cdao.updateCustomer(customer);
		return customer;
	}
	
	//Delete

	@Override
	public boolean deleteCustomerById(int id) {
		return cdao.deleteCustomerById(id);
	}



	@Override
	public boolean deleteCustomerByUserName(String userName) {
		return cdao.deleteCustomerByUserName(userName);
	}

}	
