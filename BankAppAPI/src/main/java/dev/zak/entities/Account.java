package dev.zak.entities;

public class Account{
	private int aId;
	private int cId; //(owner of the account)
	private String accountName; //name i.e "My Savings" or "Vacation Fund"
	private float balance;

	public Account() {
		super();
	}

	public Account(int aId, int cId, String accountName, float balance) {
		super();
		this.aId = aId;
		this.cId = cId;
		this.accountName = accountName;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [aId=" + aId + ", cId=" + cId + ", accountName=" + accountName + ", balance=" + balance + "]";
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
	

}
