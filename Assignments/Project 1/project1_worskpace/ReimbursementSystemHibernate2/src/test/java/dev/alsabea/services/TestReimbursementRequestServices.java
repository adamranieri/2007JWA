package dev.alsabea.services;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import dev.alsabea.connection.HibernateConnectionEstablisher;
import dev.alsabea.entities.ReimbursementRequest;
import dev.alsabea.services.impl.ReimbursementRequestServicesImpl;

@TestMethodOrder(OrderAnnotation.class)
class TestReimbursementRequestServices {

	private static ReimbursementRequestServices  rrServ = ReimbursementRequestServicesImpl.getInstance();
	@Test
	final void testCreateInstance() {
		ReimbursementRequest rr = new ReimbursementRequest();
		//(rr_id, emp_id , mgr_id ,reimbursement_request , reimbursement_status , reason )
		
		
		rr.setMgrId(2);
		rr.setEmpId(4);
		rr.setReimbursementRequest("testReimbursementRequest 5000");
		rr.setReimbursementStatus("testStatus");
		rr.setReason("testReason");
		
		
		long generatedId= rrServ.createInstance(rr);
		
		Assertions.assertNotEquals(-1, generatedId);
	}
	
	
	/*
	 * we will not do createNegativeTest, because in the services we check if the 
	 * employee actually belongs to the manager or not.
	 * or if the manager or the employee actually exist
	 */
	
//	@ParameterizedTest
//	@CsvSource({"1,4" ,	//employee's manager id is not 1
//				"2, 10", //non existent employee
//				"60, 3", //non existent manager
//				"50,40" // both are not existent
//				})
//	final void testCreateInstanceNegative(int mgrId, int empId) {
//	}


	
	@ParameterizedTest
	@CsvSource({"1, 1, 1, DENIED", "4,4,2, APPROVED"})
	final void testRetrieveById(int rrId, int empId, int mgrId, String status) {
		ReimbursementRequest rr = rrServ.retrieveById(rrId);
		Assertions.assertEquals(empId, rr.getEmpId());
		Assertions.assertEquals(mgrId, rr.getMgrId());
		Assertions.assertEquals(status, rr.getReimbursementStatus());
		
	}
	
	
	@Test
	final void testRetrieveByIdNegative() {
		ReimbursementRequest rr = rrServ.retrieveById(999);
		Assertions.assertNull(rr);
		
	}

	@Test
	final void testUpdate() {
		ReimbursementRequest rr = new ReimbursementRequest();
		//(rr_id, emp_id , mgr_id ,reimbursement_request , reimbursement_status , reason )
		
		rr.setEmpId(3);
		rr.setMgrId(2);
		rr.setReimbursementRequest("testReimbursementRequestToBeUpdated");
		rr.setReimbursementStatus("testStatusToBeUpdated");
		rr.setReason("testReasonToBeUpdated");
		
		long generatedId= rrServ.createInstance(rr);
		
		ReimbursementRequest rr2 = new ReimbursementRequest();
		rr2.setRrId(generatedId);
		rr2.setEmpId(3);
		rr2.setMgrId(2);
		rr2.setReimbursementRequest("testReimbursementRequestUpdated");
		rr2.setReimbursementStatus("testStatusUpdated");
		rr2.setReason("testReasonUpdated");
		
		Assertions.assertTrue(rrServ.update(rr2));
		
		
	}
	
	
	@ParameterizedTest
	@CsvSource({"6000,1,1", "1,3,3", "2, 1, 2" , "1, 400, 2", "1, 1, 300"})
	final void testUpdateNegative(int rrId, int empId, int mgrId) {
		
		ReimbursementRequest rr = new ReimbursementRequest();
		rr.setRrId(60000);
		rr.setReimbursementRequest("testReimbursementRequestToBe");
		rr.setReimbursementStatus("testStatusToBeUpdated");
		rr.setReason("testReasonToBeUpdated");
		
		Assertions.assertFalse(rrServ.update(rr));
		
	}

	@Test
	final void testDeleteById() {
		ReimbursementRequest rr = new ReimbursementRequest();
		
		rr.setEmpId(2);
		rr.setMgrId(1);
		rr.setReimbursementRequest("testReimbursementRequestToBeDeleted");
		rr.setReimbursementStatus("testStatusToBeDeleted");
		rr.setReason("testReasonToBeDeleted");
		
		
		long generatedId= rrServ.createInstance(rr);
		
		Assertions.assertTrue(rrServ.deleteById(generatedId));
	}
	
	
	@ParameterizedTest
	@ValueSource(ints = {100,200, -300,  50000, Integer.MAX_VALUE})
	final void testDeleteByIdNegative(int id) {

		Assertions.assertFalse(rrServ.deleteById(id));
	}
	
	
	@AfterAll
	final static void cleanUp() {
		String sql = "DELETE FROM ReimbursementRequest  WHERE "
				+ " reimbursementRequest LIKE 'test%'";
		try (Session s = HibernateConnectionEstablisher.getSessionFactory().openSession()){

			s.beginTransaction();
			s.createQuery(sql).executeUpdate();
			s.getTransaction().commit();

		} catch (javax.persistence.PersistenceException e) {
			e.printStackTrace();
		}
	}

}
