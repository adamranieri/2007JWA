package dev.winder.entities;

import java.util.HashSet;
import java.util.Set;


public class Customer {
	
	private int customerId;
	
	private String customerName;
	
	private String customerPin;
	
	private Set<BankAccount> customerAccounts = new HashSet<BankAccount>();
	
	public static int currentCidToAddToBank = 0;
	
	
	//empty constructor
	public Customer() {
		super();
	}

	public Customer(int customerId, String customerName, String customerPin) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPin = customerPin;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPin() {
		return customerPin;
	}

	public void setCustomerPin(String customerPin) {
		this.customerPin = customerPin;
	}
	
	public void addToCurrentCustomerAccounts(BankAccount currentBankAccount) {
		
		this.customerAccounts.add(currentBankAccount);
	}
	
	public Set<BankAccount> returnAllCurrentCustomerAccounts(){
		
		return this.customerAccounts;
	}
	

	
}
