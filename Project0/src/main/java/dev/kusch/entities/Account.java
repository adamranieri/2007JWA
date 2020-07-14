package dev.kusch.entities;

public class Account {

	private static int currentId = 1;
	
	private int aId;
	private int cId;
	private String name;
	private float balance;
	
	Account(String name, float balance, int cId) {
		this.name = name;
		this.balance = balance;
		this.cId = cId;
		this.aId = currentId;
		++currentId;
	}
	
	Account(String name, int cId) {
		this.name = name;
		this.balance = 0;
		this.cId = cId;
		this.aId = currentId;
		++currentId;
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

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	
}
