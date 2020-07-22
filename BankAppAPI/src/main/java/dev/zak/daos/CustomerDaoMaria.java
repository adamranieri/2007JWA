package dev.zak.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.zak.entities.Customer;
import dev.zak.utilities.ConnectionUtility;

public class CustomerDaoMaria implements CustomerDaoInterface{


	private static CustomerDaoInterface customerDao = null;
	private CustomerDaoMaria() {
		super();
	}
	
	public static CustomerDaoInterface getCustomerDaoMaria() {
		if(customerDao == null)
			customerDao = new CustomerDaoMaria();
		return customerDao;
	}
	
	private Set<Customer> getFieldsFromDB(PreparedStatement ps) {

		ResultSet rs;
		Set<Customer> customers = new HashSet<Customer>();
		Customer c;
		try {
			rs = ps.executeQuery();
			while(rs.next()) {
				c = new Customer();
				c.setcId(rs.getInt("customer_id"));
				c.setUsername(rs.getString("username"));
				c.setPassword(rs.getString("password"));
				customers.add(c);
			}
			return customers;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Customer createCustomer(Customer c) {
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
			return null;
		}
	}

	public Customer getCustomerById(int id) {
		try(Connection conn = ConnectionUtility.getConnection()){
			Set<Customer> customers = new HashSet<Customer>();
			String sql = "SELECT * FROM bank_db.customer WHERE customer_id = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			customers = this.getFieldsFromDB(ps);
			return customers.isEmpty() ? null : customers.iterator().next();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Set<Customer> getAllCustomers() {
		Set<Customer> customers = new HashSet<Customer>();
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM bank_db.customer";

			PreparedStatement ps = conn.prepareStatement(sql);
			customers = this.getFieldsFromDB(ps);
			return customers;
							
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
			if(updatedRecords>0)
				return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Set<Customer> getCustomerByUserName(String userName) {
		Set<Customer> customers = new HashSet<Customer>();
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM  bank_db.customer WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);

			customers = this.getFieldsFromDB(ps);
			return customers.isEmpty() ? null : customers;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
