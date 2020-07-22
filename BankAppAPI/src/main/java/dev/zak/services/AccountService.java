package dev.zak.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import dev.zak.daos.AccountDaoMaria;
import dev.zak.daos.CustomerDaoInterface;
import dev.zak.daos.CustomerDaoMaria;
import dev.zak.daos.TransactionDaoInterface;
import dev.zak.daos.TransactionDaoMaria;
import dev.zak.daos.AccountDaoInterface;
import dev.zak.entities.Account;
import dev.zak.entities.Transaction;
import dev.zak.utilities.ConnectionUtility;

public class AccountService implements AccountServiceInterface{

	private static CustomerDaoInterface cdao = CustomerDaoMaria.getCustomerDaoMaria();
	private static AccountDaoInterface adao = AccountDaoMaria.getAccountDaoMaria();
	private static TransactionDaoInterface tdao = TransactionDaoMaria.getTransactionDaoMaria();
	
	public AccountService() {
		super();
	}

	@Override
	public Account createAccount(int cid, Account a) throws IllegalArgumentException{
		try{
	        if (a.getBalance() < 0) {
	            throw new IllegalArgumentException();
	        }
	    }
	    catch( IllegalArgumentException e){
	    	e.getMessage();
	    	a.setBalance(0);
	    	return a;
	    }
		if(cdao.getCustomerById(cid) == null) {
			a.setcId(0);
			return a;
		}
		return adao.createAccount(cid, a);
	}

	@Override
	public Account updateAccount(int cid, Account a) throws IllegalArgumentException{
		try{
			if (a.getBalance() < 0) {
	        	String msg = "Balance must be positive, value entered:" + a.getBalance();
	        	throw new IllegalArgumentException(msg);
	        }
			a = adao.updateAccount(cid, a);
		}
		catch(IllegalArgumentException e) {
			e.getMessage();
			a.setBalance(0);
			a.setaId(0);
		}
		return a;
		
	}

	@Override
	public Set<Account> getAllAccountsByCustomerId(int cid) {
		return adao.getAllAccountsByCustomerId(cid);
	}

	@Override
	public Set<Account> getAllAccounts() {
		return adao.getAllAccounts();
	}

	@Override
	public Account getAccountById(int cid, int id) {
		return adao.getAccountById(cid, id);
	}

	@Override
	public Account getAccountById(int id) {
		return adao.getAccountById(id);
	}

	@Override
	public boolean deleteAccountById(int cid, int id) {
		return adao.deleteAccount(cid, id);
	}

	@Override
	public boolean deleteAccountById(int id) {
		return adao.deleteAccount(id);
	}

	@Override
	public Set<Account> getAccountLessThan(int cid, int balance) {
		return adao.getAccountLessThan(cid, balance);
	}

	@Override
	public Set<Account> getAccountGreaterThan(int cid, int balance) {
		return adao.getAccountGreaterThan(cid, balance);
	}
	
	@Override
	public Set<Account> getAccountBalanceBtween(int cid, int balance1, int balance2) {
		 return adao.getAccountBalanceBtween(cid, balance1, balance2);
	}
	
	@Override
	public boolean transferMoney(int aIdFrom, int aIdTo, float amount) throws SQLException {
		Connection con = ConnectionUtility.getConnection();
		try{
			Account accountFrom = this.getAccountById(aIdFrom);
			Account accountTo = this.getAccountById(aIdTo);
			if(accountFrom != null && accountTo != null) {
				float accountFromOldBalance = accountFrom.getBalance();
				float accountToOldBalance = accountTo.getBalance();
				if(accountFrom.getBalance() >= amount){
					con.setAutoCommit(false);
					accountFrom.setBalance(accountFromOldBalance - amount);
					accountFrom = adao.updateAccount(accountFrom);
					accountTo.setBalance(accountToOldBalance + amount);
					accountTo = adao.updateAccount(accountTo);
					Transaction t1 = new Transaction(0, accountFrom.getaId(), accountFromOldBalance, accountFrom.getBalance());
					t1 = tdao.createTransaction(t1);
					Transaction t2 = new Transaction(0, accountTo.getaId(), accountToOldBalance, accountTo.getBalance());
					t2 = tdao.createTransaction(t2);
					con.commit();
					con.setAutoCommit(true);
					return true;
				}
			}
			con.rollback();
			con.setAutoCommit(false);
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
			con.rollback();
			con.setAutoCommit(false);
			return false;
		}
	}


}
