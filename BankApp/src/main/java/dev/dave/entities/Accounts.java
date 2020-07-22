package dev.dave.entities;

public class Accounts {
	
	// Account's fields
	
	private int aID;
	private double balance;
	private String accountname;
	private int cID;
	
	// no-args constructor
	
	public Accounts() {
		super();
	}

	// args constructor
	
	public Accounts(int aID, double balance, String accountname, int cID) {
		super();
		this.aID = aID;
		this.balance = balance;
		this.accountname = accountname;
		this.cID = cID;
	}
	
	// public getters & setters

	public int getaID() {
		return aID;
	}

	public void setaID(int aID) {
		this.aID = aID;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}

	// An overridden implementation of toString() method.
	
	@Override
	public String toString() {
		return "Accounts [aID=" + aID + ", balance=" + balance + ", accountname=" + accountname + ", cID=" + cID + "]";
	}		
}
