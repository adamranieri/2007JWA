package dev.edwin.entities;

public class Transaction 
{
	private int tId;
	private int aId;
	private double prevBalance;
	private double finalBalance;
	
	
	public Transaction() {
		super();
	}


	public Transaction(int tId, int aId, double prevBalance, double finalBalance) {
		super();
		this.tId = tId;
		this.aId = aId;
		this.prevBalance = prevBalance;
		this.finalBalance = finalBalance;
	}


	public int gettId() {
		return tId;
	}


	public void settId(int tId) {
		this.tId = tId;
	}


	public int getaId() {
		return aId;
	}


	public void setaId(int aId) {
		this.aId = aId;
	}


	public double getPrevBalance() {
		return prevBalance;
	}


	public void setPrevBalance(double prevBalance) {
		this.prevBalance = prevBalance;
	}


	public double getFinalBalance() {
		return finalBalance;
	}


	public void setFinalBalance(double finalBalance) {
		this.finalBalance = finalBalance;
	}


	@Override
	public String toString() {
		return "Transaction [tId=" + tId + ", aId=" + aId + ", prevBalance=" + prevBalance + ", finalBalance="
				+ finalBalance + "]";
	}
	
	
	
	
	
	
}
