package dev.dave.services;

import java.util.Set;

import dev.dave.entities.Accounts;
import dev.dave.exceptions.NegativeBalanceException;
import dev.dave.exceptions.WithdrawException;

public interface AccountsServices {
	
	//CRUD like operations offered as services for end user
	
	Accounts openAccount (Accounts account); // A create-like service
	
	Accounts pullUpAccount (int aID); // A read-like operation to retrieve account details
	
	Set<Accounts> listAccounts (int cID); // A read-like operation to retrieve all accounts per customer
	
	double balanceInquiry (Accounts account); // read-like operation to check current balance
	
	Accounts updateAccount (Accounts account); // to update account object
	
	// update-like operation to add certain amount to account's balance
	
	Accounts makeDeposit (Accounts account, double amount) throws WithdrawException; 
	
	// update-like operation that results in decreasing total account's amount by withdrawn amount
	
	Accounts withdraw (Accounts account, double amount) throws NegativeBalanceException; 
	
	boolean cancelAccount (int aID); // delete-like operation to delete a single account
									 
	boolean cancelAllAccounts (int cID); // delete-like operation. Given a cID all customer's accounts can be deleted.
										 // customer will be deleted at all accounts cancellation
}
