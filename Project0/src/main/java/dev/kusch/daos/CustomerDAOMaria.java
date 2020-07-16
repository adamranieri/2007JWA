package dev.kusch.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.kusch.entities.Account;
import dev.kusch.entities.Customer;
import dev.kusch.utils.ConnectionUtil;

public class CustomerDAOMaria implements CustomerDAO {

public static CustomerDAO sdao = null;
	
	private CustomerDAOMaria() {
		
	}
	
	public static CustomerDAO getCustomerDAOMaria() {
		if (sdao == null) {
			sdao = new CustomerDAOMaria();
		}
		return sdao;
	}
	
	// TODO: IMPLEMENT THIS
	@Override
	public Customer createCustomer(Customer customer) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			// takes the Customer object and creates a new one in the table with the basic fields
			String sql = "INSERT INTO bankAPI_db.customer VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setString(2, customer.getUsername());
			ps.setString(3, customer.getPassword());
			
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("c_id");
			customer.setcId(key);
			
			
			// builds the customer's customer list based on the customers credited to them in the customers table
			String sqlAcc = "SELECT * FROM bankAPI_db.customer WHERE c_id = ?";
			PreparedStatement psAcc = conn.prepareStatement(sqlAcc, Statement.RETURN_GENERATED_KEYS);
			psAcc.setInt(1, customer.getcId());
			ResultSet rsAcc = psAcc.executeQuery();
			
			Set<Account> accounts = new HashSet<Account>();
			while(rsAcc.next()) {
				Account account = new Account();
				account.setaId(rsAcc.getInt("a_id"));
				account.setName(rsAcc.getString("name"));
				account.setBalance(rsAcc.getInt("balance"));
				
				accounts.add(account);
			}
			customer.setAccounts(accounts);
			
			
			return customer;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Customer getCustomerById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bankAPI_db.customer WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Customer customer = new Customer();
			customer.setcId(rs.getInt("c_id"));
			customer.setUsername(rs.getString("username"));
			customer.setPassword(rs.getString("password"));
			
			return customer;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Customer> getAllCustomers() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bankAPI_db.customer";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			Set<Customer> customers = new HashSet<Customer>();
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setcId(rs.getInt("c_id"));
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
	public Customer updateCustomer(Customer customer) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE bankAPI_db.customer SET username = ?, password = ? WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getUsername());
			ps.setString(2, customer.getPassword());
			ps.setInt(3,  customer.getcId());
			
			ps.execute();
			return customer;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteCustomer(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM bankAPI_db.customer WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			
			return ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
