package dev.schneider.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.schneider.entities.Customer;
import dev.schneider.utils.ConnectionUtil;

public class CustomerDAOImpl implements CustomerDAO {
	
	private static CustomerDAOImpl cdao = null;

	private CustomerDAOImpl() {
		
	}
	
	public static CustomerDAO getCustomerDAO() {
		if (cdao == null){
			CustomerDAO cdao = new CustomerDAOImpl();
			return cdao;
		}
		else {
			return cdao;
		}
	}
	
	@Override
	public Customer createCustomer(Customer customer) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO BankDB.customer VALUES(?,?,?)";
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
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	
	@Override
	public Set<Customer> getAllCustomer() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM BankDB.customer";
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
							
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Customer getCustomerByID(int cID) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM BankDB.customer WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cID);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Customer customer = new Customer();
			customer.setcID(rs.getInt("c_id"));
			customer.setUsername(rs.getString("username"));
			customer.setPassword(rs.getString("password"));
			
			return customer;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Customer getCustomerByUsername(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM BankDB.customer WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Customer customer = new Customer();
			customer.setcID(rs.getInt("c_id"));
			customer.setUsername(rs.getString("username"));
			customer.setPassword(rs.getString("password"));
			
			return customer;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE BankDB.customer SET username = ?, password = ? WHERE c_id = ?";
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
	public boolean deleteCustomer(int cID) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM BankDB.customer WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cID);
			int rows = ps.executeUpdate();
			if(rows>0) {
				return true;
			}else {
				return false;
			}				
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
