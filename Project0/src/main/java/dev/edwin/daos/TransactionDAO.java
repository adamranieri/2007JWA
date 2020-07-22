package dev.edwin.daos;

import java.util.List;

import dev.edwin.entities.Transaction;

public interface TransactionDAO 
{
//	CRUD Operations
	
//	CREATE
	Transaction createTransaction(Transaction transaction);
	
//	READ by 1 or All
	Transaction getTransactionById(int tId);
	
	List<Transaction> getTransactions();
	
	
//	UPDATE
	Transaction updateTransaction(Transaction transaction);
	
//	DELETE
	boolean deleteTransaction(int tId);
	
}
