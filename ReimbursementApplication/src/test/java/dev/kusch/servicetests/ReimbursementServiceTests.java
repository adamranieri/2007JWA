package dev.kusch.servicetests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.kusch.entities.Employee;
import dev.kusch.entities.Reimbursement;
import dev.kusch.services.EmployeeService;
import dev.kusch.services.EmployeeServiceImpl;
import dev.kusch.services.ReimbursementService;
import dev.kusch.services.ReimbursementServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
class ReimbursementServiceTests {

	private ReimbursementService rserv = new ReimbursementServiceImpl();
	private EmployeeService eserv = new EmployeeServiceImpl();
	private static Date date = new Date();
	
	@BeforeAll
	static void setDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		try {
			date = dateFormat.parse("06/07/2020");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void createReimbursement() {
		Employee emp = eserv.getEmployeeById(1);
		Reimbursement reim = new Reimbursement(0, "Denied", "Workplace accident at Chinese Restaraunt", date, 1000.00, "We received no invoice from a restaraunt",emp);
		
		rserv.createReimbursement(reim);
		Assertions.assertNotEquals(0, reim.getRid());
	}
	
	@Test
	@Order(2)
	void getAllReimbursements() {
		List<Reimbursement> reim = rserv.getAllReimbursements();
		Assertions.assertEquals(1, reim.size());
	}
	
	@Test
	@Order(3)
	void getReimbursement() {
		Reimbursement reim = rserv.getReimbursement(1);
		Assertions.assertEquals("Denied", reim.getStatus());
	}
	
	@Test
	@Order(4)
	void getReimbursementByBadId() {
		Reimbursement reim = rserv.getReimbursement(10000);
		Assertions.assertNull(reim);
	}
	
	@Test
	@Order(5)
	void getReimbursementsByEmployee() {
		Employee emp = eserv.getEmployeeById(1);
		List<Reimbursement> reim = rserv.getReimbursementsByEmployee(emp);
		System.out.println(reim);
		Assertions.assertEquals(1, reim.size());
		Assertions.assertEquals("Denied", reim.get(0).getStatus());
	}
	
	@Test
	@Order(6)
	void getReimbursementsByBadEmployee() {
		Employee emp = new Employee(1000, "Bad", "Bad", "bad", "bad", new ArrayList<Reimbursement>());
		List<Reimbursement> reim = rserv.getReimbursementsByEmployee(emp);
		Assertions.assertEquals(0, reim.size());
	}
	
	@Test
	@Order(7)
	void updateReimbursement() {
		Reimbursement reim = rserv.getReimbursement(1);
		reim.setStatus("Approved");
		reim = rserv.updateReimbursement(reim);
		Reimbursement reimAct = rserv.getReimbursement(1);
		Assertions.assertEquals("Approved",reimAct.getStatus());
	}
	
	@Test
	@Order(8)
	void updateBadReimbursement() {
		Employee emp = new Employee(1000, "Bad", "Bad", "bad", "bad", new ArrayList<Reimbursement>());
		Reimbursement reim = new Reimbursement(1000, "Pending", "YOUR FACE", date, 1000.00, "",emp);
		reim = rserv.updateReimbursement(reim);
		Assertions.assertNull(reim);
	}
	
	@Test
	@Order(9)
	void testDeleteReimbursement() {
		Reimbursement reim = rserv.getReimbursement(1);
		boolean result = rserv.deleteReimbursement(reim);
		Assertions.assertTrue(result);
	}
	
	@Test
	@Order(10)
	void testDeleteBadReimbursement() {
		Employee emp = new Employee(1000, "Bad", "Bad", "bad", "bad", new ArrayList<Reimbursement>());
		Reimbursement reim = new Reimbursement(1000, "Pending", "YOUR FACE", date, 1000.00, "",emp);
		boolean result = rserv.deleteReimbursement(reim);
		Assertions.assertFalse(result);
	}

}
