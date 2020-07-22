package dev.lee.entities;

import java.math.BigDecimal;

public class Account {

	private int aID;
	private int cID;
	private String accountName;
	private BigDecimal balance;
	
	public Account() {
		super();
	}

	public Account(int aID, int cID, String accountName, BigDecimal balance) {
		super();
		this.aID = aID;
		this.cID = cID;
		this.accountName = accountName;
		this.balance = balance;
	}

	public int getaID() {
		return aID;
	}

	public void setaID(int aID) {
		this.aID = aID;
	}

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [aID=" + aID + ", cID=" + cID + ", accountName=" + accountName + ", balance=" + balance + "]";
	}
}
