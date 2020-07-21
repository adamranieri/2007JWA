package dev.kurt.entities;

public class Transaction {

	private int tId;
	private double prevBalance;
	private double finalBalance;
	private int aId;
	
	public Transaction() {
		super();
		
	}
	public Transaction(int tId, double prevBalance, double finalBalance, int aId) {
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
		return "Transaction [tId=" + tId + ", prevBalance=" + prevBalance + ", finalBalance=" + finalBalance + ", aId="
				+ aId + "]";
	}
	
	
}
