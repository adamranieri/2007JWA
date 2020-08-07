package dev.kusch.servicetests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mockito;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.kusch.daos.EmployeeDAO;
import dev.kusch.daos.ReimbursementDAO;
import dev.kusch.entities.Employee;
import dev.kusch.entities.Reimbursement;
import dev.kusch.services.EmployeeService;
import dev.kusch.services.EmployeeServiceImpl;
import dev.kusch.services.ReimbursementService;
import dev.kusch.services.ReimbursementServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
class ReimbursementServiceTests {

	private static ReimbursementService rserv = null;
	private static EmployeeService eserv = null;
	
	private static Employee emp;
	private static Employee badEmp;
	private static Reimbursement reim;
	private static Reimbursement upReim;
	private static Reimbursement badReim;
	private static Reimbursement createReim;
	private static List<Reimbursement> listReim;
	
	@BeforeAll
	static void setupMocks() {
		emp = new Employee(1, "Sullivan", "B3stSc4r3r", "James", "Sullivan", new ArrayList<Reimbursement>());
		badEmp = new Employee(100, "Boo", "hugs", "Boo", "Boo", new ArrayList<Reimbursement>());
		createReim = new Reimbursement(0, "Denied", "Workplace accident at Chinese Restaraunt", 1000.00, "We received no invoice from a restaraunt", emp);
		reim = new Reimbursement(1, "Denied", "Workplace accident at Chinese Restaraunt", 1000.00, "We received no invoice from a restaraunt", emp);
		badReim = new Reimbursement(1000, "Pending", "YOUR FACE", 1000.00, "", badEmp);
		upReim = new Reimbursement(1, "Approved", "Workplace accident at Chinese Restaraunt", 1000.00, "We received no invoice from a restaraunt", emp);
		listReim = new ArrayList<Reimbursement>();
		listReim.add(reim);
		ReimbursementDAO rdao = Mockito.mock(ReimbursementDAO.class);
		EmployeeDAO edao = Mockito.mock(EmployeeDAO.class);

		Mockito.when(edao.getEmployeeById(1)).thenReturn(emp);
		Mockito.when(edao.getEmployeeById(70)).thenReturn(badEmp);
		
		Mockito.when(rdao.createReimbursement(createReim)).thenReturn(reim);
		Mockito.when(rdao.getAllReimbursements()).thenReturn(listReim);
		Mockito.when(rdao.getReimbursementById(1)).thenReturn(reim);
		Mockito.when(rdao.getReimbursementById(70)).thenReturn(null);
		Mockito.when(rdao.getReimbursementsByEmployee(emp)).thenReturn(listReim);
		Mockito.when(rdao.getReimbursementsByEmployee(badEmp)).thenReturn(new ArrayList<Reimbursement>());
		Mockito.when(rdao.updateReimbursement(upReim)).thenReturn(upReim);
		Mockito.when(rdao.updateReimbursement(badReim)).thenReturn(null);
		Mockito.when(rdao.deleteReimbursement(reim)).thenReturn(true);
		Mockito.when(rdao.deleteReimbursement(badReim)).thenReturn(false);
		
		rserv = new ReimbursementServiceImpl(rdao);
		eserv = new EmployeeServiceImpl(edao);
	}
	
	@Test
	@Order(1)
	void createReimbursement() {
		Reimbursement reimbursement = rserv.createReimbursement(createReim);
		Assertions.assertNotEquals(0, reimbursement.getRid());
	}
	
	@Test
	@Order(2)
	void getAllReimbursements() {
		List<Reimbursement> reimbursement = rserv.getAllReimbursements();
		Assertions.assertEquals(1, reimbursement.size());
	}
	
	@Test
	@Order(3)
	void getReimbursement() {
		Reimbursement reimbursement = rserv.getReimbursement(1);
		Assertions.assertEquals("Denied", reimbursement.getStatus());
	}
	
	@Test
	@Order(4)
	void getReimbursementByBadId() {
		Reimbursement reimbursement = rserv.getReimbursement(10000);
		Assertions.assertNull(reimbursement);
	}
	
	@Test
	@Order(5)
	void getReimbursementsByEmployee() {
		List<Reimbursement> reimbursement = rserv.getReimbursementsByEmployee(emp);
		Assertions.assertEquals(1, reimbursement.size());
		Assertions.assertEquals("Denied", reimbursement.get(0).getStatus());
	}
	
	@Test
	@Order(6)
	void getReimbursementsByBadEmployee() {
		List<Reimbursement> reimbursement = rserv.getReimbursementsByEmployee(badEmp);
		Assertions.assertEquals(0, reimbursement.size());
	}
	
	@Test
	@Order(7)
	void updateReimbursement() {
		Reimbursement reimbursement = rserv.updateReimbursement(upReim);
		Assertions.assertEquals("Approved",reimbursement.getStatus());
	}
	
	@Test
	@Order(8)
	void updateBadReimbursement() {
		Reimbursement reimbursement = rserv.updateReimbursement(badReim);
		Assertions.assertNull(reimbursement);
	}
	
	@Test
	@Order(9)
	void testDeleteReimbursement() {
		boolean result = rserv.deleteReimbursement(reim);
		Assertions.assertTrue(result);
	}
	
	@Test
	@Order(10)
	void testDeleteBadReimbursement() {
		boolean result = rserv.deleteReimbursement(badReim);
		Assertions.assertFalse(result);
	}

}
