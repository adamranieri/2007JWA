package dev.kusch.daotests;

import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;

import dev.kusch.daos.EmployeeDAO;
import dev.kusch.daos.EmployeeDAOHibernate;
import dev.kusch.daos.ReimbursementDAO;
import dev.kusch.daos.ReimbursementDAOHibernate;
import dev.kusch.entities.Employee;
import dev.kusch.entities.Reimbursement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@TestMethodOrder(OrderAnnotation.class)
public class ReimbursementDAOTests {
	
	private static ReimbursementDAO rdao = ReimbursementDAOHibernate.getReimbursementDAOHibernate();
	private static EmployeeDAO edao = EmployeeDAOHibernate.getEmployeeDAOHibernate();
	
	@Test
	@Order(1)
	void createReimbursement() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		Employee employee = edao.getEmployeeById(1);
		try {
			date = dateFormat.parse("06/07/2020");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reimbursement reim = new Reimbursement(0, "Accepted", "Bought new clipboard", 10.45, "Better to file your paperwork", employee);
		rdao.createReimbursement(reim);
		Assertions.assertNotEquals(0, reim.getRid());
	}
	
	@Test
	@Order(2)
	void getAllReimbursements() {
		List<Reimbursement> reimbursements = rdao.getAllReimbursements();
		Assertions.assertEquals(1, reimbursements.size());
	}
	
	@Test
	@Order(3)
	void getReimbursementById() {
		Reimbursement reim = rdao.getReimbursementById(1);
		Assertions.assertEquals(10.45, reim.getAmount());
	}

	@Test
	@Order(4)
	void getReimbursementByBadId() {
		Reimbursement reim = rdao.getReimbursementById(1000);
		Assertions.assertNull(reim);
	}
	
	@Test
	@Order(5)
	void getReimbursementByUser() {
		Employee emp = edao.getEmployeeById(1);
		List<Reimbursement> reimbursements = rdao.getReimbursementsByEmployee(emp);
		Assertions.assertEquals(1, reimbursements.size());
	}
	
	@Test
	@Order(6)
	void getReimbursementByBadUser() {
		List<Reimbursement> reim = new ArrayList<Reimbursement>();
		Employee emp = new Employee(1000, "BAD", "BAD", "BAD", "BAD", reim);
		List<Reimbursement> reimbursements = rdao.getReimbursementsByEmployee(emp);
		Assertions.assertEquals(0, reimbursements.size());
	}
	
	@Test
	@Order(7)
	void updateReimbursement() {
		Reimbursement reim = rdao.getReimbursementById(1);
		reim.setAcceptMessage("I'm Watching");
		reim = rdao.updateReimbursement(reim);
		Assertions.assertEquals("I'm Watching", rdao.getReimbursementById(1).getAcceptMessage());
	}
	
	@Test
	@Order(8)
	void updateBadReimbursement() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		Employee employee = edao.getEmployeeById(1);
		try {
			date = dateFormat.parse("06/07/2020");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reimbursement reimbursement = new Reimbursement(1000, "Submitted", "BAD REQUEST", 18.37, " ", employee);
		
		
		reimbursement = rdao.updateReimbursement(reimbursement);
		Assertions.assertNull(reimbursement);
	}
	
	@Test
	@Order(9)
	void deleteReimbursement() {
		boolean result = rdao.deleteReimbursement(rdao.getReimbursementById(1));
		Assertions.assertTrue(result);
	}
	
	@Test
	@Order(10)
	void deleteBadReimbursement() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		Employee employee = edao.getEmployeeById(1);
		try {
			date = dateFormat.parse("06/07/2020");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reimbursement reimbursement = new Reimbursement(1000, "Submitted", "BAD REQUEST", 18.37, " ", employee);
		boolean result = rdao.deleteReimbursement(reimbursement);
		Assertions.assertFalse(result);
	}
}
