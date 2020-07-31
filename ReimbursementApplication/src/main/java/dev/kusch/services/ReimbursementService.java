package dev.kusch.services;

import java.util.List;

import dev.kusch.entities.Employee;
import dev.kusch.entities.Reimbursement;

public interface ReimbursementService {
	
	// CRUD like
	Reimbursement createReimbursement(Reimbursement reimbursement);
	List<Reimbursement> getAllReimbursements();
	Reimbursement getReimbursement(int id);
	List<Reimbursement> getReimbursementsByEmployee(Employee employee);
	Reimbursement updateReimbursement(Reimbursement reimbursement);
	boolean deleteReimbursement(Reimbursement reimbursement);
	
	

}
