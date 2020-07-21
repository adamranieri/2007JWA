package dev.noah.entities;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	private int cId;
	private String username;
	private String password;
	private Set<Account> accounts = new HashSet<Account>();
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int cId, String username, String password) {
		super();
		this.cId = cId;
		this.username = username;
		this.password = password;
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

	@Override
	public String toString() {
		return "Customer [cId=" + cId + ", username=" + username + ", password=" + password + ", accounts=" + accounts
				+ "]";
	}
}
