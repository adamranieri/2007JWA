package dev.kurt.servicetests;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


import dev.kurt.entities.Employee;
import dev.kurt.entities.Reimbursement;
import dev.kurt.services.EmployeeService;
import dev.kurt.services.EmployeeServiceImpl;
import dev.kurt.services.ReimbursementService;
import dev.kurt.services.ReimbursementServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
public class ReimbursementServiceTests {

	private static ReimbursementService reiServ = new ReimbursementServiceImpl();
	private static EmployeeService eServ = new EmployeeServiceImpl();
	private static Employee kurt = eServ.getEmployeeById(1);
	private static Employee bobby = eServ.getEmployeeById(2);
	
	
	@Test
	@Order(1) 
	void createReimbursement() {
		Reimbursement rei = new Reimbursement(0,"Ubers from airport",100,"july29",kurt);
		reiServ.createReimbursement(rei);
		Assertions.assertNotEquals(0, rei.getReimbursementId());
	}
	
	@Test
	@Order(2) 
	void getReimbursementById() {
		Reimbursement rei = reiServ.getReimbursementById(1);
		Assertions.assertEquals(1, rei.getReimbursementId());
	}
	
	@Test
	@Order(3) 
	void getAllReimbursements() {
		Reimbursement rei = new Reimbursement(0,"Filet Mignon",60,"july30",kurt);
		reiServ.createReimbursement(rei);
		List<Reimbursement> reis = reiServ.getAllReimbursements();
		Assertions.assertEquals(2,reis.size());
	}
	
	@Test
	@Order(4) 
	void getReimbursementsByEmployee() {
		Reimbursement rei = new Reimbursement(0,"Bobs burger",1000,"july31",bobby);
		reiServ.createReimbursement(rei);
		List<Reimbursement> reis = reiServ.getReimbursementsByEmployee(kurt);
		Assertions.assertNotEquals(3,reis.size());
	}
	
	@Test
	@Order(5) 
	void updateReimbursement() {
		Reimbursement rei = reiServ.getReimbursementById(1);
		rei.setStatus("Approved");
		reiServ.updateReimbursement(rei);
		Assertions.assertNotEquals(null,rei.getStatus());
	}
	
	@Test
	@Order(6) 
	void addStatusUpdateToReimbursement() {
		Reimbursement rei = reiServ.getReimbursementById(2);
		reiServ.addStatusUpdateToReimbursement(rei,"Denied", "Filet mignon is not a business expense - Michael");
		Assertions.assertNotEquals(null, rei.getStatusDate());
	}
	
	@Test
	@Order(7) 
	void deleteReimbursement() {
		Reimbursement rei = reiServ.getReimbursementById(1);
		boolean result = reiServ.deleteReimbursement(rei);
		Assertions.assertEquals(true,result);
	}
	
}
