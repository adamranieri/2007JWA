package dev.dave.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.dave.entities.Accounts;
import dev.dave.utils.ConnectionUtil;

public class AccountsDAODB implements AccountsDAO{
	
	// We're making this DAO a singleton
	
	private static AccountsDAODB adbdao = null; // static copy of itself
	
	// private constructor
	
	private AccountsDAODB() { 
		
	}
	
	// a public static method responsible for getting us an instance of the class
	// this will ensure an instance of the class is only created once...
	
	public static AccountsDAO getAccountsDAODB() {
		
		if (adbdao == null) 
		{
			adbdao = new AccountsDAODB();
			return adbdao;
		}
		else 
		{
			return adbdao;
		}
	}
	
	// CREATE

	@Override
	public Accounts createAccount(Accounts account) {
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "INSERT INTO bank_db.ACCOUNTS VALUES (?,?,?,?)"; 
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0); // inserting parameters into the prepared statement
			ps.setString(2, account.getAccountname()); // inserting parameters into the prepared statement
			ps.setDouble(3, account.getBalance()); // inserting parameters into the prepared statement
			ps.setInt(4, account.getcID()); // inserting parameters into the prepared statement
			ps.execute(); // executing the prepared statement
			
			ResultSet rs = ps.getGeneratedKeys(); //we create a result set
			rs.next(); // since the result set points to a value that's prior to the first record we call the next method
			int key = rs.getInt("aID"); // when we create our object we always pass in 0 for the aID as the actual aID will be assigned in the DB
										// this is to get that value from the DB
			account.setaID(key); 		// and this is set it to the object
			
			return account; // an account object is returned
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// READ

	@Override
	public Accounts getAccByID(int ID) {
		try (Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "SELECT * FROM bank_db.ACCOUNTS WHERE aID = ?"; //in this case we are querying the DB (SELECT)
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ID); // our only parameter is the ID our method takes in
			ResultSet rs = ps.executeQuery(); // we create our result set
			rs.next(); // we need to start parsing through it at record number one 
					   //hence we call the next method to position ourselves at that first record
			Accounts account = new Accounts(); //we need a customer object to start populating with the info retrieved
			
			//this is to set the values of our account object fields to those values retrieved by the prepared statement
			
			account.setaID(rs.getInt("aID"));
			account.setAccountname(rs.getString("accountname"));
			account.setBalance(rs.getDouble("balance"));
			account.setcID(rs.getInt("cID"));
			
			return account; // account object is returned
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Accounts> getAllAccounts() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM bank_db.ACCOUNTS"; // this one retrieves all account records
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			Set<Accounts> allaccounts = new HashSet<Accounts>(); // since our method returns a set including all accounts we need to create the collection
			
			// we need to iterate through all the table records and throw them into our account objects set
			// we'll use a while statement for that
						
		    while(rs.next()) {
		    	
		    	//we'll need an account object to assign retrieved values to
				// at the same time all the account objects will go into the set
		    	
		    	Accounts account = new Accounts();
		    	account.setaID(rs.getInt("aID"));
		    	account.setAccountname(rs.getString("accountname"));
		    	account.setBalance(rs.getDouble("balance"));
		    	account.setcID(rs.getInt("cID"));
		    	allaccounts.add(account); //lastly we add the account into the set  	
		    }
			return allaccounts; // we return our set including all accounts
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	@Override
	public Set<Accounts> getAllAccountsByCustomer(int cID) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM bank_db.ACCOUNTS WHERE cID = ?"; // this one retrieves all account records from a given customer
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cID); // our only parameter is the cID our method takes in
			ResultSet rs = ps.executeQuery();
			
			Set<Accounts> allcustaccounts = new HashSet<Accounts>(); // since our method returns a set including all accounts we need to create the collection
			
			// we need to iterate through all the table records and throw them into our account objects set
			// we'll use a while statement for that
						
		    while(rs.next()) {
		    	
		    	//we'll need an account object to assign retrieved values to
				// at the same time all the account objects will go into the set
		    	
		    	Accounts account = new Accounts();
		    	account.setaID(rs.getInt("aID"));
		    	account.setAccountname(rs.getString("accountname"));
		    	account.setBalance(rs.getDouble("balance"));
		    	account.setcID(rs.getInt("cID"));
		    	allcustaccounts.add(account); //lastly we add the account into the set  	
		    }
			return allcustaccounts; // we return our set including all accounts
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	// UPDATE

	@Override
	public Accounts updateAccount(Accounts account) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			// in this case we'll update a given existing object that has an identity field (cID) already set
			
			String sql = "UPDATE bank_db.ACCOUNTS SET accountname = ?, balance = ?, cID = ? WHERE aID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// since we are updating an object and therefore a record
			// we'll define those new values before executing the prepared statement
			
			ps.setString(1, account.getAccountname());
			ps.setDouble(2, account.getBalance());
			ps.setInt(3, account.getcID());
			ps.setInt(4, account.getaID());
			
			// now we execute the ps
			
			ps.execute();
			
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// DELETE
	
	// in this case we're deleting a record (row) given a certain

	@Override
	public boolean deleteAccount(int ID) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "DELETE FROM bank_db.ACCOUNTS WHERE aID = ?";
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

	@Override
	public boolean deleteAllCustAccounts(int cID) {
		
try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "DELETE FROM bank_db.ACCOUNTS WHERE cID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cID);
			
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
