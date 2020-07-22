package dev.nauman.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.nauma.utils.ConnectionUtil;
import dev.nauman.entities.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	private static CustomerDAOImpl cdao = null;
	private static AccountDAOImpl adao = AccountDAOImpl.getAccountDAOImpl();

	private CustomerDAOImpl() {
	}

	public static CustomerDAOImpl getCustomerDAOImpl() {
		if(cdao==null) {
			cdao = new CustomerDAOImpl();
		}

		return cdao;
	}
	@Override
	public Customer createCustomer(Customer customer) {

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO project_zero_db.customer VALUES(?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, customer.getcId());
			ps.setString(2, customer.getUsername());
			ps.setString(3, customer.getPassword());
			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			customer.setcId(rs.getInt("c_id"));

			return customer;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Customer getCustomerById(int cId) {

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM project_zero_db.customer WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cId);
			ResultSet rs = ps.executeQuery();
			rs.next();

			Customer customer = new Customer();
			customer.setcId(rs.getInt("c_id"));
			customer.setUsername(rs.getString("username"));
			customer.setPassword(rs.getString("password"));
			customer.setAccounts(adao.getAccountsByCId(cId));

			return customer;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Set<Customer> getAllCustomers() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM project_zero_db.customer";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			Set<Customer> customers = new HashSet<Customer>();
			
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setcId(rs.getInt("c_id"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
				customer.setAccounts(adao.getAccountsByCId(rs.getInt("c_id")));
				customers.add(customer);
			}
			
			return customers;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Customer updateCustomer(Customer customer) {

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE project_zero_db.customer SET username = ?, password = ? WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, customer.getUsername());
			ps.setString(2, customer.getPassword());
			ps.setInt(3, customer.getcId());
			ps.execute();

			return customer;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean deleteCustomerById(int cId) {

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM project_zero_db.customer WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cId);

			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected>0) {
				return true;
			}return false;

		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}