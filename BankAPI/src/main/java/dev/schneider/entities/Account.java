package dev.schneider.entities;

public class Account {
	private int acctID;
	private int cID;
	private String acctName;
	private int balance;
	
	public Account() {
		super();
	}

	public Account(int acctID, int cID, String acctName, int balance) {
		super();
		this.acctID = acctID;
		this.cID = cID;
		this.acctName = acctName;
		this.balance = balance;
	}

	public int getAcctID() {
		return acctID;
	}

	public void setAcctID(int acctID) {
		this.acctID = acctID;
	}

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}

	public String getAcctName() {
		return acctName;
	}
	
	public String getAcctName(int id) {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public int getBalance() {
		return balance;
	}
	
	public int getAcctBalance(int id) {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [acctID=" + acctID + ", cID=" + cID + ", acctName=" + acctName + ", balance=" + balance + "]";
	}
	
	
	
	
}
