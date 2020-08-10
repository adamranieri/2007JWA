package dev.kurt.servicetests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mockito;

import dev.kurt.daos.ReimbursementDAO;
import dev.kurt.entities.Employee;
import dev.kurt.entities.Reimbursement;
import dev.kurt.services.ReimbursementService;
import dev.kurt.services.ReimbursementServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
public class ReimbursementServiceTests {
	
	
	@Test
	@Order(1) 
	void createReimbursement() {
		Employee kurt = new Employee(0,"kd@email.com","password","Kurt","Martinez");
		Reimbursement rei = new Reimbursement(1,"Ubers from airport",100,"july29",kurt);
		
		ReimbursementDAO rDao = Mockito.mock(ReimbursementDAO.class);
	
		
		Mockito.when(rDao.createReimbursement(rei)).thenReturn(rei);
		ReimbursementService rServ = new ReimbursementServiceImpl(rDao);
		
		rServ.createReimbursement(rei);
		Assertions.assertNotEquals(0, rei.getReimbursementId());
	}
	
	@Test
	@Order(2) 
	void getReimbursementById() {
		Employee kurt = new Employee(0,"kd@email.com","password","Kurt","Martinez");
		Reimbursement rei = new Reimbursement(1,"Ubers from airport",100,"july29",kurt);
		
		ReimbursementDAO rDao = Mockito.mock(ReimbursementDAO.class);

		
		Mockito.when(rDao.getReimbursementById(1)).thenReturn(rei);
		ReimbursementService rServ = new ReimbursementServiceImpl(rDao);
		
		Reimbursement Reimbursement = rServ.getReimbursementById(1);
		Assertions.assertEquals(1, Reimbursement.getReimbursementId());
	}
	
	@Test
	@Order(3) 
	void getAllReimbursements() {
		Employee kurt = new Employee(0,"kd@email.com","password","Kurt","Martinez");
		Reimbursement rei = new Reimbursement(0,"Ubers from airport",100,"july29",kurt);
		Reimbursement rei2 = new Reimbursement(0,"BEEF",134,"july3424234",kurt);
		Reimbursement rei3 = new Reimbursement(0,"that thing thigny thignythign",10440,"july76",kurt);
		
		List<Reimbursement> fakeReimbursements = new ArrayList<Reimbursement>();
		fakeReimbursements.add(rei);
		fakeReimbursements.add(rei2);
		fakeReimbursements.add(rei3);
		
		ReimbursementDAO rDao = Mockito.mock(ReimbursementDAO.class);

		
		Mockito.when(rDao.getAllReimbursements()).thenReturn(fakeReimbursements);
		ReimbursementService rServ = new ReimbursementServiceImpl(rDao);
		
		List<Reimbursement> Reimbursements = rServ.getAllReimbursements();
		Assertions.assertEquals(3,Reimbursements.size());
	}
	
	@Test
	@Order(4) 
	void getReimbursementsByEmployee() {
		Employee kurt = new Employee(0,"kd@email.com","password","Kurt","Martinez");
		Reimbursement rei = new Reimbursement(0,"Ubers from airport",100,"july29",kurt);
		Reimbursement rei2 = new Reimbursement(0,"BEEF",134,"july3424234",kurt);
		
		List<Reimbursement> fakeReimbursements = new ArrayList<Reimbursement>();
		fakeReimbursements.add(rei);
		fakeReimbursements.add(rei2);
		
		ReimbursementDAO rDao = Mockito.mock(ReimbursementDAO.class);
		
		
		Mockito.when(rDao.getEmployeeReimbursements(kurt)).thenReturn(fakeReimbursements);
		ReimbursementService rServ = new ReimbursementServiceImpl(rDao);
		
		
		List<Reimbursement> michaelsReimbursements = rServ.getReimbursementsByEmployee(kurt);
		Assertions.assertEquals(2,michaelsReimbursements.size());
	}
	
	@Test
	@Order(5) 
	void updateReimbursement() {
		Employee kurt = new Employee(0,"kd@email.com","password","Kurt","Martinez");
		Reimbursement rei = new Reimbursement(0,"Ubers from airport",100,"july29",kurt);
		Reimbursement rei2 = new Reimbursement(0,"BEEF",134,"july3424234",kurt);
		
		ReimbursementDAO rDao = Mockito.mock(ReimbursementDAO.class);
		
		Mockito.when(rDao.updateReimbursement(rei)).thenReturn(rei2);
		ReimbursementService rServ = new ReimbursementServiceImpl(rDao);
		
		rei.setAmount(134);
		Reimbursement reimbursement = rServ.updateReimbursement(rei);
		Assertions.assertEquals(134,reimbursement.getAmount());
	}
	
	@Test
	@Order(6) 
	void addStatusUpdateToReimbursement() {
		Employee kurt = new Employee(0,"kd@email.com","password","Kurt","Martinez");
		Reimbursement rei = new Reimbursement(0,"Ubers from airport",100,"july29",kurt);
		Reimbursement rei2 = new Reimbursement(0,"BEEF",134,"july3424234",kurt);
		
		
		ReimbursementDAO rDao = Mockito.mock(ReimbursementDAO.class);

		

		Mockito.when(rDao.updateReimbursement(rei)).thenReturn(rei2);
		ReimbursementService eServ = new ReimbursementServiceImpl(rDao);
		
		Reimbursement rei3 = eServ.addStatusUpdateToReimbursement(rei,"who Cares","i dont");
		
		Assertions.assertEquals(134,rei3.getAmount());
	}
	
	@Test
	@Order(7) 
	void deleteReimbursement() {
		Employee bobby = new Employee(0,"bob@email.com","bobspassword","Bob","Bobson");
		Reimbursement reimbursement = new Reimbursement(0,"Ubers from airport",100,"july29",bobby);
		
		ReimbursementDAO rDao = Mockito.mock(ReimbursementDAO.class);
		
		Mockito.when(rDao.deleteReimbursement(reimbursement)).thenReturn(true);
		ReimbursementService rServ = new ReimbursementServiceImpl(rDao);
		
		boolean result = rServ.deleteReimbursement(reimbursement);
		Assertions.assertEquals(true,result);
	}
	
}
