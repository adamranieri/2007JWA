package dev.dave.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import dev.dave.daos.AccountsDAO;
import dev.dave.daos.AccountsDAODB;
import dev.dave.daos.CustomersDAO;
import dev.dave.daos.CustomersDAODB;
import dev.dave.entities.Accounts;
import dev.dave.entities.Customers;
import dev.dave.utils.ConnectionUtil;


@TestMethodOrder(OrderAnnotation.class) // in this case we'll need to run tests in order as we need the customer object created to subsequently be able to retrieve, update and delete it

class AccountsDAOTestsDB {
	
	private static AccountsDAO adao = AccountsDAODB.getAccountsDAODB();
	private static CustomersDAO cdao = CustomersDAODB.getCustomersDAODB();

	// to set up our DB tables
	
	@BeforeAll
	static void setUp() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_bank_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void createAccount() {
		
		// In this case our table will not create an account unless it is linked to a customer as cID is the foreign key
		// so, we'll instantiate a customer object first and call for its creation using the custdao
		
		Customers john = new Customers(0, "johndoe18", "BreakitDown638!"); //we pass in zero as ID since customer is not created yet and doesn't actually have an ID assigned until created
		cdao.createCustomer(john); // this calls the DAO implementation to create a customer in the DB
		
		// now, we can test the account creation
		
		Accounts funds = new Accounts(0, 100, "Budget", john.getcID()); //we pass in zero as aID since account is not created yet and doesn't actually have an aID assigned until created. We also pull the customer's cID
		adao.createAccount(funds);// this calls the DAO to create an account
		Assertions.assertNotEquals(0, funds.getaID()); // since in a real life scenario it would be hard to tell what the ID number assigned would be, we'll assert that it is not zero in order to ensure account creation
		Assertions.assertEquals(1, funds.getaID()); // this is the first account we'll create, as long as that's the case we can also assert that aID will be 1
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
		Accounts scholarship = new Accounts(0, 50000, "School Savings", 1); // we create a new account, we pass in a cID from an existing customer. Our database can map different accounts to the same customer.
		adao.createAccount(scholarship); // we call the method to create the new account
		Set<Accounts> allaccs = adao.getAllAccounts(); // we need a set as our method returns a set of accounts
		Assertions.assertEquals(2, allaccs.size()); // we expect the size of the set to be two
	}
	
	@Test
	@Order(4)
	void getAllAccountsByCustomer() {
		Customers jane = new Customers(0, "janedoe35", "Testing7#!"); 
		cdao.createCustomer(jane); // we'll throw in a new customer to have more than one
		Accounts janeSavings = new Accounts(0, 80000, "Savings", jane.getcID());
		adao.createAccount(janeSavings); // we create an account for this new customer
		Accounts joyride = new Accounts(0, 50000, "Vacations", 1);  // now we create another account for our first customer
		adao.createAccount(joyride); // we call the method to create the new account
		Set<Accounts> allcustaccs = adao.getAllAccountsByCustomer(1); // we need a set as our method returns a set of accounts
		Assertions.assertEquals(3, allcustaccs.size()); // we expect the size of the set to be three
	}
	
	@Test
	@Order(5)
	void updateAccount() {
		Accounts sship = adao.getAccByID(2); // we only create a new object to store the retrieved account object and not have objects named the same
		sship.setBalance(10000); // we make changes to the object, in this case we're increasing the balance
		sship = adao.updateAccount(sship); // we call the update method to save changes
		Assertions.assertEquals(10000, sship.getBalance()); // if balance is updated it'll be as expected
	}
	
	@Test
	@Order(6)
	void deleteAccount() {
		boolean result = adao.deleteAccount(2); // we call the method to delete account with ID 2
		Assertions.assertEquals(true, result); // the returned value should be true as expected, meaning something was deleted
	}
	
	@Test
	@Order(7)
	void deleteAccountNegative() {
		boolean result = adao.deleteAccount(5); // we call the method to delete an account that doesn't exist
		Assertions.assertEquals(false, result); // the returned value should be false as expected, meaning there was no such record to begin with, something that does not exist cannot be deleted
	}
	
	@Test
	@Order(8)
	void deleteAllCustAccounts() {
		Customers john = cdao.getCustByID(1);
		boolean result = adao.deleteAllCustAccounts(1); // we call the method to delete accounts from customer with cID 1
		Assertions.assertEquals(true, result); // the returned value should be true as expected, meaning something was deleted
		Assertions.assertEquals(0, john.getAccounts().size()); // we can also assert that since john had several accounts, had they been deleted he should now have 0 accounts under him
	}
	
	// to drop the tables after testing is done with
	
	@AfterAll
	static void tearDown() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_bank_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
