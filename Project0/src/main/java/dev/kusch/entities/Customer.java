package dev.kusch.entities;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	// hold data retrieved from database
	private int cId;
	private String username;
	private String password;
	private Set<Account> accounts;
	
	public Customer(int cId, String username, String password) {
		this.username = username;
		this.password = password;
		this.accounts = new HashSet<Account>();
		this.cId = cId;
	}
	
	public Customer() {
		
	}

	@Override
	public String toString() {
		return "Customer [cId=" + cId + ", username=" + username + ", password=" + password + ", accounts=" + accounts
				+ "]";
	}
	
	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
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
	
	
}
