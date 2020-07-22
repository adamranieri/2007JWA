package dev.dave.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Customers {
	
	//Customer's fields
	
	private int cID;               
	private String username;
	private String password;
	Set<Accounts> accounts = new HashSet<Accounts>();
	
	// no-args constructor
	
	public Customers() {
		super ();
	}
	
	// args constructor (Accounts List not included as object customer is not initialized with it)
	
		public Customers(int cID, String username, String password) {
			super ();
			this.cID = cID;
			this.username = username;
			this.password = password;
		}

	// public getters & setters

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

	public Set<Accounts> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Accounts> accounts) {
		this.accounts = accounts;
	}

	// An overridden implementation of toString() method.
	
	@Override
	public String toString() {
		return "Customers [cID=" + cID + ", username=" + username + ", password=" + password + ", accounts=" + accounts
				+ "]";
	}
}
