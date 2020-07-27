package dev.alsabea.doas;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.alsabea.connection.ConnectionEstablisher;
import dev.alsabea.doas.impl.ReimbursementRequestDaoImpl;
import dev.alsabea.entities.ReimbursementRequest;

@TestMethodOrder(OrderAnnotation.class)
class TestReimbursementRequestDao {

	private static ReimbursementRequestDao  rrDao = ReimbursementRequestDaoImpl.getInstance();
	
	
	@Test
	final void testCreateInstance() {
		ReimbursementRequest rr = new ReimbursementRequest();
		//(rr_id, emp_id , mgr_id ,reimbursement_request , reimbursement_status , reason )
		
		rr.setEmpId(5);
		rr.setMgrId(1);
		rr.setReimbursementRequest("testReimbursementRequest 5000");
		rr.setReimbursementStatus("testStatus");
		rr.setReason("testReason");
		
		
		int generatedId= rrDao.createInstance(rr);
		
		Assertions.assertNotEquals(-1, generatedId);
	}
	
	@Test 
	final void testRetrieveAllByEmpId() {
		List<ReimbursementRequest> rrs = rrDao.retrieveAllRequestsByEmpId(6);
		Assertions.assertEquals(2, rrs.size());
		
		List<ReimbursementRequest> rrs2= rrDao.retrieveAllRequestsByEmpId(9);
		Assertions.assertEquals(1, rrs2.size());
		
	}

	
	@Test 
	final void testRetrieveAllByMgrId() {
		List<ReimbursementRequest> rrs = rrDao.retrieveAllRequestsByMgrId(2);
		Assertions.assertEquals(2, rrs.size());
		
		List<ReimbursementRequest> rrs2= rrDao.retrieveAllRequestsByMgrId(1);
		Assertions.assertEquals(1, rrs2.size());
	}
	
	@Test
	final void testRetrieveById() {
		//(rr_id, emp_id , mgr_id ,reimbursement_request , reimbursement_status , reason )
		//1	5	1	req 5000$ to buy awesome car	DENIED	you should save to buy it, no free money for you
		ReimbursementRequest rr = rrDao.retrieveById(1);
		Assertions.assertEquals(5, rr.getEmpId());
		Assertions.assertEquals(1, rr.getMgrId());
		Assertions.assertEquals("DENIED", rr.getReimbursementStatus());
		
	}
	

	

	@Test
	final void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testDeleteById() {
		fail("Not yet implemented"); // TODO
	}
	
	
	@AfterAll
	final static void cleanUp() {
		String sql = "DELETE FROM reimbursement_system_db.reimbursement_requests  WHERE "
				+ " reimbursement_request LIKE 'test%';";
		Connection con = ConnectionEstablisher.getConnection();
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
