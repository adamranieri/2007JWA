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

import dev.dave.daos.CustomersDAO;
import dev.dave.daos.CustomersDAODB;
import dev.dave.entities.Customers;
import dev.dave.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class) // in this case we'll need to run tests in order as we need the customer object created to subsequently be able to retrieve, update and delete it

class CustomersDAOTestsDB {
	
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
	void createCustomer() {
		Customers john = new Customers(0, "johndoe18", "BreakitDown638!"); //we pass in zero as ID since customer is not created yet and doesn't actually have an ID assigned until created
		cdao.createCustomer(john); // this calls the DAO implementation to create a customer in the DB
		Assertions.assertNotEquals(0, john.getcID()); // we can assert that cID is not zero in order to ensure customer creation
		Assertions.assertEquals(1, john.getcID()); // this is the first customer we'll create, as long as that's the case we can also assert that cID will be 1
	}
	
	@Test
	@Order(2)
	void getCustByID() {
		Customers john = cdao.getCustByID(1); //our now existing customer object john has an ID of 1
		Assertions.assertEquals(1, john.getcID()); //we'll assert that we can retrieve the object by comparing the expected ID vs the actual ID from the object. If the object is retrieved the IDs will match
	}
	
	@Test
	@Order(3)
	void getAllCustomers() {
		Customers jane = new Customers(0, "janedoe89", "123Tamarindo!"); // we create a new customer, to retrieve all, it's better to have more than one
		cdao.createCustomer(jane); // the method to create the new customer
		Set<Customers> allcusts = cdao.getAllCustomers(); // since our method returns a set of customers, we create a set of customers to store the result and call the method
		Assertions.assertEquals(2, allcusts.size()); //now, we assert by comparing an expected 2, to the size of the set, which in this case we know to be 2
	}
	
	@Test
	@Order(4)
	void getCustByUsername() {
		Customers jane =  cdao.getCustByUsername("janedoe89"); //our now existing customer object jane has "janedoe89" for username
		Assertions.assertEquals("janedoe89", jane.getUsername()); 
	}
	
	@Test
	@Order(5)
	void updateCustomer() {
		Customers j = cdao.getCustByID(1); // we only create a new object to store the retrieved customer object and not have objects named the same
		j.setPassword("Breathofthewild2017"); // we make changes to the object, in this case we set the password to a different value than the initial one
		j = cdao.updateCustomer(j); // we'll perform the update on the object retrieved, we call the method to update it, which is the same as saving the changes made to it
		Assertions.assertEquals("Breathofthewild2017",j.getPassword()); // we assert that the expected new password is the same one as the one we retrieve after updating the customer object
	}
	
	@Test
	@Order(6)
	void deleteCustomer() {
		boolean result = cdao.deleteCustomer(2); // we call the method to delete customer with ID 2
		Assertions.assertEquals(true, result); // the returned value should be true as expected, meaning something was deleted
	}
	
	@Test
	@Order(7)
	void deleteCustomerNegative() {
		boolean result = cdao.deleteCustomer(3); // we call the method to delete customer with ID 3, which does not exist as we have only added 1 and 2
		Assertions.assertEquals(false, result); // the returned value should be false as expected, meaning there was no such record to begin with, something that does not exist cannot be deleted
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
