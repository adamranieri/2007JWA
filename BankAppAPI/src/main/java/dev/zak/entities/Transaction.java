package dev.zak.entities;

public class Transaction {

	private int tId;
	private int aId;
	private float prevBalance;
	private float finalBalance;
	public Transaction() {
		super();
	}
	public Transaction(int tId, int aId, float prevBalance, float finalBalance) {
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
	public float getPrevBalance() {
		return prevBalance;
	}
	public void setPrevBalance(float prevBalance) {
		this.prevBalance = prevBalance;
	}
	public float getFinalBalance() {
		return finalBalance;
	}
	public void setFinalBalance(float finalBalance) {
		this.finalBalance = finalBalance;
	}
	
	@Override
	public String toString() {
		return "Transaction [tid=" + tId + ", aid=" + aId + ", prevBalance=" + prevBalance + ", finalBalance="
				+ finalBalance + "]";
	}

}
