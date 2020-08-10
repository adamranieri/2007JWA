package dev.cosentino.daos;

import java.util.List;

import dev.cosentino.entities.Reimbursement;

public interface ReimbursementDAO {

	// Create
	Reimbursement createReimbursement(Reimbursement reimbursement);
	
	// Read
	Reimbursement getReimbursementById(int id);
	List<Reimbursement> getReimbursementsByEmployeeId(int id);
	List<Reimbursement> getReimbursementByUsername(String username);
	List<Reimbursement> getReimbursementByStatus(String status);
	List<Reimbursement> getAllReimbursements();
	// Update
	Reimbursement updateReimbursement(Reimbursement reimbursement);
}
