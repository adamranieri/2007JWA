package dev.alsabea.services.mocktests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.alsabea.doas.ReimbursementRequestDao;
import dev.alsabea.entities.Employee;
import dev.alsabea.entities.Manager;
import dev.alsabea.entities.ReimbursementRequest;
import dev.alsabea.services.ReimbursementRequestServices;
import dev.alsabea.services.impl.ReimbursementRequestServicesImpl;

class MockTestReimbursementRequestService {

	private static ReimbursementRequestDao rrd;
	private static ReimbursementRequestServices rrServ;
	
	
	@BeforeAll
	final static void init() {


		rrd = Mockito.mock(ReimbursementRequestDao.class);

		rrServ = new ReimbursementRequestServicesImpl(rrd);
	}

	final ReimbursementRequest buildRequest(long id) {
		
		ReimbursementRequest rr = new ReimbursementRequest();
		//(rr_id, emp_id , mgr_id ,reimbursement_request , reimbursement_status , reason )
		
		rr.setRrId(id);
		rr.setMgr(new Manager(2));
		rr.setEmp(new Employee(4));
		rr.setReimbursementRequest("testReimbursementRequest 5000");
		rr.setReimbursementStatus("testStatus");
		rr.setReason("testReason");
		return rr;
	}
	
	
	@Test
	final void testCreateInstance() {
		ReimbursementRequest rr =  buildRequest(0);
		
		Mockito.when(rrd.createInstance(rr)).thenReturn(rr.getRrId()+1);
		
		Assertions.assertEquals(1, rrServ.createInstance(rr));
	}

	@Test
	final void testRetrieveById() {
		ReimbursementRequest rr =  buildRequest(1);
		
		Mockito.when(rrd.retrieveById(1)).thenReturn(rr);
		
		Assertions.assertEquals(2, rrServ.retrieveById(1).getMgr().getMgrId());
	}

	@Test
	final void testUpdate() {
		
		ReimbursementRequest rr =  buildRequest(1);
		
		Mockito.when(rrd.update(rr)).thenReturn(true);
		
		Assertions.assertTrue(rrServ.update(rr));
	}
	

	@Test
	final void testDeleteById() {
		
		Mockito.when(rrd.deleteById(1)).thenReturn(true);
		
		Assertions.assertTrue(rrServ.deleteById(1));
	}

}
