package dev.nauman.daos;

import java.util.List;

import dev.nauman.entities.Reimbursement;

public interface ReimbursementDAO {

	Reimbursement createReimbursement(Reimbursement reimbursement);
	
	Reimbursement getReimbursementById(int rId);
	List<Reimbursement> getAllReimbursements();
	
	Reimbursement updateReimbursement(Reimbursement reimbursement);
	
}
