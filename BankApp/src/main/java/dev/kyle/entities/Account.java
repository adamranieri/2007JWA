package dev.kyle.entities;

public class Account {

	private int aid;
	private int cid; // foreign key
	private String accName;
	private double balance;
	
	public Account(int aid, int cid, String accName, double balance) {
		super();
		this.aid = aid;
		this.cid = cid;
		this.accName = accName;
		this.balance = balance;
	}
	
	public Account() {
		super();
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [aid=" + aid + ", cid=" + cid + ", accName=" + accName + ", balance=" + balance + "]";
	}
	
	
}
