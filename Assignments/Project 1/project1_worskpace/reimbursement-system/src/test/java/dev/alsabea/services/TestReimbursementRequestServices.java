package dev.alsabea.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import dev.alsabea.connection.ConnectionEstablisher;
import dev.alsabea.doas.ReimbursementRequestDao;
import dev.alsabea.doas.impl.ReimbursementRequestDaoImpl;
import dev.alsabea.entities.ReimbursementRequest;

@TestMethodOrder(OrderAnnotation.class)
class TestReimbursementRequestServices {

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
	
	
	/*
	 * to use @ParameterizedTest, you must remove the @Test annotation, you cannot use both
	 * @ParameterizedTest and @Test on one function there must be only one of them.
	 */
	//@Test 
	@ParameterizedTest
	@CsvSource({"6,2", "9,1"})
	@Order (1)
	final void testRetrieveAllByEmpId(int id, int expected) {
		List<ReimbursementRequest> rrs = rrDao.retrieveAllRequestsByEmpId(id);
		Assertions.assertEquals(expected, rrs.size());

	}
	
	
	@Test 
	@Order (2)
	final void testRetrieveAllByEmpIdNegative() {
		List<ReimbursementRequest> rrs = rrDao.retrieveAllRequestsByEmpId(99);
		Assertions.assertNull(rrs);
		
	}



	@ParameterizedTest
	@CsvSource({"2 , 2","1 , 1"})
	@Order (3)
	final void testRetrieveAllByMgrId(int id, int expected) {
		
		List<ReimbursementRequest> rrs = rrDao.retrieveAllRequestsByMgrId(id);
		Assertions.assertEquals(expected, rrs.size());
	}
	
	
	@Test 
	@Order (4)
	final void testRetrieveAllByMgrIdNegative() {
		List<ReimbursementRequest> rrs = rrDao.retrieveAllRequestsByMgrId(99);
		Assertions.assertNull(rrs);
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
	final void testRetrieveByIdNegative() {
		//(rr_id, emp_id , mgr_id ,reimbursement_request , reimbursement_status , reason )
		//1	5	1	req 5000$ to buy awesome car	DENIED	you should save to buy it, no free money for you
		ReimbursementRequest rr = rrDao.retrieveById(999);
		Assertions.assertNull(rr);
		
	}
	

	

	@Test
	final void testUpdate() {
		ReimbursementRequest rr = new ReimbursementRequest();
		//(rr_id, emp_id , mgr_id ,reimbursement_request , reimbursement_status , reason )
		
		rr.setEmpId(7);
		rr.setMgrId(2);
		rr.setReimbursementRequest("testReimbursementRequestToBeUpdated");
		rr.setReimbursementStatus("testStatusToBeUpdated");
		rr.setReason("testReasonToBeUpdated");
		
		int generatedId= rrDao.createInstance(rr);
		
		ReimbursementRequest rr2 = new ReimbursementRequest();
		rr2.setRrId(generatedId);
		rr2.setReimbursementRequest("testReimbursementRequestUpdated");
		rr2.setReimbursementStatus("testStatusUpdated");
		rr2.setReason("testReasonUpdated");
		
		Assertions.assertTrue(rrDao.update(rr2));
		
		
	}
	
	
	@Test
	final void testUpdateNegative() {
		
		ReimbursementRequest rr = new ReimbursementRequest();
		rr.setRrId(60000);
		rr.setReimbursementRequest("testReimbursementRequestToBe");
		rr.setReimbursementStatus("testStatusToBeUpdated");
		rr.setReason("testReasonToBeUpdated");
		
		Assertions.assertFalse(rrDao.update(rr));
		
	}

	@Test
	final void testDeleteById() {
		ReimbursementRequest rr = new ReimbursementRequest();
		//(rr_id, emp_id , mgr_id ,reimbursement_request , reimbursement_status , reason )
		
		rr.setEmpId(5);
		rr.setMgrId(1);
		rr.setReimbursementRequest("testReimbursementRequestToBeDeleted");
		rr.setReimbursementStatus("testStatusToBeDeleted");
		rr.setReason("testReasonToBeDeleted");
		
		
		int generatedId= rrDao.createInstance(rr);
		
		Assertions.assertTrue(rrDao.deleteById(generatedId));
	}
	
	
	@ParameterizedTest
	@ValueSource(ints = {100,200, -300,  50000, Integer.MAX_VALUE})
	final void testDeleteByIdNegative(int id) {

		Assertions.assertFalse(rrDao.deleteById(id));
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
