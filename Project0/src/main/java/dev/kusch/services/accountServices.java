package dev.kusch.services;

import dev.kusch.entities.Account;

public interface accountServices {

	// CRUD like operations
	Account startAccount(String name, float balance);
	
	Account withdrawMoney(float amount);
	
	Account depositMoney(float amount);
	
	Account checkBalance();
	
	Account endAccoutn();
	
}
