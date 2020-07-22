package dev.atan.daos;


import java.util.List;
import dev.atan.entities.Account;


public interface AccountDAO {
	
	//create
	Account createAccount(Account account);
	
	//read
	Account getAccountById(int id);
	List<Account> getAllAccounts();
	List<Account> getAccountsByCustomerId(int id);
	List<Account> getAccountsByBalanceLessThan(int lessThan);
	List<Account> getAccountsByBalanceGreaterThan(int greaterThan);
	
	//update
	Account updateAccount(Account account);
		
	//delete
	boolean deleteAccount(int id);
	

}
