package dev.dave.daos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dev.dave.entities.Customers;

public class CustomersDAOLocal implements CustomersDAO {
	
	// We're making this DAO a singleton
	
	private static CustomersDAOLocal custdao = null; // static copy of itself
	
	private CustomersDAOLocal()  // private constructor
	{
		
	};
	
	// a public static method responsible for getting us an instance of the class
	// this will ensure an instance of the class is only created once...
	
	public static CustomersDAO getCustomersDAO() 
	{
		if (custdao == null) // ... if custdao doesn't exist...
		{
			custdao = new CustomersDAOLocal(); // ... then it creates it...
			return custdao;
		}
		else 								   // ... otherwise it just returns it since it would
		{                                      // already exist.  
			return custdao;
		}
	}
	
	// we create a map to store our object from the Customers class, we'll map it out 
	// to an Integer object and that's the way we'll create a customer along with their ID
	// this is like we would add the object into a DataBase
	// the counter starts at 1, when we start adding customers, it will be incremented
	// first customer gets ID = 1, and so on
	
	private Map<Integer, Customers> customersTable = new HashMap<Integer, Customers>(); 
	
	private int counter = 1;
	
	// Create operation
	
	public Customers createCustomer(Customers customer) {
		customer.setcID(counter); //first we need to set the ID, so we're passing in the counter when calling for our ID setter from Customers
		this.customersTable.put(customer.getcID(), customer); // adds customer record into the table
		this.counter++; //to have the counter increase by 1, so next customer will get the next number for their ID
		return customer; // the method will return the customer created
	}
	
	// Read operations
	
	// this one retrieves the customer object searching it by cID

	public Customers getCustByID(int ID) {
		return customersTable.get(ID);
	}
	
	// this other one will retrieve all customer objects stored in a set (no duplicates)

	public Set<Customers> getAllCustomers() {
		Set<Customers> allcusts = new HashSet<Customers>(customersTable.values()); 
		return allcusts;
	}
	
	// Update operation

	public Customers updateCustomer(Customers customer) {
		customersTable.put(customer.getcID(), customer); // we call .put() method and we pass in the customer's ID and the customer as that's what our map contains and what we're looking to update
		return customer; // customer is returned once updated
	}
	
	// Delete operation

	public boolean deleteCustomer(int ID) {
		Customers cust = customersTable.remove(ID); // to pinpoint the record (customer object) to delete based on the ID passed in
		
		if (cust != null) // after delete operations being performed, if cust value is not null...
		{
			return true; // ... then something was removed
		}
		else 
		{
			return false; // else it has not been deleted
		}
	}

	@Override
	public Customers getCustByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
