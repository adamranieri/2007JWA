package dev.cosentino.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.management.remote.rmi.RMIConnectorServer;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.cosentino.daos.EmployeeDAO;
import dev.cosentino.daos.EmployeeDAOhibernate;
import dev.cosentino.daos.ReimbursementDAO;
import dev.cosentino.daos.ReimbursementDAOhibernate;
import dev.cosentino.entities.Employee;
import dev.cosentino.entities.Reimbursement;
import dev.cosentino.services.EmployeeService;
import dev.cosentino.services.EmployeeServiceImpl;
import dev.cosentino.services.ReimbursementImpl;
import dev.cosentino.services.ReimbursementService;

@TestMethodOrder(OrderAnnotation.class)
class ReimbursementServiceTests {

	private static EmployeeService eserv = new EmployeeServiceImpl();
	private static ReimbursementService rserv = new ReimbursementImpl();
	 
//	@Test
//	@Order(1)
//	void createReimbursement() {
//		Employee employee = eserv.getEmployeeById(2);
//		Reimbursement reim = new Reimbursement(0,"Submitted","",400,"payroll messed up",Date.valueOf(LocalDate.now()).toString(),employee);
//		rserv.createReimbursement(reim);
//		Assertions.assertNotEquals(0, reim.getrId());
//	}
	
	@Test
	@Order(2)
	void getReimbursementById() {
		Reimbursement reim = rserv.getReimbursementById(2);
		Assertions.assertEquals(2, reim.getrId());
	}
	
	@Test
	@Order(3)
	void getReimbursementsByEmployeeId() {
		List<Reimbursement> reims = rserv.getReimbursementsByEmployeeId(1);		
		Assertions.assertEquals(3, reims.size());
	}
	
	@Test
	@Order(4)
	void getReimbursementByUsername() {
		List<Reimbursement> reims = rserv.getReimbursementByUsername("BigSteve32");
		Assertions.assertEquals(3, reims.size());
	}
	
	@Test
	@Order(5)
	void getReimbursementByStatus() {
		List<Reimbursement> reims = rserv.getReimbursementByStatus("Approved");
		Assertions.assertEquals(3, reims.size());
	}
	
	@Test
	@Order(6)
	void getAllReimbursements() {
		List<Reimbursement> reims = rserv.getAllReimbursements();
		Assertions.assertEquals(9, reims.size());
	}
	
	@Test
	@Order(7)
	void updateReimbursement() {
		Reimbursement reim = rserv.getReimbursementById(8);
		reim.setStatus("Approved");
		reim.setManager_note("Approved!!");
		rserv.updateReimbursement(reim);
		System.out.println(reim);
		Assertions.assertEquals("Approved", reim.getStatus());
		Assertions.assertEquals("Approved!!", reim.getManager_note());
	}
	
	@Test
	@Order(8)
	void getReimbursementByEmpAndStatus() {
		List<Reimbursement> reim = rserv.getReimbursementByEmployeeAndStatus(eserv.getEmployeeById(1), "Approved");
		Assertions.assertEquals(1, reim.size());
	}

}
