package dev.cosentino.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;

import dev.cosentino.daos.EmployeeDAO;
import dev.cosentino.daos.EmployeeDAOhibernate;
import dev.cosentino.daos.ReimbursementDAO;
import dev.cosentino.daos.ReimbursementDAOhibernate;
import dev.cosentino.entities.Employee;
import dev.cosentino.entities.Reimbursement;

@TestMethodOrder(OrderAnnotation.class)

class ReimbursementDAOtests {
	
	private static EmployeeDAO edao = EmployeeDAOhibernate.getEmployeeDAOhibernate();
	private static ReimbursementDAO rdao = ReimbursementDAOhibernate.getReimbursementDAOhibernate();

	@Test
	@Order(1)
	public void createReimbursement() {
		Employee emp = edao.getEmployeeById(2);
		Reimbursement reim = new Reimbursement(0, "Submitted", "", 28, "lunch meeting", Date.valueOf(LocalDate.now()).toString(), emp);
		rdao.createReimbursement(reim);
		Assertions.assertNotEquals(0, reim.getrId());
	}
	

	@Test
	@Order(2)
	public void getReimbursementById() {
		Reimbursement reim = rdao.getReimbursementById(1);
		Assertions.assertEquals(1, reim.getrId());
	}

	@Test
	@Order(3)
	public void getReimbursementsByEmployeeId() {
		List<Reimbursement> reim = rdao.getReimbursementsByEmployeeId(1);
		Assertions.assertEquals(3, reim.size());
	}

	@Test
	@Order(4)
	public void getReimbursementByUsername() {
		List<Reimbursement> reim = rdao.getReimbursementByUsername("BigSteve32");
		Assertions.assertEquals(3, reim.size());
	}

	@Test
	@Order(5)
	public void getReimbursementBySubmittedStatus() {
		List<Reimbursement> reim = rdao.getReimbursementByStatus("Submitted");
		Assertions.assertEquals(5, reim.size());
	}
	
	@Test
	@Order(6)
	public void getReimbursementByApprovedStatus() {
		List<Reimbursement> reim = rdao.getReimbursementByStatus("Approved");
		Assertions.assertEquals(2, reim.size());
	}
	@Test
	@Order(7)
	public void getReimbursementByDeniedStatus() {
		List<Reimbursement> reim = rdao.getReimbursementByStatus("Denied");
		Assertions.assertEquals(2, reim.size());
	}
	
	@Test
	@Order(8)
	public void getAllReimbursements() {
		List<Reimbursement> reim = rdao.getAllReimbursements();
		Assertions.assertEquals(9, reim.size());
	}
	
	@Test
	@Order(9)
	public void updateReimbursement() {
		Reimbursement reim = rdao.getReimbursementById(8);
		reim.setStatus("Approved");
		reim.setManager_note("Approved!");
		rdao.updateReimbursement(reim);
		Assertions.assertEquals("Approved",reim.getStatus());
		Assertions.assertEquals("Approved!",reim.getManager_note());
	}

}
