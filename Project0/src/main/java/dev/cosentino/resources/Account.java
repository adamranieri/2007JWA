package dev.cosentino.resources;

public class Account {

	
	private int accountId;
	private int customerId;
	private String accountName;
	private float balance;
	
	public Account() {
		super();
	}
	
	public Account(int accountId, String accountName, float balance, int customerId) {
		super();
		this.accountId = accountId;
		this.customerId = customerId;
		this.accountName = accountName;
		this.balance = balance;
	}
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", customerId=" + customerId + ", accountName=" + accountName
				+ ", balance=" + balance + "]";
	}
	
	
}
