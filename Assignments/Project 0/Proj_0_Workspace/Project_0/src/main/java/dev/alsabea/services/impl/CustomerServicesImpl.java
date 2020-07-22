package dev.alsabea.services.impl;

import java.util.List;

import dev.alsabea.daos.AccountDao;
import dev.alsabea.daos.CustomerDao;
import dev.alsabea.daos.impl.AccountDaoImpl;
import dev.alsabea.daos.impl.CustomerDaoImpl;
import dev.alsabea.entities.Account;
import dev.alsabea.entities.Customer;
import dev.alsabea.services.CustomerServices;

public class CustomerServicesImpl implements CustomerServices {

	private static CustomerServicesImpl cServices ;
	
	private static CustomerDao custDao = CustomerDaoImpl.getCustomerDao();
	private static AccountDao acctDao	= AccountDaoImpl.getAccountDao();
	
	private CustomerServicesImpl() {
		
	}
	
	
	public static CustomerServicesImpl getCustomerServicesInstance() {
		if (cServices == null)
			cServices= new CustomerServicesImpl();
		return cServices;
	}
	
	
	@Override
	public int create(Customer c) {
		
		return custDao.create(c);
	}

	@Override
	public Customer retrieveById(int i) {
		
		return custDao.retrieveById(i);
	}
	
	@Override
	public List<Customer> retrieveByUsername(String username) {
		
		return custDao.retrieveByUsername(username);
	}
	
	@Override
	public List<Customer> retrieveAll() {
		
		return custDao.retrieveAll();
	}

	@Override
	public boolean delete(int id) {
		
		return custDao.delete(id);
	}

	@Override
	public boolean update(Customer c) {
		
		return custDao.update(c);
	}

	@Override
	public List<Account> getCustomerAccounts(int id) {
		
		return acctDao.getAllCustomerAccounts(id);
	}

}