package dev.winder.entities;

public class Transaction {

	
	private int transactionId;
	
	private int aId;
	
	private double prevBalance;
	
	private double finalBalance;
	
	
	public Transaction() {}


	public Transaction(int transactionId,  double prevBalance, double finalBalance, int aId) {
		super();
		this.transactionId = transactionId;
		this.aId = aId;
		this.prevBalance = prevBalance;
		this.finalBalance = finalBalance;
	}


	public int getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
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
	
	
	
	
	
	

	
}//end transaction
