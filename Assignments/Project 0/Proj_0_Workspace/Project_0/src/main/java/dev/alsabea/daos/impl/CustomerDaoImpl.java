package dev.alsabea.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.alsabea.connectionUtils.ConnectionUtils;
import dev.alsabea.daos.CustomerDao;
import dev.alsabea.entities.Customer;
import dev.alsabea.exceptions.DaoException;

public class CustomerDaoImpl implements CustomerDao {

	private static CustomerDaoImpl dao = null;

	private CustomerDaoImpl() {

	};

	public static CustomerDao getCustomerDao() {

		if (dao == null) {
			dao = new CustomerDaoImpl();
			return dao;
		} else {
			return dao;
		}

	}

	@Override
	public int create(Customer t) {
		final String insertMySql = "insert into proj_0_db.customer (username, password) values (?, ?) ";
		Connection con = ConnectionUtils.getConnection();
		int createdRecordId = -1;
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(insertMySql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			rs.next();
			// get the key generated and assigned to the customer object by the database
			createdRecordId = rs.getInt("customer_id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return the key.
		return createdRecordId;
	}

	@Override
	public boolean delete(int id) {
		final String deleteSql = "DELETE FROM proj_0_db.customer WHERE customer_id = ?";
		int numOfDeletedRecords = -4;
		Connection con = ConnectionUtils.getConnection();
		try (PreparedStatement ps = con.prepareStatement(deleteSql)) {
			ps.setInt(1, id);

			numOfDeletedRecords = ps.executeUpdate();
			// if the delete function deletes more than 1 object, there must be an error,
			// because customer_id is the primary_key, each record has a unique primary key
			// so there must be some problem with the database or the DAO if it deletes more than one.
			if (numOfDeletedRecords > 1)
				throw new DaoException("sql query did not update the expected number of rows");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (numOfDeletedRecords == 1)
			return true;
		else // if (numOfDeletedRecords == 0) no element have been deleted (id does not refer
				// to any customer object)
			return false;
	}

	@Override
	public List<Customer> retrieveAll() {
		final String retrieveSql = "SELECT * FROM proj_0_db.customer";
		Connection con = ConnectionUtils.getConnection();
		ResultSet rs = null;
		List<Customer> recordsList = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(retrieveSql)) {
			rs = ps.executeQuery();
			while (rs.next())
				recordsList.add(extractFromRs(rs));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return recordsList;
	}

	@Override
	public Customer retrieveById(int id) {
		final String retrieveSql = "SELECT * FROM proj_0_db.customer WHERE customer_id = ?";
		Connection con = ConnectionUtils.getConnection();
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(retrieveSql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return extractFromRs(rs);
	}

	/**
	 * username is not unique, for this reason we return a list.
	 */
	@Override
	public List<Customer> retrieveByUsername(String name) {
		final String retrieveSql = "SELECT * FROM proj_0_db.customer WHERE username = ?";
		Connection con = ConnectionUtils.getConnection();
		ResultSet rs = null;
		List<Customer> recordsList = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(retrieveSql)) {
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next())
				recordsList.add(extractFromRs(rs));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return recordsList;
	}

	@Override
	public boolean update(Customer t) {
		final String updateSql = "UPDATE proj_0_db.customer " + "SET username= ? , password = ? WHERE customer_id = ?;";

		Connection con = ConnectionUtils.getConnection();
		int numOfUpdatedRecords= -4;
		try (PreparedStatement ps = con.prepareStatement(updateSql)) {
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.setInt(3, t.getCustomerId());
			numOfUpdatedRecords = ps.executeUpdate();
			if (numOfUpdatedRecords > 1)
				throw new DaoException("sql query did not update the expected number of rows");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (numOfUpdatedRecords == 1)
			return true;
		else // if (numOfDeletedRecords == 0) no element have been deleted (id does not refer
				// to any customer object)
			return false;
	}

	/**
	 *  Takes a result set, extract the customer information and 
	 *  assign them to a customer object then returns this customer.
	 *  
	 * @param rs
	 * @return Customer
	 */
	private Customer extractFromRs(ResultSet rs) {
		Customer c = new Customer();

		try {
			c.setCustomerId(rs.getInt("customer_id"));
			c.setUsername(rs.getString("username"));
			c.setPassword(rs.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
	}

}