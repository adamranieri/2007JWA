package dev.rutan.entities;

public class Account {

	private int aId;
	private int cId;
	private String accountName;
	private int balance;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int aId, int cId, String accountName, int balance) {
		super();
		this.aId = aId;
		this.cId = cId;
		this.accountName = accountName;
		this.balance = balance;
	}

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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [aId=" + aId + ", cId=" + cId + ", accountName=" + accountName + ", balance=" + balance + "]";
	}
	
	
	
}
