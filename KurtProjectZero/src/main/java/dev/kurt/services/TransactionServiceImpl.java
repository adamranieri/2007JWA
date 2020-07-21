package dev.kurt.services;

import java.util.ArrayList;

import dev.kurt.daos.AccountDAO;
import dev.kurt.daos.AccountDAOMaria;
import dev.kurt.daos.TransactionDAO;
import dev.kurt.daos.TransactionDAOMaria;
import dev.kurt.entities.Transaction;

public class TransactionServiceImpl implements TransactionService{

	private static AccountDAO accDao = AccountDAOMaria.getAccountDAOMaria();
	private static TransactionDAO traDao = TransactionDAOMaria.getTransactionDAOMaria();
	
	
	@Override
	public Transaction createTransaction(Transaction transaction) {
		return traDao.createTransaction(transaction);
	}
	

	@Override
	public Transaction getTransactionById(int id) {
		return traDao.getTransactionById(id);
	}

	@Override
	public ArrayList<Transaction> getAllTransactions() {
		return traDao.getAllTransactions();
	}

	@Override
	public ArrayList<Transaction> getTransactionsByAccountId(int id) {
		return traDao.getTransactionsByAccountId(id);
	}

	@Override
	public Transaction updateTransaction(Transaction transaction) {
		return traDao.updateTransaction(transaction);
		
	}

	@Override
	public boolean deleteTransactionById(int id) {
		return traDao.deleteTransactionById(id);
	}

	

}
