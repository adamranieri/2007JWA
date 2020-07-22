package dev.dave.services;

import java.util.Set;

import dev.dave.daos.AccountsDAO;
import dev.dave.daos.AccountsDAODB;
import dev.dave.daos.AccountsDAOLocal;
import dev.dave.daos.CustomersDAO;
import dev.dave.daos.CustomersDAODB;
import dev.dave.entities.Accounts;
import dev.dave.entities.Customers;
import dev.dave.exceptions.NegativeBalanceException;
import dev.dave.exceptions.WithdrawException;

public class AccountsServicesImpl implements AccountsServices {
	
	private static AccountsDAO adao = AccountsDAODB.getAccountsDAODB(); //our services can use our DAOS for the CRUD-like operations
	private static CustomersDAO cdao = CustomersDAODB.getCustomersDAODB();
	
	// Making services singleton
	
	private static AccountsServicesImpl accserv = null; // static copy of itself
	
	// private constructor
	
	private AccountsServicesImpl() {
		
	};
	
	// a public static method responsible for getting us an instance of the class
	// this will ensure an instance of the class is only created once...
	
	public static AccountsServices getAccountsServices() 
	{
		if (accserv == null) 
		{
			accserv = new AccountsServicesImpl();
			return accserv;
		}
		else 
		{
			return accserv;
		}
	}
	
	// the idea of using DAOS in services is to make some CRUD-like operations available for 
	// the user as a service and when it makes sense for the business logic for the end user to 
	// be able to run this kind of processing on their own
	
	// this method is a create-like operation so we return the result of the Accounts DAO that creates an account

	@Override
	public Accounts openAccount(Accounts account) {
		return adao.createAccount(account);
	}
	
	// this method is a read-like operation, it returns the result of the adao when it searches account by aID

	@Override
	public Accounts pullUpAccount(int aID) {
		return adao.getAccByID(aID);
	}

	// this one will retrieve all the accounts from a given customer by passing in the cID
	// it cannot be used locally but with the DB
	
	@Override
	public Set<Accounts> listAccounts(int cID) {
		return adao.getAllAccountsByCustomer(cID);
	}
	
	// another read-like operation to check the accounts balance

	@Override
	public double balanceInquiry(Accounts account) {
		double accountBalance = account.getBalance();
		return accountBalance;
	}
	
	// update operation
	
	@Override
	public Accounts updateAccount(Accounts account) {
		return adao.updateAccount(account);
	}
	
	// update-like operation to increase account's balance 
	// it will throw a withdraw exception if resulting balance is less than the current amount
	// lastly we call the adao to save our changes made to the account object and we return it

	@Override
	public Accounts makeDeposit(Accounts account, double amount) throws WithdrawException {
		
		if ((amount + account.getBalance())< account.getBalance()) throw new WithdrawException();
		
		account.setBalance(account.getBalance() + amount);
		
		adao.updateAccount(account);
		
		return account;
	}
	
	// another update-like operation to reflect the result of a withdraw (certain amount taken from the total balance)
	// the result of this cannot be less than zero, so the customer couldn't withdraw
	// a greater amount than the total balance
	// lastly we call the adao to save our changes made to the account object and we return it

	@Override
	public Accounts withdraw(Accounts account, double amount) throws NegativeBalanceException {
		
		if ((account.getBalance() - amount)<0) throw new NegativeBalanceException();
		
		account.setBalance(account.getBalance() - amount);
		
		adao.updateAccount(account);
		
		return account;
	}

	// a delete-like operation to delete a single account
	// this method takes in the aID and call the adao to perform the operation
	
	@Override
	public boolean cancelAccount(int aID) {
		return adao.deleteAccount(aID);
	}
	
	// a delete-like operation 
	// this one cannot be performed on our local map as it links accounts to aID
	// and this is to delete all accounts from a given customer
	// we'll use this one with the actual DB

	@Override
	public boolean cancelAllAccounts(int cID) {
		boolean areAccsDeleted = adao.deleteAllCustAccounts(cID);
		
		if (areAccsDeleted == true) {
			cdao.deleteCustomer(cID);
			return true;
		}else {
			return false;
		}
	}
}
