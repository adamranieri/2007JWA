package dev.alsabea.daos;

import java.util.List;

import dev.alsabea.entities.Account;

public interface AccountDao extends CRUD<Account>{

	
	List<Account> getAllCustomerAccounts(int id);
}
