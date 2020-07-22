package dev.kyle.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.kyle.entities.Customer;
import dev.kyle.utils.ConnectionUtil;

public class CustomerDAOMaria implements CustomerDAO {
	
	private static CustomerDAO cdao = null;
	
	private CustomerDAOMaria() {}
	
	public static CustomerDAO getCustomerDAOMaria() {
		if (cdao == null) {
			cdao = new CustomerDAOMaria();
			return cdao;
		} else {
			return cdao;
		}
	}

	@Override
	public Customer createCustomer(Customer c) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO bankdb.CUSTOMER VALUES(?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,0);
			ps.setString(2, c.getUsername());
			ps.setString(3, c.getPassword());
			ps.execute(/* >>>> NO PARAMETERES <<<< */); 
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("c_id");
			c.setCid(key);
			
			
			//no need for conn.close here, because of the try with resource 
			return c;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Customer getCustomerById(int cid) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bankdb.CUSTOMER WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,cid);
			
			ResultSet rs = ps.executeQuery();
			rs.next();

			Customer c = new Customer();
			c.setCid(rs.getInt("c_id"));
			c.setUsername(rs.getString("username"));
			c.setPassword(rs.getString("password"));
			
			return c;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Customer> getAllCustomers() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bankdb.CUSTOMER";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Set<Customer> customers = new HashSet<Customer>();
			
			while(rs.next()) {
				Customer c = new Customer();
				c.setCid(rs.getInt("c_id"));
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

	@Override
	public Customer updateCustomer(Customer c) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE bankdb.CUSTOMER SET username = ?, password = ? WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getUsername());
			ps.setString(2,c.getPassword());
			ps.setInt(3, c.getCid());
			ps.execute(); 
			
			return c;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteCustomer(int cid) {
		try(Connection conn = ConnectionUtil.getConnection() ) {
			String sql = "DELETE FROM bankdb.CUSTOMER WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cid);
			int rows = ps.executeUpdate();
			if (rows > 0) {
				return true;
			} else {
				return false;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
