package dev.zak.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dev.zak.entities.Account;
import dev.zak.entities.Customer;
import dev.zak.utilities.ConnectionUtility;

public class CustomerDaoMariaOLD implements CustomerDaoInterface{


	private static CustomerDaoInterface customerDao = null;
	private CustomerDaoMariaOLD() {
		super();
	}
	
	public static CustomerDaoInterface getCustomerDaoMaria() {
		if(customerDao == null)
			customerDao = new CustomerDaoMariaOLD();
		return customerDao;
	}
	
	private Set<Customer> getFildsFromDB(PreparedStatement ps) {
		ResultSet rs;
		Map<Integer, Customer> customers = new HashMap<Integer, Customer>();
		Customer c = new Customer();
		Account a = new Account();
		Set<Account> accounts = new HashSet<Account>();
		
		try {
			rs = ps.executeQuery();
			while(rs.next()) {
				accounts.clear();
				
				c.setcId(rs.getInt("customer_id"));
				c.setUsername(rs.getString("username"));
				c.setPassword(rs.getString("password"));
				
				a.setaId(rs.getInt("account_id"));
				a.setcId(rs.getInt("a.customer_id"));
				a.setAccountName(rs.getString("account_name"));
				a.setBalance(rs.getFloat("balance"));
				
				if(customers.containsKey(c.getcId())) {
					accounts = customers.get(c.getcId()).getAccounts();
				}
				
				if(a.getaId() != 0)
					accounts.add(a);
				c.setAccounts(accounts);
				customers.put(c.getcId(), c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Set<Customer> customersSet= new HashSet<Customer>(customers.values());
		return customersSet;
	}
	
	public Customer createCustomer(Customer c) {
		//System.out.println(c.getcId()+", "+c.getUsername()+", "+c.getPassword());
		try (Connection con = ConnectionUtility.getConnection()){
			String sql = "INSERT INTO bank_db.customer (customer_id,username,password) VALUES (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, c.getcId());
			ps.setString(2, c.getUsername());
			ps.setString(3, c.getPassword());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("customer_id");
			c.setcId(key);
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Customer getCustomerById(int id) {

		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM bank_db.customer c LEFT JOIN bank_db.account a ON c.customer_id=a.customer_id WHERE c.customer_id = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			/*
			 * ResultSet rs = ps.executeQuery(); rs.next();
			 * 
			 * Customer c = new Customer(); c.setcId(rs.getInt("customer_id"));
			 * c.setUsername(rs.getString("username"));
			 * c.setPassword(rs.getString("password"));
			 * 
			 * return c;
			 */
			return this.getFildsFromDB(ps).isEmpty() ? null : this.getFildsFromDB(ps).iterator().next();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Set<Customer> getAllCustomers() {
//		System.out.println("--------------");
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM bank_db.customer c LEFT JOIN bank_db.account a ON c.customer_id=a.customer_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			return this.getFildsFromDB(ps).isEmpty() ? null : this.getFildsFromDB(ps);
							
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Customer updateCustomer(Customer c) {
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "UPDATE bank_db.customer SET username = ?, password = ? WHERE customer_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getUsername());
			ps.setString(2, c.getPassword());
			ps.setInt(3, c.getcId());
			int updatedRecords = ps.executeUpdate();
			// System.out.println("updatedRecords = "+updatedRecords);
			if(updatedRecords>0)
				return c;
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteCustomer(int id) {
		// the ON DELETE CASCADE is specified when creating the table
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "DELETE FROM bank_db.customer WHERE customer_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);;
			int updatedRecords = ps.executeUpdate();
			// System.out.println("updatedRecords = "+updatedRecords);
			if(updatedRecords>0)
				return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Set<Customer> getCustomerByUserName(String userName) {

		//System.out.println("username" + userName);
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM  bank_db.customer c LEFT JOIN bank_db.account a ON c.customer_id = a.customer_id WHERE c.username = ?";
			//System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			
			return this.getFildsFromDB(ps).isEmpty() ? null : this.getFildsFromDB(ps);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
