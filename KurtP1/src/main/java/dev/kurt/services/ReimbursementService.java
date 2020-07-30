package dev.kurt.services;

import java.util.List;

import dev.kurt.entities.Employee;
import dev.kurt.entities.Reimbursement;

public interface ReimbursementService {
	
	Reimbursement createReimbursement(Reimbursement reimbursement);
	
	Reimbursement getReimbursementById(int id);
	List<Reimbursement> getAllReimbursements();
	List<Reimbursement> getReimbursementsByEmployee(Employee employee);
	
	Reimbursement updateReimbursement(Reimbursement reimbursement);
	Reimbursement addStatusUpdateToReimbursement(Reimbursement reimbursement, String status, String notes);
	
	boolean deleteReimbursement(Reimbursement reimbursement);
	
}
