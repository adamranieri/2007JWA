package dev.edwin.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.edwin.entities.Customer;
import dev.edwin.utils.ConnectionUtil;

public class CustomerDAOImp implements CustomerDAO 
{

	private static CustomerDAO cDao = null;
	
	private CustomerDAOImp()
	{}
	
	public static CustomerDAO getCustomerDAO()
	{
		if(cDao == null)
		{
			cDao = new CustomerDAOImp();
			return cDao;
		}
		else
			return null;
	}
	
//	CRUD Operations
	public Customer createCustomer(Customer customer) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO project0_db.CUSTOMER VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setString(2, customer.getUsername());
			ps.setString(3, customer.getPassword());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("cId");
			customer.setcId(key);
			
			return customer;			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Customer getCustomerById(int cId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM project0_db.CUSTOMER WHERE CID =(?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cId);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Customer customer = new Customer();
			
//			customer.setAccounts(null);
			customer.setcId(rs.getInt("CID"));
			customer.setPassword(rs.getString("PASSWORD"));
			customer.setUsername(rs.getString("USERNAME"));
			
			return customer;			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Customer> getCustomers() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM project0_db.CUSTOMER";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Customer customer = null;
			List<Customer> customers = new ArrayList<Customer>();
		
			while(rs.next())
			{
				customer = new Customer();
//				TODO: Need to update the set accounts to pull from accounts DAO
//				customer.setAccounts(null);
				customer.setcId(rs.getInt("CID"));
				customer.setPassword(rs.getString("PASSWORD"));
				customer.setUsername(rs.getString("USERNAME"));
				
				customers.add(customer);
			}
			

			return customers;			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public Customer updateCustomer(Customer customer) {

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE project0_db.CUSTOMER SET USERNAME=?,PASSWORD=? WHERE CUSTOMER.CID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,customer.getUsername());
			ps.setString(2,customer.getPassword());
			ps.setInt(3, customer.getcId());
			
			
			
			if(ps.executeUpdate() > 0 )
			{
				return customer;
			}
			

			return null;			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	public boolean deleteCustomer(int cId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM project0_db.CUSTOMER WHERE CID = ?";
			String sql2 = "DELETE FROM project0_db.ACCOUNT WHERE CID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps.setInt(1, cId);
			ps2.setInt(1, cId);
			
			
			if(ps2.executeUpdate() > 0 && ps.executeUpdate() > 0 )
			{
				return true;
			}
			

			return false;			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Customer> getCustomerByUsername(String username) 
	{
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "SELECT * FROM project0_db.CUSTOMER WHERE USERNAME LIKE ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+ username + "%");
			
			ResultSet rs = ps.executeQuery();

			
			List<Customer> customers = new ArrayList<Customer>();
			Customer customer;

			while(rs.next())
			{
				customer = new Customer();
				
				customer.setcId(rs.getInt("CID"));
				customer.setPassword(rs.getString("PASSWORD"));
				customer.setUsername(rs.getString("USERNAME"));
				
				customers.add(customer);
			}
			
			return customers;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		
	}



}
