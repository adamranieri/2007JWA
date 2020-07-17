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

	private static CustomerServices cServices ;
	
	private static CustomerDao custDao = CustomerDaoImpl.getCustomerDao();
	private static AccountDao acctDao	= AccountDaoImpl.getAccountDao();
	
	private CustomerServicesImpl() {
		
	}
	
	
	public static CustomerServices getCustomerServicesInstance() {
		if (cServices == null)
			cServices= new CustomerServicesImpl();
		return cServices;
	}
	
	
	@Override
	public int addCustomer(Customer c) {
		
		return custDao.create(c);
	}

	@Override
	public Customer getCustomerById(int id) {
		
		return custDao.retrieveById(id);
	}

	@Override
	public boolean deleteCustomer(int id) {
		
		return custDao.delete(id);
	}

	@Override
	public boolean updateCustomer(Customer c) {
		
		return custDao.update(c);
	}

	@Override
	public List<Account> getCustomerAccounts(int id) {
		
		return acctDao.getAllCustomerAccounts(id);
	}

}
