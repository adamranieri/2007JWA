package dev.kusch.daos;

import java.util.List;
import java.util.Set;

import dev.kusch.entities.Employee;
import dev.kusch.entities.Reimbursement;

public interface ReimbursementDAO {
	
	// CREATE
	Reimbursement createReimbursement(Reimbursement reimbursement);

	// READ
	List<Reimbursement> getAllReimbursements();
	List<Reimbursement> getReimbursementsByEmployee(Employee employee);
	Reimbursement getReimbursementById(int id);
	
	// UPDATE
	Reimbursement updateReimbursement(Reimbursement reimbursement);
	
	// DELETE
	boolean deleteReimbursement(Reimbursement reimbursement);	
}
