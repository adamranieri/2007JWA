package dev.kusch.services;

import java.util.List;

import dev.kusch.entities.Account;
import dev.kusch.entities.Customer;

public interface customerServices {

	// CRUD like operations
	Customer addAccount(Account newAccount);
	
	Customer changeUsername(String newUser);
	
	Customer changePassword(String newPass);
	
	Customer dropAccount(Account deadAccount);
}
