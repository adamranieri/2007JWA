package dev.nauman.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.nauman.daos.EmployeeDAO;
import dev.nauman.daos.EmployeeDAOImpl;
import dev.nauman.daos.ReimbursementDAO;
import dev.nauman.daos.ReimbursementDAOImpl;
import dev.nauman.entities.Reimbursement;
import dev.nauman.services.EmployeeService;
import dev.nauman.services.EmployeeServiceImpl;
import dev.nauman.services.ReimbursementService;
import dev.nauman.services.ReimbursementServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
class ReimbursmentServiceTests {

	ReimbursementService rserv = ReimbursementServiceImpl.getEmployeeServiceImpl();
	EmployeeService eserv = EmployeeServiceImpl.getEmployeeServiceImpl();
	
	@Test
	@Order(1)
	void getAllReimbursementsTest() {
		List<Reimbursement> reimbursements = rserv.getAllReimbursements();
		System.out.println(reimbursements);
		Assertions.assertEquals(16, reimbursements.size());
		
	}

}
