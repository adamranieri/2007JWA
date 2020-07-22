package dev.kyle.entities;

import java.util.HashSet;
import java.util.Set;

// Java Bean
public class Customer {

	private int cid;
	private String username;
	private String password;
	
	private Set<Account> accounts = new HashSet<Account>();
	
	public Customer(int cid, String username, String password) {
		super();
		this.cid = cid;
		this.username = username;
		this.password = password;
	}

	public Customer() {
		super();
	}
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
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
		return "Customer [cid=" + cid + ", username=" + username + ", password=" + password + ", accounts=" + accounts
				+ "]";
	}

}
