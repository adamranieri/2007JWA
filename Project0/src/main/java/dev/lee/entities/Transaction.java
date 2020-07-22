package dev.lee.entities;

import java.math.BigDecimal;

public class Transaction {

	private int tId;
	private int aId;
	private BigDecimal prevBalance;
	private BigDecimal finalBalance;
	private BigDecimal amount;

	public Transaction() {
		super();
	}

	public Transaction(int tId, int aId, BigDecimal prevBalance, BigDecimal finalBalance, BigDecimal amount) {
		super();
		this.tId = tId;
		this.aId = aId;
		this.prevBalance = prevBalance;
		this.finalBalance = finalBalance;
		this.amount = amount;
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

	public BigDecimal getPrevBalance() {
		return prevBalance;
	}

	public void setPrevBalance(BigDecimal prevBalance) {
		this.prevBalance = prevBalance;
	}

	public BigDecimal getFinalBalance() {
		return finalBalance;
	}

	public void setFinalBalance(BigDecimal finalBalance) {
		this.finalBalance = finalBalance;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [tId=" + tId + ", aId=" + aId + ", prevBalance=" + prevBalance + ", finalBalance="
				+ finalBalance + ", amount=" + amount + "]";
	}
}
