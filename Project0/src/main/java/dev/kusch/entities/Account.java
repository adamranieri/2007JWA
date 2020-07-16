package dev.kusch.entities;

public class Account {
	
	private int aId;
	private int cId;
	private String name;
	private double balance;
	
	public Account(String name, double d, int cId, int aid) {
		this.name = name;
		this.balance = d;
		this.cId = cId;
		this.aId = aid;
	}
	
	public Account(String name, int cId, int aid) {
		this.name = name;
		this.balance = 0;
		this.cId = cId;
		this.aId = aid;
	}
	
	public Account() {
		
	}

	@Override
	public String toString() {
		return "Account [aId=" + aId + ", cId=" + cId + ", name=" + name + ", balance=" + balance + "]";
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	
}
