package dev.kurt.services;

import java.util.ArrayList;

import dev.kurt.entities.Transaction;

public interface TransactionService {

	Transaction createTransaction();
	
	Transaction getTransactionById(int id);
	ArrayList<Transaction> getAllTransactions();
	ArrayList<Transaction> getTransactionsByAccountId(int id);
	
	Transaction updateTransaction(Transaction transaction);
	
	boolean deleteTransactionById(int id);
}
