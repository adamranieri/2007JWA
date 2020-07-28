package dev.alsabea.doas.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.alsabea.connection.ConnectionEstablisher;
import dev.alsabea.doas.EmployeeManagerDao;
import dev.alsabea.entities.Employee_Manager;
import dev.alsabea.exceptions.DaoException;

public class EmployeeManagerDaoImpl implements EmployeeManagerDao {

	private static EmployeeManagerDaoImpl dao = null;

	private EmployeeManagerDaoImpl() {

	}

	public static EmployeeManagerDao getInstance() {
		if (dao == null) 
			dao = new EmployeeManagerDaoImpl();
		return dao;

	}

	@Override
	public int createInstance(Employee_Manager t) {
		final String insertMySql = "insert into reimbursement_system_db.emp_mgr "
				+ " (first_name , last_name , username , password , emp_role, mgr_id) values " 
				+ " (?, ?, ?, ?, ?, ?) ";
		Connection con = ConnectionEstablisher.getConnection();
		int createdRecordId = -1;
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(insertMySql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, t.getFirstName());
			ps.setString(2, t.getLastName());
			ps.setString(3, t.getUsername());
			ps.setString(4, t.getPassword());
			ps.setString(5, t.getEmpRole());
			ps.setInt(6, t.getMgrId());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			rs.next();
			// get the key generated and assigned to the employee object by the database
			createdRecordId = rs.getInt("emp_id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return the generated key from the database.
		return createdRecordId;
	}

	@Override
	public List<Employee_Manager> retrieveAll() {
		final String retrieveSql = "SELECT * FROM reimbursement_system_db.emp_mgr ";
		Connection con = ConnectionEstablisher.getConnection();
		ResultSet rs = null;
		List<Employee_Manager> recordsList = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(retrieveSql)) {
			rs = ps.executeQuery();
			while (rs.next())
				recordsList.add(extractFromRs(rs));

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (recordsList.size() != 0)
			return recordsList;
		else
			return null;
	}

	@Override
	public Employee_Manager retrieveById(int key) {
		final String retrieveSql = "SELECT * FROM reimbursement_system_db.emp_mgr WHERE emp_id = ?";
		Connection con = ConnectionEstablisher.getConnection();
		ResultSet rs = null;
		boolean isNotEmpty = true;
		try (PreparedStatement ps = con.prepareStatement(retrieveSql)) {
			ps.setInt(1, key);
			rs = ps.executeQuery();
			isNotEmpty = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (isNotEmpty)
			return extractFromRs(rs);
		else
			return null;
	}

	@Override
	public boolean update(Employee_Manager t) {
		final String updateSql = "UPDATE reimbursement_system_db.emp_mgr "
				+ " SET first_name = ?, last_name = ?, username= ? , password = ? , emp_role = ?, "
				+ " mgr_id=?  WHERE emp_id = ?;";

		Connection con = ConnectionEstablisher.getConnection();
		int numOfUpdatedRecords = -4;
		try (PreparedStatement ps = con.prepareStatement(updateSql)) {
			ps.setString(1, t.getFirstName());
			ps.setString(2, t.getLastName());
			ps.setString(3, t.getUsername());
			ps.setString(4, t.getPassword());
			ps.setString(5, t.getEmpRole());
			ps.setInt(6, t.getMgrId());
			ps.setInt(7, t.getEmpId());

			numOfUpdatedRecords = ps.executeUpdate();
			if (numOfUpdatedRecords > 1)
				throw new DaoException("sql query did not update the expected number of rows");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (numOfUpdatedRecords == 1)
			return true;
		else // if (numOfUpdatedRecords == 0) no element have been updated (id does not refer
				// to any update object)
			return false;
	}

	@Override
	public boolean deleteById(int key) {
		final String deleteSql = "DELETE FROM reimbursement_system_db.emp_mgr WHERE emp_id = ?";
		int numOfDeletedRecords = -4;
		Connection con = ConnectionEstablisher.getConnection();
		try (PreparedStatement ps = con.prepareStatement(deleteSql)) {
			ps.setInt(1, key);

			numOfDeletedRecords = ps.executeUpdate();
			// if the delete function deletes more than 1 object, there must be an error,
			// because emp_id is the primary_key, each record has a unique primary key
			// so there must be some problem with the database or the DAO if it deletes more
			// than one.
			if (numOfDeletedRecords > 1)
				throw new DaoException("sql query did not update the expected number of rows");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (numOfDeletedRecords == 1)
			return true;
		else 
			return false;
	}

	private Employee_Manager extractFromRs(ResultSet rs) {
		Employee_Manager emp = new Employee_Manager();
		// ( emp_id, first_name , last_name , username , password , emp_role, mgr_id)

		try {
			emp.setEmpId(rs.getInt("emp_id"));
			emp.setFirstName(rs.getString("first_name"));
			emp.setLastName(rs.getString("last_name"));
			emp.setUsername(rs.getString("username"));
			emp.setPassword(rs.getString("password"));
			emp.setEmpRole(rs.getString("emp_role"));
			emp.setMgrId(rs.getInt("mgr_id"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emp;
	}

}
