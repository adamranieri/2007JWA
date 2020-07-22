package dev.dave.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import dev.dave.daos.AccountsDAO;
import dev.dave.daos.AccountsDAOLocal;
import dev.dave.entities.Accounts;


@TestMethodOrder(OrderAnnotation.class) // in this case we'll need to run tests in order as we need the customer object created to subsequently be able to retrieve, update and delete it

class AccountsDAOTestsLocal {
	
	public static AccountsDAO adao = AccountsDAOLocal.getAccountsDAO();

	@Test
	@Order(1)
	void createAccount() {
		Accounts funds = new Accounts(0, 100, "Budget", 1); //we pass in zero as ID since account is not created yet and doesn't actually have an ID assigned until created, 1 is just a mock uID, were not testing to see whether this assigns the account to customer with cID 1, just that the method will create the account
		adao.createAccount(funds);// this calls the local DAO implementation to create an account
		Assertions.assertNotEquals(0, funds.getaID()); // since in a real life scenario it would be hard to tell what the ID number assigned would be, we'll assert that it is not zero in order to ensure account creation
	}
	@Test
	@Order(2)
	void getAccByID() {
		Accounts funds = adao.getAccByID(1); //our now existing account object has an ID of 1
		Assertions.assertEquals(1, funds.getaID()); // we'll assert that we can retrieve the object by comparing the expected ID vs the actual ID from the object. If the object is retrieved the IDs will match
	}
	
	@Test
	@Order(3)
	void getAllAccounts() {
		Accounts scholarship = new Accounts(0, 50000, "School Savings", 2); // we create a new account, we pass in a different cID as our mock database (the map) could not hold duplicate keys. In real life our database can map different accounts to the same customer though.
		adao.createAccount(scholarship); // we call the method to create the new account
		Set<Accounts> allaccs = adao.getAllAccounts(); // this method returns a set of accounts
		Assertions.assertEquals(2, allaccs.size()); // we expect the size of the set to be two
	}

	@Test
	@Order(4)
	void updateAccount() {
		Accounts sship = adao.getAccByID(2); // we only create a new object to store the retrieved account object and not have objects named the same
		sship.setBalance(10000); // we make changes to the object, in this case we're increasing the balance
		sship = adao.updateAccount(sship); // we call the update method to save changes
		Assertions.assertEquals(10000, sship.getBalance()); // if balance is updated it'll be as expected
	}

	@Test
	@Order(5)
	void deleteAccount() {
		boolean result = adao.deleteAccount(1); // we call the method to delete account with ID 1
		Assertions.assertEquals(true, result); // the returned value should be true as expected, meaning something was deleted
	}
	
	@Test
	@Order(6)
	void deleteAccountNegative() {
		boolean result = adao.deleteAccount(5); // we call the method to delete an account that doesn't exist
		Assertions.assertEquals(false, result); // the returned value should be false as expected, meaning there was no such record to begin with, something that does not exist cannot be deleted
	}
}
