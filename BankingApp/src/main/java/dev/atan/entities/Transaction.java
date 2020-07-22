package dev.atan.entities;

public class Transaction {

	/*- Transaction attributes
	- tId
	- aId (account this transaction is part of)
	- prevBalance
	- finalBalance*/
	
	private int tID;
	private int aID;
	private int prevBalance;
	private int finalBalance;
	
	public Transaction() {

	}

	public Transaction(int tID, int aID, int prevBalance, int finalBalance) {
		super();
		this.tID = tID;
		this.aID = aID;
		this.prevBalance = prevBalance;
		this.finalBalance = finalBalance;
	}

	public int gettID() {
		return tID;
	}

	public void settID(int tID) {
		this.tID = tID;
	}

	public int getaID() {
		return aID;
	}

	public void setaID(int aID) {
		this.aID = aID;
	}

	public int getPrevBalance() {
		return prevBalance;
	}

	public void setPrevBalance(int prevBalance) {
		this.prevBalance = prevBalance;
	}

	public int getFinalBalance() {
		return finalBalance;
	}

	public void setFinalBalance(int finalBalance) {
		this.finalBalance = finalBalance;
	}

	@Override
	public String toString() {
		return "Transaction [tID=" + tID + ", aID=" + aID + ", prevBalance=" + prevBalance + ", finalBalance="
				+ finalBalance + "]";
	}
	
	
	
	
}
