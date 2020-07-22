package dev.schneider.entities;

public class Customer {
	private int cID;
	private String username;
	private String password;
	
	private int[] accounts;

	public Customer(int cID, String username, String password) {
		super();
		this.cID = cID;
		this.username = username;
		this.password = password;
	}

	public Customer() {
		super();
	}

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int[] getAccounts() {
		return accounts;
	}

	public void setAccounts(int[] accounts) {
		this.accounts = accounts;
	}
	
	
	
	
}
