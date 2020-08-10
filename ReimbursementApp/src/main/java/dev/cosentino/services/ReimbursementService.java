package dev.cosentino.services;

import java.util.List;

import dev.cosentino.entities.Employee;
import dev.cosentino.entities.Reimbursement;

public interface ReimbursementService {

	// Create
	Reimbursement createReimbursement(Reimbursement reimbursement);
		
	// Read
	Reimbursement getReimbursementById(int id);
	List<Reimbursement> getReimbursementsByEmployeeId(int id);
	List<Reimbursement> getReimbursementByUsername(String username);
	List<Reimbursement> getReimbursementByStatus(String status);
	List<Reimbursement> getAllReimbursements();
	List<Reimbursement> getReimbursementByEmployeeAndStatus(Employee employee, String status);	
	// Update
	Reimbursement updateReimbursement(Reimbursement reimbursement);
}
