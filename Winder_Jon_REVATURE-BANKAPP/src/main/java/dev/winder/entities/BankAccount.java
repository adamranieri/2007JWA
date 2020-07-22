package dev.winder.entities;

import dev.winder.bankappexceptions.InsufficientFundsException;

public class BankAccount {
	
	
	private int accountId;
	
	private String accountName;
	
	private double accountBalance;
	
	private int customerId;
	
	public BankAccount() {
		super();
	}
	
	//this branch has a minimum balance that has to be deposited in order to open an account
	public BankAccount(int aId, String aName, double aBal, int cId)  {
		this.accountId = aId;
		this.accountName = aName;
		this.accountBalance = aBal;
		this.customerId = cId;
	}


	public int getAccountId() {
		return accountId;
	}


	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public double getAccountBalance() {
		return accountBalance;
	}


	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	
	public void setCustomerId(int id) {
		
		this.customerId = id;
	}

	public int getCustomerId() {
		return customerId;
	}
	
	
	
	
	

}
