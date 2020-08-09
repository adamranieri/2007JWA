package dev.alsabea.doas;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import dev.alsabea.doas.impl.ReimbursementRequestDaoImpl;
import dev.alsabea.entities.Employee;
import dev.alsabea.entities.Manager;
import dev.alsabea.entities.ReimbursementRequest;
import dev.alsabea.setupteardown.SetUpAndTearDown;

@TestMethodOrder(OrderAnnotation.class)
class TestReimbursementRequestDao {

	private static ReimbursementRequestDao rrDao = ReimbursementRequestDaoImpl.getInstance();

	@BeforeAll
	@Test
	final static void setup() {
		SetUpAndTearDown.setup();
	}

	@AfterAll
	@Test
	final static void teardown() {
		SetUpAndTearDown.teardown();
	}

	@Test
	final void testCreateInstance() {
		ReimbursementRequest rr = new ReimbursementRequest();

		rr.setMgr(new Manager(2));
		rr.setEmp(new Employee(4));
		rr.setReimbursementRequest("testReimbursementRequest 5000");
		rr.setReimbursementStatus("testStatus");
		rr.setReason("testReason");

		long generatedId = rrDao.createInstance(rr);

		Assertions.assertNotEquals(-1, generatedId);
	}

	/*
	 * we will not do createNegativeTest, because in the services we check if the
	 * employee actually belongs to the manager or not. or if the manager or the
	 * employee actually exist
	 */

	@ParameterizedTest
	@CsvSource({ "1, 1, 1, DENIED", "4,4,2, APPROVED" })
	final void testRetrieveById(int rrId, int empId, int mgrId, String status) {
		ReimbursementRequest rr = rrDao.retrieveById(rrId);
		Assertions.assertEquals(empId, rr.getEmp().getEmpId());
		Assertions.assertEquals(mgrId, rr.getMgr().getMgrId());
		Assertions.assertEquals(status, rr.getReimbursementStatus());

	}

	@Test
	final void testRetrieveByIdNegative() {
		ReimbursementRequest rr = rrDao.retrieveById(999);
		Assertions.assertNull(rr);

	}

	@Test
	final void testUpdate() {
		ReimbursementRequest rr = new ReimbursementRequest();

		rr.setEmp(new Employee(3));
		rr.setMgr(new Manager(2));
		rr.setReimbursementRequest("testReimbursementRequestToBeUpdated");
		rr.setReimbursementStatus("testStatusToBeUpdated");
		rr.setReason("testReasonToBeUpdated");

		long generatedId = rrDao.createInstance(rr);

		ReimbursementRequest rr2 = new ReimbursementRequest();
		rr2.setRrId(generatedId);
		rr2.setEmp(new Employee(3));
		rr2.setMgr(new Manager(2));
		rr2.setReimbursementRequest("testReimbursementRequestUpdated");
		rr2.setReimbursementStatus("testStatusUpdated");
		rr2.setReason("testReasonUpdated");

		Assertions.assertTrue(rrDao.update(rr2));

	}

	@ParameterizedTest
	@CsvSource({ "6000,1,1", "1,3,3", "2, 1, 2", "1, 400, 2", "1, 1, 300" })
	final void testUpdateNegative(int rrId, int empId, int mgrId) {

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

		rr.setEmp(new Employee(2));
		rr.setMgr(new Manager(1));
		rr.setReimbursementRequest("testReimbursementRequestToBeDeleted");
		rr.setReimbursementStatus("testStatusToBeDeleted");
		rr.setReason("testReasonToBeDeleted");

		long generatedId = rrDao.createInstance(rr);

		Assertions.assertTrue(rrDao.deleteById(generatedId));
	}

	@ParameterizedTest
	@ValueSource(ints = { 100, 200, -300, 50000, Integer.MAX_VALUE })
	final void testDeleteByIdNegative(int id) {

		Assertions.assertFalse(rrDao.deleteById(id));
	}

}
