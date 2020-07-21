package dev.kurt.daos;

import java.util.ArrayList;

import dev.kurt.entities.Transaction;

public interface TransactionDAO {

	Transaction createTransaction(Transaction transaction);
	
	Transaction getTransactionById(int id);
	ArrayList<Transaction> getAllTransactions();
	ArrayList<Transaction> getTransactionsByAccountId(int id);
	
	Transaction updateTransaction(Transaction transaction);
	
	boolean deleteTransactionById(int id);
	
	
}
