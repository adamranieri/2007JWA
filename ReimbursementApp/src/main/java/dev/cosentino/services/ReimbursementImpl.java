package dev.cosentino.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import dev.cosentino.daos.EmployeeDAO;
import dev.cosentino.daos.EmployeeDAOhibernate;
import dev.cosentino.daos.ReimbursementDAO;
import dev.cosentino.daos.ReimbursementDAOhibernate;
import dev.cosentino.entities.Employee;
import dev.cosentino.entities.Reimbursement;

public class ReimbursementImpl implements ReimbursementService{

	private ReimbursementDAO rdao;
	private EmployeeDAO edao;
	
	public ReimbursementImpl() {
		super();
		this.rdao = ReimbursementDAOhibernate.getReimbursementDAOhibernate();
		this.edao = EmployeeDAOhibernate.getEmployeeDAOhibernate();
	}
	
	public ReimbursementImpl(ReimbursementDAO rdao, EmployeeDAO edao) {
		super();
		this.rdao = rdao;
		this.edao = edao;
	}
	
	@Override
	public Reimbursement createReimbursement(Reimbursement reimbursement) {
		return this.rdao.createReimbursement(reimbursement);
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		return this.rdao.getReimbursementById(id);
	}

	@Override
	public List<Reimbursement> getReimbursementsByEmployeeId(int id) {
		return this.rdao.getReimbursementsByEmployeeId(id);
	}

	@Override
	public List<Reimbursement> getReimbursementByUsername(String username) {
		return this.rdao.getReimbursementByUsername(username);
	}

	@Override
	public List<Reimbursement> getReimbursementByStatus(String status) {
		return this.rdao.getReimbursementByStatus(status);
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		return this.rdao.getAllReimbursements();
	}

	@Override
	public Reimbursement updateReimbursement(Reimbursement reimbursement) {
		return this.rdao.updateReimbursement(reimbursement);
	}

	@Override
	public List<Reimbursement> getReimbursementByEmployeeAndStatus(Employee employee, String status) {
		List<Reimbursement> reims = rdao.getReimbursementsByEmployeeId(employee.geteId());
		List<Reimbursement> reimStatus = new ArrayList<>();
		for(int i = 0; i < reims.size(); i++) {
			if(reims.get(i).getStatus().equals(status)) {
				reimStatus.add(reims.get(i));
			}
		}
		
		return reimStatus;
	}

}
