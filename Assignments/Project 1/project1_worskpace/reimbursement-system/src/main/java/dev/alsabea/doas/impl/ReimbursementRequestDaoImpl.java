
package dev.alsabea.doas.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.alsabea.connection.ConnectionEstablisher;
import dev.alsabea.doas.ReimbursementRequestDao;
import dev.alsabea.entities.Employee;
import dev.alsabea.entities.ReimbursementRequest;


public class ReimbursementRequestDaoImpl implements ReimbursementRequestDao {

	private static ReimbursementRequestDaoImpl dao;
	
	public static ReimbursementRequestDao getInstance() {
		if (dao == null) {
			dao = new ReimbursementRequestDaoImpl();
			return dao;
		} else {
			return dao;
		}
	}
	
	
	@Override
	public int createInstance(ReimbursementRequest t) {
		final String insertMySql = "insert into reimbursement_system_db.reimbursement_requests  "
				+ " (emp_id , mgr_id ,reimbursement_request , reimbursement_status , reason ) values "
				+ "(?, ?, ?, ?, ?) ";
		Connection con = ConnectionEstablisher.getConnection();
		int createdRecordId = -1;
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(insertMySql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, t.getEmpId());
			ps.setInt(2, t.getMgrId());
			ps.setString(3, t.getReimbursementRequest());
			ps.setString(4, t.getReimbursementStatus());
			ps.setString(5, t.getReason());
			
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			rs.next();
			// get the key generated and assigned to the employee object by the database
			createdRecordId = rs.getInt("rr_id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return the generated key from the database.
		return createdRecordId;
	}
	
	@Override
	public List<ReimbursementRequest> retrieveAllRequestsByEmpId(int key) {
		final String retrieveSql = "SELECT * FROM reimbursement_system_db.reimbursement_requests "
				+ " WHERE emp_id = ?";
		Connection con = ConnectionEstablisher.getConnection();
		ResultSet rs = null;
		List<ReimbursementRequest> recordsList = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(retrieveSql)) {
			ps.setInt(1, key);
			
			rs = ps.executeQuery();
			while (rs.next())
				recordsList.add(extractFromRs(rs));

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (recordsList.size()!=0)
			return recordsList; 
		else
			return null;
	}
	
	@Override
	public List<ReimbursementRequest> retrieveAllRequestsByMgrId(int key) {
		final String retrieveSql = "SELECT * FROM reimbursement_system_db.reimbursement_requests "
				+ " WHERE mgr_id = ?";
		Connection con = ConnectionEstablisher.getConnection();
		ResultSet rs = null;
		List<ReimbursementRequest> recordsList = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(retrieveSql)) {
			ps.setInt(1, key);
			
			rs = ps.executeQuery();
			while (rs.next())
				recordsList.add(extractFromRs(rs));

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (recordsList.size()!=0)
			return recordsList; 
		else
			return null;
	}
	
	@Override
	public ReimbursementRequest retrieveById(int key) {
		final String retrieveSql = "SELECT * FROM "
				+ "reimbursement_system_db.reimbursement_requests WHERE rr_id = ?";
		Connection con = ConnectionEstablisher.getConnection();
		ResultSet rs = null;
		boolean isNotEmpty = true;
		try (PreparedStatement ps = con.prepareStatement(retrieveSql)) {
			ps.setInt(1, key);
			rs = ps.executeQuery();
			isNotEmpty= rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (isNotEmpty)
			return extractFromRs(rs);
		else
			return null;
	}



	@Override
	public boolean update(ReimbursementRequest t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(int key) {
		// TODO Auto-generated method stub
		return false;
	}


	private ReimbursementRequest extractFromRs(ResultSet rs) {
		// (rr_id, emp_id , mgr_id ,reimbursement_request , reimbursement_status , reason )
		ReimbursementRequest rr= new ReimbursementRequest();

		try {
			rr.setRrId(rs.getInt("rr_id"));
			rr.setEmpId(rs.getInt("emp_id"));
			rr.setMgrId(rs.getInt("mgr_id"));
			rr.setReimbursementRequest(rs.getNString("reimbursement_request"));
			rr.setReimbursementStatus(rs.getString("reimbursement_status"));
			rr.setReason(rs.getString("reason"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return rr;
	}



}
