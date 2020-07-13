package dev.kusch.entities;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private static int currentId = 1;
	
	// hold data retrieved from database
	private int cId;
	private String username;
	private String password;
	private List<Account> accounts;
	
	public Customer(String username, String password) {
		this.username = username;
		this.password = password;
		this.accounts = new ArrayList<Account>();
		this.cId = currentId;
		++currentId;
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

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	
}
