package dev.nauman.entities;

import dev.nauman.exceptions.NegativeBalanceException;

public class Account{

	private int aId; //account id
	private int cId; //customer id who owns this account
	private String accountName; //such as My Savings or Vacation Fund
	private double balance; //must be positive
	
	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) throws NegativeBalanceException {
		if(balance < 0) {
			this.balance = 0;
			throw new NegativeBalanceException();
		}else {
			this.balance = balance;
		}
	}
	public Account() {
		super();
	}
	public Account(int aId, int cId, String accountName, double balance) throws NegativeBalanceException {
		super();
		this.aId = aId;
		this.cId = cId;
		this.accountName = accountName;
		if(balance < 0) {
			this.balance = 0;
			throw new NegativeBalanceException();
		}else {
			this.balance = balance;
		}
	}
	@Override
	public String toString() {
		return "Account [aId=" + aId + ", cId=" + cId + ", accountName=" + accountName + ", balance=" + balance + "]";
	}
	public boolean equals(Account a) {
		if(a.aId == this.aId)
			return true;
		return false;
	}
}