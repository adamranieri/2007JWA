package dev.alsabea.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dev.alsabea.connectionUtils.ConnectionUtils;
import dev.alsabea.daos.AccountDao;
import dev.alsabea.daos.CustomerDao;
import dev.alsabea.entities.Account;
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
	public boolean create(Customer t) {
		final String insertMySql= "insert into proj_0_db.customer (username, password) values (?, ?) ";
		Connection con= ConnectionUtils.getConnection();
		
		try (PreparedStatement ps= con.prepareStatement(insertMySql)){
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			if (ps.executeUpdate()!= 1)
				throw new DaoException("sql query did not update the expected number of rows");
		} catch (Exception e){
			e.printStackTrace();
		}
		return true;
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
		return true;
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

		return CustomerDao.extractFromRs(rs);
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

	@Override
	public List<Account> getAccounts(int id) {
		final String sqlStmt= "SELECT * FROM proj_0_db.account WHERE customer_id = ?";
		
		Connection con= ConnectionUtils.getConnection();
		ResultSet rs= null;
		List<Account> accts= new ArrayList<>();
		
		try (PreparedStatement ps= con.prepareStatement(sqlStmt)){
			ps.setInt(1, id);
			rs= ps.executeQuery();
			
			while (rs.next()) {
				accts.add(AccountDao.extractFromRs(rs));
			}

			
		} catch (Exception e){
			e.printStackTrace();
		}

		return accts;
	}




	@Override
	public int getIdByUsernameAndPassword(String username, String password) {
		final String sqlStatement ="SELECT * FROM proj_0_db.customer WHERE "
				+ "username = ? AND password = ?";
		Connection con= ConnectionUtils.getConnection();
		ResultSet rs= null;
		try (PreparedStatement ps= con.prepareStatement(sqlStatement)){
			ps.setString(1, username);
			ps.setString(2, password);
			rs= ps.executeQuery();
			rs.next();
		} catch (Exception e){
			e.printStackTrace();
		}

		return CustomerDao.extractFromRs(rs).getCustomerId();
	}
	

}
