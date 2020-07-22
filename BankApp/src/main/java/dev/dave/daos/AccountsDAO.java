package dev.dave.daos;

import java.util.Set;

import dev.dave.entities.Accounts;

public interface AccountsDAO {

	// CREATE operations
	
	Accounts createAccount (Accounts account); // to persist object account
	
	// READ operations
	
	Accounts getAccByID (int ID);
	
	Set<Accounts> getAllAccounts (); 
	
	Set<Accounts> getAllAccountsByCustomer(int cID);
	
	// UPDATE operations
	
	Accounts updateAccount (Accounts account);
	
	// DELETE operations
	
	boolean deleteAccount (int ID);
	
	boolean deleteAllCustAccounts(int cID);
}
