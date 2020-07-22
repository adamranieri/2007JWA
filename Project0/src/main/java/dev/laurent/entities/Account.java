package dev.laurent.entities;

public class Account {
	private int aId;
	private int cId;
	private String accountName;
	private double balance;
	
	public Account() {
		super();
	}
	
	public Account(int aId, int cId, String accountName, double balance) {
		super();
		this.aId = aId;
		this.cId = cId;
		this.accountName = accountName;
		this.balance = balance;
	}
	public int getAccId() {
		return aId;
	}
	public void setAccId(int aId) {
		this.aId = aId;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getAccName() {
		return accountName;
	}
	public void setAccName(String accountName) {
		this.accountName = accountName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Account [aId=" + aId + ", cId=" + cId + ", accountName=" + accountName + ", balance=" + balance + "]";
	}
	
	
}
