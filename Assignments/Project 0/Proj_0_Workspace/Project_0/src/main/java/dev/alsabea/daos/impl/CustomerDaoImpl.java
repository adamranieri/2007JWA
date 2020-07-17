package dev.alsabea.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dev.alsabea.connectionUtils.ConnectionUtils;
import dev.alsabea.daos.CustomerDao;
import dev.alsabea.entities.Customer;
import dev.alsabea.exceptions.DaoException;

public class CustomerDaoImpl implements CustomerDao {
	
	
private static CustomerDaoImpl dao = null;
	
	private CustomerDaoImpl() {
		
	};
	
	public static CustomerDao getCustomerDao() {
		
		if(dao == null) {
			dao = new CustomerDaoImpl();
			return dao;
		}else {
			return dao;
		}
		
	}

	@Override
	public int create(Customer t) {
		final String insertMySql= "insert into proj_0_db.customer (username, password) values (?, ?) ";
		Connection con= ConnectionUtils.getConnection();
		int createdRecordId=-1;
		ResultSet rs=null;
		try (PreparedStatement ps= con.prepareStatement(insertMySql, Statement.RETURN_GENERATED_KEYS)){
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			if (ps.executeUpdate()!= 1)
				throw new DaoException("sql query did not update the expected number of rows");
			rs= ps.getGeneratedKeys();
			rs.next();
			createdRecordId = rs.getInt("customer_id");
		} catch (Exception e){
			e.printStackTrace();
		}
		return createdRecordId;
	}
	
	@Override
	public boolean delete(int id) {
		final String deleteSql= "DELETE FROM proj_0_db.customer WHERE customer_id = ?";
		
		Connection con= ConnectionUtils.getConnection();
		try (PreparedStatement ps= con.prepareStatement(deleteSql)){
			ps.setInt(1,id);
			if (ps.executeUpdate()!= 1)
				throw new DaoException("sql query did not update the expected number of rows");
		} catch (Exception e){
			e.printStackTrace();
		}
		return true; //if it runs successfully, then return the id of the thing deleted
		//you cannot do return generated keys, because there are no keys that are generated when deleting.
	}

	@Override
	public Customer retrieveById(int id) {
		final String retrieveSql= "SELECT * FROM proj_0_db.customer WHERE customer_id = ?";
		Connection con= ConnectionUtils.getConnection();
		ResultSet rs= null;
		try (PreparedStatement ps= con.prepareStatement(retrieveSql)){
			ps.setInt(1, id);
			rs= ps.executeQuery();
			rs.next();
		} catch (Exception e){
			e.printStackTrace();
		}

		return extractFromRs(rs);
	}

	

	
	
	@Override
	public boolean update(Customer t) {
		final String updateSql="UPDATE proj_0_db.customer "
				+ "SET username= ? , password = ? WHERE customer_id = ?;";

		Connection con= ConnectionUtils.getConnection();

		try (PreparedStatement ps= con.prepareStatement(updateSql)){
			ps.setString(1,t.getUsername());
			ps.setString(2, t.getPassword());
			ps.setInt(3, t.getCustomerId());
			if (ps.executeUpdate()!= 1)
				throw new DaoException("sql query did not update the expected number of rows");
		} catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
	
	private Customer extractFromRs(ResultSet rs) {
		Customer c = new Customer();
		
		try {
			c.setCustomerId(rs.getInt("customer_id"));
			c.setUsername(rs.getString("username"));
			c.setPassword(rs.getString("password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return c;
	}

}
