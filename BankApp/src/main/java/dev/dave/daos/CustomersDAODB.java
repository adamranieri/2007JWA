package dev.dave.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.dave.entities.Customers;
import dev.dave.utils.ConnectionUtil;

public class CustomersDAODB implements CustomersDAO{
	
	// Making DAO a singleton
	
	private static CustomersDAO cdbdao = null; // static copy of itself
	
	// private constructor
	
	private CustomersDAODB() { 
		
	}
	
	// a public static method responsible for getting us an instance of the class
	// this will ensure an instance of the class is only created once...
	
	public static CustomersDAO getCustomersDAODB() 
	{
		if (cdbdao == null) 
		{
			cdbdao = new CustomersDAODB();
			return cdbdao;
		}
		else 
		{
			return cdbdao;
		}
	}
	
	// Below is what's called a try with resources. It ensures the connection to the DB is closed
	// when method's operation wraps.

	// CREATE
	
	// our SQL query, in this case an INSERT command as we are creating a customer 
	// object and persisting it to the DB
	
	//we'll use a Java JDBC PreparedStatement to execute the commands against the DB
	// this is helpful in preventing SQL injections as we can insert parameters 
	// into the SQL statement, instead of passing them directly.
	
	@Override
	public Customers createCustomer(Customers customer) {
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "INSERT INTO bank_db.CUSTOMERS VALUES (?,?,?)"; 
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0); // inserting parameters into the prepared statement
			ps.setString(2, customer.getUsername()); // inserting parameters into the prepared statement
			ps.setString(3, customer.getPassword()); // inserting parameters into the prepared statement
			ps.execute(); // executing the prepared statement
			
			ResultSet rs = ps.getGeneratedKeys(); //we create a result set
			rs.next(); // since the result set points to a value that's prior to the first record we call the next method
			int key = rs.getInt("cID"); // when we create our object we always pass in 0 for the cID as the actual cID will be assigned in the DB
										// this is to get that value from the DB
			customer.setcID(key); 		// and this is set it to the object
			
			return customer; // a customer object is returned
			
		} catch (SQLException e) { //our try with resources block also has a catch for SQL exceptions
			e.printStackTrace();
			return null;
		}		
	}
	
	// READ

	@Override
	public Customers getCustByID(int ID) {

		try (Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "SELECT * FROM bank_db.CUSTOMERS WHERE cID = ?"; //in this case we are querying the DB (SELECT)
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ID); // our only parameter is the ID our method takes in
			ResultSet rs = ps.executeQuery(); // we create our result set
			rs.next(); // we need to start parsing through it at record number one 
					   //hence we call the next method to position ourselves at that first record
			Customers customer = new Customers(); //we need a customer object to start populating with the info retrieved
			
			//this is to set the values of our customer object fields to those values retrieved by the prepared statement.
			
			customer.setcID(rs.getInt("cID")); 
			customer.setUsername(rs.getString("username"));
			customer.setPassword(rs.getString("password"));
			
			return customer; // the customer object is returned
			
		} catch (SQLException e) { // should an exception be thrown
			e.printStackTrace();
			return null;
		}	
	}
	
	// Another read operation
	
	@Override
	public Customers getCustByUsername(String username) {
		try (Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "SELECT * FROM bank_db.CUSTOMERS WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Customers customer = new Customers();
			
			customer.setcID(rs.getInt("cID")); 
			customer.setUsername(rs.getString("username"));
			customer.setPassword(rs.getString("password"));
			
			return customer;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Another Read operation 

	@Override
	public Set<Customers> getAllCustomers() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM bank_db.CUSTOMERS"; // this one retrieves all customer records
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			Set<Customers> allcustomers = new HashSet<Customers>(); // since our method returns a set including all customers we need to create the collection
			
			// we need to iterate through all the table records and throw them into our customer objects set
			// we'll use a while statement for that
			
			while(rs.next()) {
				
				//we'll need a customer object to assign retrieved values to
				// at the same time all the customer objects will go into the set
				Customers customer = new Customers(); 
				customer.setcID(rs.getInt("cID"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
				allcustomers.add(customer); //lastly we add the costumer into the set
			}
			return allcustomers; // we return our set including all customers
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	// UPDATE

	@Override
	public Customers updateCustomer(Customers customer) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			// in this case we'll update a given existing object that has an identity field (cID) already set
			
			String sql = "UPDATE bank_db.CUSTOMERS SET username = ?, password = ? WHERE cID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// since we are updating an object and therefore a record
			// we'll define those new values before executing the prepared statement
			
			ps.setString(1, customer.getUsername());
			ps.setString(2, customer.getPassword());
			ps.setInt(3, customer.getcID());
			
			// now we execute the ps
			
			ps.execute();
			
			return customer;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	// DELETE
	// in this case we're deleting a record (row) given a certain cID

	@Override
	public boolean deleteCustomer(int ID) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "DELETE FROM bank_db.CUSTOMERS WHERE cID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ID);
			int rows = ps.executeUpdate(); // we need to know what number of records are deleted
			
			if(rows>0) { 
				return true; // if more than zero then it returns true
			}else { 
				return false; // otherwise it's false and it means no row (record) was deleted
				} 
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}
}
