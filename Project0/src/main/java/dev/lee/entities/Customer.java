package dev.lee.entities;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	private int cID;
	private String username;
	private String password;
	private Set<Account> accounts = new HashSet<Account>();
	
	public Customer() {
		super();
	}

	public Customer(int cID, String username, String password) {
		super();
		this.cID = cID;
		this.username = username;
		this.password = password;
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

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Customer [cID=" + cID + ", username=" + username + ", password=" + password + ", accounts=" + accounts
				+ "]";
	}
	
}
