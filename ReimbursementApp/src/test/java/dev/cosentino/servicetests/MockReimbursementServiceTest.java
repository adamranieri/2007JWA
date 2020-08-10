package dev.cosentino.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.cosentino.daos.EmployeeDAO;
import dev.cosentino.daos.ReimbursementDAO;
import dev.cosentino.entities.Employee;
import dev.cosentino.entities.Reimbursement;
import dev.cosentino.services.ReimbursementImpl;
import dev.cosentino.services.ReimbursementService;

class MockReimbursementServiceTest {

 
	  @Test 
	  void getReimbursementByEmployeeAndStatus() { 
		  Employee fred = new Employee(0, "Fred", "FredTheFake", "password123", "Employee"); 
		  Reimbursement reim = new Reimbursement(0,"Submitted","",400,"payroll messed up","2020-03-03",fred);
		  Reimbursement reim2 = new Reimbursement(0,"Approved","",4080,"payroll messed up","2020-06-03",fred);
		  Reimbursement reim3 = new Reimbursement(0,"Denied","",200,"payroll messed up","2020-03-07",fred);
		  Reimbursement reim4 = new Reimbursement(0,"Approved","",40,"payroll messed up","2020-07-09",fred);
		  Reimbursement reim5 = new Reimbursement(0,"Denied","",450,"payroll messed up","2020-01-10",fred);
	  
		  List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		  reimbursements.add(reim); 
		  reimbursements.add(reim2);
		  reimbursements.add(reim3);
		  reimbursements.add(reim4); 
		  reimbursements.add(reim5);
	  		  
		  EmployeeDAO edao = Mockito.mock(EmployeeDAO.class); 
		  ReimbursementDAO rdao = Mockito.mock(ReimbursementDAO.class);
		  Mockito.when(rdao.getAllReimbursements()).thenReturn(reimbursements);
		  Mockito.when(rdao.getReimbursementsByEmployeeId(fred.geteId())).thenReturn(reimbursements);
		  
		  ReimbursementService rserv = new ReimbursementImpl(rdao,edao);
	  
		  List<Reimbursement> reims = rserv.getAllReimbursements();
		  reims = rserv.getReimbursementByEmployeeAndStatus(fred, "Approved");
	  		  
		  Assertions.assertEquals(2, reims.size());
	  }

}
