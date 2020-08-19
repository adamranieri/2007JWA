package dev.nauman.services;

import java.util.List;

import dev.nauman.entities.Employee;
import dev.nauman.entities.Reimbursement;

public interface ReimbursementService {

	Reimbursement submitReimbursement(Reimbursement reimbursement);
	
	Reimbursement getReimbursementById(int rId);
	List<Reimbursement> getAllReimbursements();
	List<Reimbursement> getAllAcendingByItemReimbursements(boolean ascending);
	List<Reimbursement> getAllAcendingByStatusReimbursements(boolean ascending);
	List<Reimbursement> getAllAcendingByCategoryReimbursements(boolean ascending);
	List<Reimbursement> getAllAcendingByAmountReimbursements(boolean ascending);
	List<Reimbursement> getAllAcendingByEmployeeReimbursements(boolean ascending);
	List<Reimbursement> getAllAcendingByNoteReimbursements(boolean ascending);
	List<Reimbursement> getAllAcendingByDateReimbursements(boolean ascending);
	
	List<Reimbursement> getAllEmployeeReimbursements(int eId);
	List<Reimbursement> getAllEmployeeReimbursements(Employee employee);

	Reimbursement updateReimbursement(Reimbursement reimbursement);
	
}
