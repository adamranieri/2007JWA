package dev.zak.daos;

import java.util.Set;

import dev.zak.entities.Transaction;

public interface TransactionDaoInterface {
	public Transaction createTransaction(Transaction t);
	public Transaction getTransactionById(int tId);
	public Set<Transaction>getAllTransactionsByAccount(int aId);
	public Set<Transaction>getAllTransactions();
	public Transaction updateTransation(Transaction t);
	public boolean DeleteTransactionById(int tId);
	public boolean DeleteAllTransactionsByAccount(int aId);
	public boolean DeleteAllTransactionsint();

}
