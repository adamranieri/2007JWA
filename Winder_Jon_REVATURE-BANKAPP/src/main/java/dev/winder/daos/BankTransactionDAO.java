package dev.winder.daos;

import dev.winder.bankappexceptions.InsufficientFundsException;
import dev.winder.entities.Transaction;

public interface BankTransactionDAO {
	

	Transaction depositFunds(Transaction transaction, double d);
	
	Transaction withdrawal(Transaction transaction, double d) throws InsufficientFundsException;
	
	
	

}
