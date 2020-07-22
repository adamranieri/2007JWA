package dev.dave.daos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dev.dave.entities.Accounts;

public class AccountsDAOLocal implements AccountsDAO {
	
	// We're making this DAO a singleton
	
		private static AccountsDAOLocal accdao = null; // static copy of itself
		
		private AccountsDAOLocal()  // private constructor
		{
			
		};
		
		// a public static method responsible for getting us an instance of the class
		// this will ensure an instance of the class is only created once...
		
		public static AccountsDAO getAccountsDAO() 
		{
			if (accdao == null) // ... if accdao doesn't exist...
			{
				accdao = new AccountsDAOLocal(); // ... then it creates it...
				return accdao;
			}
			else 								   // ... otherwise it just returns it since it would
			{                                      // already exist.  
				return accdao;
			}
		}
		
		// we create a map to store our object from the Accounts class, we'll map it out 
		// to an Integer object and that's the way we'll create an account along with its ID
		// this is like we would add the object into a DataBase
		// the counter starts at 1, when we start adding accounts, it will be incremented
		// first account gets ID = 1, and so on
		
		private Map<Integer, Accounts> accountsTable = new HashMap<Integer, Accounts>(); 
		
		private int counter = 1;
		
		// Create operation

	@Override
	public Accounts createAccount(Accounts account) {
		account.setaID(counter); //first we need to set the ID, so we're passing in the counter when calling for our ID setter from Accounts
		this.accountsTable.put(account.getcID(), account); // adds account record into the table
		this.counter++; //to have the counter increase by 1, so next account will get the next number for its ID
		return account; // the method will return the account created
	}
	
	// Read operations
	
		// this one retrieves the account object searching it by aID

	@Override
	public Accounts getAccByID(int ID) {
		return accountsTable.get(ID);
	}
	
	// this other one will retrieve all account objects stored in a set (no duplicates)

	@Override
	public Set<Accounts> getAllAccounts () {
		Set<Accounts> allaccs = new HashSet<Accounts>(accountsTable.values());
		return allaccs;
	}
	
	// Update operation
		
	@Override
	public Accounts updateAccount(Accounts account) {
		accountsTable.put(account.getcID(), account); // we call .put() method and we pass in the account's ID and the account as that's what our map contains and what we're looking to update
		return account; // account is returned once updated	
		
		// Delete operations
	}

	@Override
	public boolean deleteAccount(int ID) {
		Accounts account = accountsTable.remove(ID); // to pinpoint the record (account) to delete based on the ID passed in
		
		if (account != null) // after deleting, if account value is not null...
		{
			return true; // ... then account record was removed
		}
		else 
		{
			return false; // else it was not deleted
		}
	}
	
	// to do with DB

	@Override
	public Set<Accounts> getAllAccountsByCustomer(int cID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAllCustAccounts(int cID) {
		// TODO Auto-generated method stub
		return false;
	}
}
