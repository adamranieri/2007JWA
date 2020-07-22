package dev.edwin.daos;

import java.util.List;

import dev.edwin.entities.Transaction;

public class TransactionDAOImp implements TransactionDAO 
{
	
	private static TransactionDAO tDao = null;
	
	private TransactionDAOImp()
	{}
	
	public static TransactionDAO getTransactionDAO()
	{
		if(tDao == null)
		{
			tDao = new  TransactionDAOImp();
			return tDao;
		}
		return tDao;
	}

	public Transaction createTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}

	public Transaction getTransactionById(int tId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Transaction> getTransactions() {
		// TODO Auto-generated method stub
		return null;
	}



	public boolean deleteTransaction(int tId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Transaction updateTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}

}
