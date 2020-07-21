package dev.kurt.entities;

/*
	- customers
	- Accounts
	customers have accounts 
	customer attributes
	    - cId
	    - username
	    - password
	    - array of accounts
	account attributes
	    - aId
	    - cId (owner of the account)
	    - account name i.e "My Savings" or "Vacation Fund"
	    - balance
 */
public class Account {
	
	private int aId;
	private String accountName;
	private double accountBalance;
	private int cId;
	
	public Account() {
		super();
	}

	public Account(int aId, String accountName, double accountBalance, int cId)
	{
		super();
		this.aId = aId;
		this.accountName = accountName;
		this.accountBalance = accountBalance;
		this.cId = cId;
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
	
	public double getAccountBalance() {
		return accountBalance;
	}
	
	public void setAccountBalance(double accountBalance){
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "Account [aId=" + aId + ", accountName=" + accountName + ", accountBalance=" + accountBalance + ", cId="
				+ cId + "]";
	}
	

	
	
	
	
}
