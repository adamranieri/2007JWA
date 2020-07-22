package dev.lee.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.lee.entities.Customer;
import dev.lee.utils.ConnectionUtil;

public class CustomerDAOMaria implements CustomerDAO{
	
	private static CustomerDAO cdao = null;
	
	private CustomerDAOMaria() {}
	
	public static CustomerDAO getCustomerDAOMaria() {
		if(cdao == null) {
			cdao = new CustomerDAOMaria();
		}
		return cdao;
	}
	  
	@Override
	public Customer createCustomer(Customer customer) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO bank_db.customer VALUES (?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setString(2, customer.getUsername());
			ps.setString(3, customer.getPassword());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("c_id");
			customer.setcID(key);
			
			return customer;			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Customer getCustomerById(int cId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.customer WHERE c_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Customer customer = new Customer();		
			customer.setcID(rs.getInt("c_id"));
			customer.setUsername(rs.getString("username"));
			customer.setPassword(rs.getString("password"));
			return customer;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.customer WHERE username = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			rs.next(); 
			
			Customer customer = new Customer();
			customer.setcID(rs.getInt("c_id"));
			customer.setUsername(rs.getString("username"));
			customer.setPassword(rs.getString("password"));
			
			return customer;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Customer> getAllCustomers() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.customer";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			Set<Customer> customers = new HashSet<Customer>();
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setcID(rs.getInt("c_id"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
				customers.add(customer);
			}
			return customers;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE bank_db.customer SET username = ?, password = ? WHERE c_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getUsername());
			ps.setString(2, customer.getPassword());
			ps.setInt(3, customer.getcID());
			
			ps.execute();
			
			return customer;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteCustomer(int cId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM bank_db.customer WHERE c_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cId);
			
			int rows = ps.executeUpdate();
			return rows != 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


}
