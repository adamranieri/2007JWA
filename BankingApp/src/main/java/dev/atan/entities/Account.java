package dev.atan.entities;

import dev.atan.exceptions.NegativeBalance;

public class Account {
	/*account attributes
	- aId
	- cId (owner of the account)
	- account name i.e "My Savings" or "Vacation Fund"
	- balance */
	
	private int aID;
	private int cID;
	private String aName;
	private double aBalance;
	
	public Account(int aID, int cID, String aName, double aBalance) throws NegativeBalance {
		super();
		this.aID = aID;
		this.cID = cID;
		this.aName = aName;
		if(aBalance < 0) {
			throw new NegativeBalance();
			}
		else {
		this.aBalance = aBalance;}
	}
	
	public Account() {
		
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

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public double getaBalance() {
		return aBalance;
	}

	public void setaBalance(double aBalance) throws NegativeBalance {
		if(aBalance < 0) {
			throw new NegativeBalance();
			}
		else {
		this.aBalance = aBalance;}
	}

	@Override
	public String toString() {
		return "Account [aID=" + aID + ", cID=" + cID + ", aName=" + aName + ", aBalance=" + aBalance + "]";
	}
	
	
	
	

}
