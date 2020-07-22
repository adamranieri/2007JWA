package dev.winder.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.winder.entities.Customer;
import dev.winder.utils.ConnectionSQLUtil;

//add implemented DAO methods from the CustomerDAO interface
public class CustomerDAOMaria implements CustomerDAO {
	
	private static BankAccountDAO bDao= BankAccountDAOMaria.getBankAccountDAOMaria();

	//create a static object with CustomerDAO interface reference
	private static CustomerDAO cDao = null;
	
	//create a private constructor for the purpose of this DAO being a singleton
	private CustomerDAOMaria() {
		
	}
	
	//create public static method that gives us access to the constructor
	public static CustomerDAO getCustomerDAOMaria() {
		
		if(cDao == null) {
			cDao = new CustomerDAOMaria();
		}//end if
		return cDao;
	}//end CustomerDAO

	//save Customer method
	
	//CREATE OPERATION
	@Override
	public Customer createCustomer(Customer customer) {
		
		//
		try(Connection BankDBConn = ConnectionSQLUtil.getConnection()){
			
			String sqlCommand = "INSERT INTO BankDB.customers VALUES (?,?,?)";
			//precompiled SQL statement that takes in a command and executes by .execute() for creating a whole new customer
			PreparedStatement ps = BankDBConn.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS);
			//set the first column to zero since SQL will auto-increment it
			ps.setInt(1, 0);
			ps.setString(2, customer.getCustomerName());
			ps.setString(3, customer.getCustomerPin());
			//execute the statement and send it to the serverhost!!
			ps.execute();
			//get the virtual table so we can return values to our J-Unit Test Case
			ResultSet rs = ps.getGeneratedKeys();
			//it will initial point before the first row in our resultset object
			rs.next();
			//retrieve customer id from result set object
			int key = rs.getInt("c_id");
			customer.setCustomerId(key);
			
			//return back to our J-Unit Test Case.
			return customer;
			
		//catch the SQLException
		}catch(SQLException ex) {
			
			ex.printStackTrace();
			return null;
		}
		
	}

	//READ OPERATION
	@Override
	public Set<Customer> getAllCustomers() {
		try(Connection BankDBConn = ConnectionSQLUtil.getConnection()){
			
			String sqlCommand = "SELECT * FROM BankDB.customers";
			
			PreparedStatement ps = BankDBConn.prepareStatement(sqlCommand);
			
			ResultSet rs = ps.executeQuery();
			
			//return the set of customers, we are only concerned about the size of the table
			Set<Customer>customers = new HashSet<Customer>();
			
			//while there is another row in our result set virtual table..
			while(rs.next()) {
				
				Customer customerInSet = new Customer();
				
				customerInSet.setCustomerId(rs.getInt("c_id"));
				customerInSet.setCustomerName(rs.getString("cName"));
				customerInSet.setCustomerPin(rs.getString("cPin"));
				customers.add(customerInSet);
				
			}
			
			return customers;
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;

		}
	
	}

	//READ OPERATION
	@Override
	public Customer getCustomerByCustomerId(int id) {
		try (Connection BankDBConn = ConnectionSQLUtil.getConnection()){
			
			String sqlCommand = "SELECT * FROM BankDB.customers WHERE c_id = ? ";
			
			PreparedStatement ps = BankDBConn.prepareStatement(sqlCommand);
			
			//set the int to one in the prepare statement in order to execute the query of the object being reutrned back
			ps.setInt(1,id);
			
			//execute the query the precompliled statement obj makes to the database to return the SQL virtual table
			//as a result set object
			ResultSet rs = ps.executeQuery();
			
			//initially points before first row
			rs.next();
			
			//create our customer obj and assign it the 
			Customer customer = new Customer();
			
			//call upon the attributes from the result set virtual table and assign it to the customer
			customer.setCustomerId(rs.getInt("c_id"));
			customer.setCustomerName(rs.getString("cName"));
			customer.setCustomerPin(rs.getString("cPin"));
			
			return customer;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		
		try(Connection BankDBConn = ConnectionSQLUtil.getConnection()){
			
			String sqlStatement = "SELECT * FROM BankDB.customers WHERE cName = ?";
			
			PreparedStatement ps = BankDBConn.prepareStatement(sqlStatement);
			
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			Customer customer = new Customer();
			
			customer.setCustomerName(rs.getString("cName"));
			customer.setCustomerId(rs.getInt("c_id"));
			customer.setCustomerPin(rs.getString("cPin"));
			
			return customer;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}//end get customer by username

	//CREATE OPERATION
	@Override
	public Customer updateCustomer(Customer customer) {
		
		try(Connection BankDBConn = ConnectionSQLUtil.getConnection()){
			
			
			//c_id cannot be changed since the SQL WHERE CLAUSE GETS THE RECORD,
			String sqlCommand = "UPDATE BankDB.customers SET cName = ?, cPin = ? WHERE c_id = ?";
			
			PreparedStatement ps = BankDBConn.prepareStatement(sqlCommand);
			
			
			//name or pin will be changed here, must be executed in the order of ? format specifiers
			ps.setString(1, customer.getCustomerName());
			ps.setString(2, customer.getCustomerPin());
			ps.setInt(3,customer.getCustomerId());

			
			ps.execute();
				
			return customer;
					
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;	
		}
		
		
	}

	public boolean deleteCustomerById(int id) {
		
		try(Connection BankDBConn = ConnectionSQLUtil.getConnection()){
			
			String sqlCommand = "DELETE FROM BankDB.customers WHERE c_id = ?";
			
			PreparedStatement ps = BankDBConn.prepareStatement(sqlCommand);
			ps.setInt(1, id);
			
			int rows = ps.executeUpdate();
			

			//check to make sure there is at least one row in the result set Virtual table
			if(rows>0) {
				return true;
			}else {
				return false;
			}
			
		}catch(SQLException ex) {
			
			ex.printStackTrace();
			return false;
		}
	}
}
