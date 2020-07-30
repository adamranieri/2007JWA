package dev.kurt.daos;

import java.util.List;

import dev.kurt.entities.Employee;
import dev.kurt.entities.Reimbursement;

public interface ReimbursementDAO {
	Reimbursement createReimbursement(Reimbursement reimbursement);
	
	Reimbursement getReimbursementById(int id);
	List<Reimbursement> getAllReimbursements();
	List<Reimbursement> getEmployeeReimbursements(Employee employee);

	
	Reimbursement updateReimbursement(Reimbursement reimbursement);
	
	boolean deleteReimbursement(Reimbursement reimbursement);
}
