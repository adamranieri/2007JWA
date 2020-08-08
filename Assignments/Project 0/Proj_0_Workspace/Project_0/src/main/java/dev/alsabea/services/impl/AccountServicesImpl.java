package dev.alsabea.services.impl;

import java.util.ArrayList;
import java.util.List;

import dev.alsabea.daos.AccountDao;
import dev.alsabea.daos.impl.AccountDaoImpl;
import dev.alsabea.entities.Account;
import dev.alsabea.exceptions.NegativeBalanceException;
import dev.alsabea.services.AccountServices;

public class AccountServicesImpl implements AccountServices {

	private static AccountServicesImpl aServices;

	private static AccountDao acctDao = AccountDaoImpl.getAccountDao();

	private AccountServicesImpl() {
	}

	public static AccountServicesImpl getAccountServicesInstance() {
		if (aServices == null)
			aServices = new AccountServicesImpl();
		return aServices;
	}

	@Override
	public int create(Account t) throws NegativeBalanceException {
		if (t.getBalance() < 0)
			throw new NegativeBalanceException();
		return acctDao.create(t);
	}

	@Override
	public boolean delete(int id) {

		return acctDao.delete(id);
	}

	@Override
	public List<Account> retrieveAllAccounts(int custID) {

		return acctDao.getAllCustomerAccounts(custID);
	}

	@Override
	public Account retrieveById(int id) {

		return acctDao.retrieveById(id);
	}

	@Override
	public boolean update(Account t) throws NegativeBalanceException {

		if (t.getBalance() < 0)
			throw new NegativeBalanceException();

		return acctDao.update(t);
	}

	/**
	 * @param 
	 * - balance refers to the account balance that we check against.
	 * - list contains list of accounts
	 * @return
	 * -  list of accounts that have a balance that is less than the one in the 'balance parameter'
	 */
	@Override
	public List<Account> balanceLessThan( int balance, List<Account> list) throws NegativeBalanceException{
		
		if (balance < 0 )
			throw new NegativeBalanceException();
		List<Account> acctsToBeReturned = new ArrayList<>();
		for (Account a : list) {
			if (a.getBalance() < balance)
				acctsToBeReturned.add(a);
		}
		return acctsToBeReturned;
	}

	@Override
	public List<Account> balanceGreaterThan( int balance, List<Account> list) throws NegativeBalanceException{
		if (balance < 0 )
			throw new NegativeBalanceException();
		List<Account> acctsToBeReturned = new ArrayList<>();
		for (Account a : list) {
			if (a.getBalance() > balance)
				acctsToBeReturned.add(a);
		}
		return acctsToBeReturned;
	}

}