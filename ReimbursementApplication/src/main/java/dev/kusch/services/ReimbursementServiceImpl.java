package dev.kusch.services;

import java.util.List;

import dev.kusch.daos.ReimbursementDAO;
import dev.kusch.daos.ReimbursementDAOHibernate;
import dev.kusch.entities.Employee;
import dev.kusch.entities.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService {

	private static ReimbursementDAO rdao = ReimbursementDAOHibernate.getReimbursementDAOHibernate();
	
	@Override
	public Reimbursement createReimbursement(Reimbursement reimbursement) {
		return rdao.createReimbursement(reimbursement);
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		return rdao.getAllReimbursements();
	}

	@Override
	public Reimbursement getReimbursement(int id) {
		return rdao.getReimbursementById(id);
	}

	@Override
	public List<Reimbursement> getReimbursementsByEmployee(Employee employee) {
		return rdao.getReimbursementsByEmployee(employee);
	}

	@Override
	public Reimbursement updateReimbursement(Reimbursement reimbursement) {
		return rdao.updateReimbursement(reimbursement);
	}

	@Override
	public boolean deleteReimbursement(Reimbursement reimbursement) {
		return rdao.deleteReimbursement(reimbursement);
	}

}
