package dev.kurt.daotests;



import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.kurt.daos.EmployeeDAO;
import dev.kurt.daos.EmployeeDAOHibernate;
import dev.kurt.daos.ReimbursementDAO;
import dev.kurt.daos.ReimbursementDAOHibernate;
import dev.kurt.entities.Employee;
import dev.kurt.entities.Reimbursement;



@TestMethodOrder(OrderAnnotation.class)
class ReimbursementDAOTests {

	private static ReimbursementDAO reiDao = new ReimbursementDAOHibernate();
	private static EmployeeDAO eDao = new EmployeeDAOHibernate();
	private static Employee kurt = new Employee(0,"kd@email.com","password","Kurt","Martinez");
	private static Employee bobby = new Employee(0,"bob@aol.com","bobisbob","Bob","Boberson");
	
	
	
	@BeforeAll
	static void setUp() {
		eDao.createEmployee(kurt);
		eDao.createEmployee(bobby);
	}
	
	@Test
	@Order(1) 
	void createReimbursement() {
		Reimbursement rei = new Reimbursement(0,"Ubers from airport",100,"july29",kurt);
		reiDao.createReimbursement(rei);
		Assertions.assertNotEquals(0, rei.getReimbursementId());
	}
	
	@Test
	@Order(2) 
	void getReimbursementById() {
		Reimbursement rei = reiDao.getReimbursementById(1);
		Assertions.assertEquals(1, rei.getReimbursementId());
	}
	
	@Test
	@Order(3) 
	void getAllReimbursements() {
		Reimbursement rei = new Reimbursement(0,"Filet Mignon",60,"july30",kurt);
		reiDao.createReimbursement(rei);
		List<Reimbursement> reis = reiDao.getAllReimbursements();
		Assertions.assertEquals(2,reis.size());
	}
	
	@Test
	@Order(4) 
	void getEmployeeReimbursements() {
		Reimbursement rei = new Reimbursement(0,"Bobs burger",1000,"july31",bobby);
		reiDao.createReimbursement(rei);
		List<Reimbursement> reis = reiDao.getEmployeeReimbursements(kurt);
		Assertions.assertNotEquals(3,reis.size());
	}
	
	
	@Test
	@Order(5) 
	void updateReimbursement() {
		Reimbursement rei = reiDao.getReimbursementById(1);
		rei.setStatus("Approved");
		reiDao.updateReimbursement(rei);
		Assertions.assertNotEquals(null,rei.getStatus());
	}
	
	@Test
	@Order(6) 
	void deleteReimbursement() {
		Reimbursement rei = reiDao.getReimbursementById(1);
		boolean result = reiDao.deleteReimbursement(rei);
		Assertions.assertEquals(true,result);
	}
	
	@AfterAll
	static void tearDown() {
		eDao.deleteEmployee(kurt);
		eDao.deleteEmployee(bobby);
	}
}
