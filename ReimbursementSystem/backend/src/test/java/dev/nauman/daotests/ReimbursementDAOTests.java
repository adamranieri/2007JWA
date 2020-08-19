package dev.nauman.daotests;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.nauman.daos.EmployeeDAO;
import dev.nauman.daos.EmployeeDAOImpl;
import dev.nauman.daos.ReimbursementDAO;
import dev.nauman.daos.ReimbursementDAOImpl;
import dev.nauman.entities.Reimbursement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class ReimbursementDAOTests {

	ReimbursementDAO rdao = ReimbursementDAOImpl.getReimbursementDAOImpl();
	EmployeeDAO edao = EmployeeDAOImpl.getEmployeeDAOImpl();
	
	@Test
	@Order(1)
	void createReimbursementTest() {
		Reimbursement reimbursement = new Reimbursement(0, edao.getEmployeeById(5), "firewood", 150);
		reimbursement = rdao.createReimbursement(reimbursement);
		Assertions.assertNotEquals(0, reimbursement.getrId());
	}
	@Test
	@Order(2)
	void getReimbursementByIdPositiveTest() {
		Reimbursement reimbursement = rdao.getReimbursementById(1);
		Assertions.assertEquals("Food is good", reimbursement.getNote());
	}
	@Test
	@Order(3)
	void getReimbursementByIdNegativeTest() {
		Reimbursement reimbursement = rdao.getReimbursementById(133);
		Assertions.assertEquals(null, reimbursement);
	}

	@Test
	@Order(5)
	void updateReimbursementPositiveTest() {
		Reimbursement reimbursement = new Reimbursement(1, edao.getEmployeeById(2), "Moon Bounce", "For the kids at the Harvest Festival", 300);
		reimbursement = rdao.updateReimbursement(reimbursement);
		Assertions.assertEquals("Moon Bounce", reimbursement.getItem());
	}
	@Test
	@Order(6)
	void updateReimbursementWrongReimbursementTest() {
		Reimbursement reimbursement = new Reimbursement(133, edao.getEmployeeById(2), "submitted", "Event", new Timestamp(System.currentTimeMillis()), "Moon Bounce", "For the kids at the Harvest Festival", 300);
		reimbursement = rdao.updateReimbursement(reimbursement);
		Assertions.assertEquals(null, reimbursement);
	}
	@Test
	@Order(7)
	void getAllReimbursementsTest() {
		List<Reimbursement> reimbursements = rdao.getAllReimbursements();
		System.out.println(reimbursements);
		Assertions.assertEquals(16, reimbursements.size());
	}
}
