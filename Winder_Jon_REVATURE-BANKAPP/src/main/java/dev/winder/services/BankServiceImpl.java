package dev.winder.services;

import java.util.Set;

import dev.winder.bankappexceptions.InsufficientFundsException;
import dev.winder.daos.BankAccountDAO;
import dev.winder.daos.BankAccountDAOMaria;
import dev.winder.daos.BankTransactionDAO;
import dev.winder.daos.BankTransactionDAOMaria;
import dev.winder.daos.CustomerDAO;
import dev.winder.daos.CustomerDAOMaria;
import dev.winder.entities.BankAccount;
import dev.winder.entities.Customer;
import dev.winder.entities.Transaction;


//Transaction services are a bank service
public class BankServiceImpl implements BankServices {
	
	
	private static BankAccountDAO bDao= BankAccountDAOMaria.getBankAccountDAOMaria();
	
	private static BankTransactionDAO tDao = BankTransactionDAOMaria.getBankTransactionDAOMaria();
	
	private static CustomerDAO cDao = CustomerDAOMaria.getCustomerDAOMaria();

	@Override
	public BankAccount createBankAccount(BankAccount bankAccount) {
		
		return bDao.createBankAccount(bankAccount);
	}

	@Override
	public BankAccount getBankAccountByAid(int id) {
		// TODO Auto-generated method stub
		return bDao.getBankAccountByAid(id);
	}

	@Override
	public Set<BankAccount> getAllBankAccountsInBranch() {
		return bDao.getAllBankAccountsInBranch();
	}

	@Override
	public Set<BankAccount> getAllCurrentCustomerAccounts(int id) {
		// TODO Auto-generated method stub
		return bDao.getAllCurrentCustomerAccounts(id);
	}

	@Override
	public BankAccount updateBankAccountName(BankAccount bankAccount,String name) {
		bankAccount.setAccountName(name);
		bDao.updateBankAccount(bankAccount);
		return bankAccount;
	}

	@Override
	public BankAccount returnBalanceByAid(int aid) {
		// TODO Auto-generated method stub
		return bDao.returnBalanceByAid(aid);
	}

	@Override
	public Boolean deleteBankAccount(int aid) {
		// TODO Auto-generated method stub
		return bDao.deleteBankAccount(aid);
	}

	@Override
	//also update the customer table
	public Boolean removeThisCustomersAccounts(int cid) {
		return bDao.removeThisCustomersAccounts(cid);
	}

	@Override
	//int transactionId,  double prevBalance, double finalBalance, int aId, automatically updates the bank account
	public Transaction depositFunds(Transaction transaction, double d)throws InsufficientFundsException {
		
		if(d < 0) {
			throw new InsufficientFundsException();
		}
		return tDao.depositFunds(transaction, d);
	}

	@Override
	public Transaction withdrawal(Transaction transaction, double d) throws InsufficientFundsException {
		if(d> transaction.getPrevBalance()) {
			throw new InsufficientFundsException();
		}
		
		return tDao.withdrawal(transaction, d);
	}

}
