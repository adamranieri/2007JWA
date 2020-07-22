package dev.atan.entities;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	
	/*customer attributes
	- cId
	- username
	- password
	- array of accounts */
	
	private int cID;
	private String userName;
	private String cPassword;
	private int openAccounts;
	
	List<Account> openAccountsArray = new ArrayList<Account>();
	
	public Customer() {
	}

	public Customer(int cID, String userName, String cPassword, int openAccounts) {
		super();
		this.cID = cID;
		this.userName = userName;
		this.cPassword = cPassword;
		this.openAccounts = openAccounts;
	}

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getcPassword() {
		return cPassword;
	}

	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}

	public List<Account> getOpenAccountsArray() {
		return openAccountsArray;
	}

	public void setOpenAccountsArray(List<Account> openAccountsArray) {
		this.openAccountsArray = openAccountsArray;
	}
	
	public void addAccountsToArr(Account newAccount) {
		this.openAccountsArray.add(newAccount);
	}
	
	public void removeAccountsFromArr(Account account) {
		this.openAccountsArray.remove(account);
	}
		

	public void setOpenAccounts(int openAccounts) {
		this.openAccounts = openAccounts;
	}
	
	public int getOpenAccounts() {
		return openAccounts;
	}

	@Override
	public String toString() {
		return "Customer (cID=" + cID + ", userName=" + userName + ", cPassword=" + cPassword + ", openAccounts="
				+ openAccounts + ")";
	}
	
	
	
	

}
