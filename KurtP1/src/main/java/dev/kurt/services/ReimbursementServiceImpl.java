package dev.kurt.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dev.kurt.daos.ReimbursementDAO;
import dev.kurt.daos.ReimbursementDAOHibernate;
import dev.kurt.entities.Employee;
import dev.kurt.entities.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService{

	private ReimbursementDAO serDao;
	
	public ReimbursementServiceImpl() {
		super();
		this.serDao = new ReimbursementDAOHibernate();
	}
	
	public ReimbursementServiceImpl(ReimbursementDAO serDao) {
		super();
		this.serDao = serDao;
	}
	
	@Override
	public Reimbursement createReimbursement(Reimbursement reimbursement) {
		reimbursement.setStatus("Pending");
		Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy 'at' hh:mm:ss a z");  
        String strDate = dateFormat.format(date);  
        reimbursement.setSubmitDate(strDate);
		return this.serDao.createReimbursement(reimbursement);
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		return this.serDao.getReimbursementById(id);
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		return this.serDao.getAllReimbursements();
	}

	@Override
	public List<Reimbursement> getReimbursementsByEmployee(Employee employee) {
		return this.serDao.getEmployeeReimbursements(employee);
	}

	@Override
	public Reimbursement updateReimbursement(Reimbursement reimbursement) {
		Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy 'at' hh:mm:ss a z");  
        String strDate = dateFormat.format(date);  
        reimbursement.setStatusDate(strDate);
		return this.serDao.updateReimbursement(reimbursement);
	}

	@Override
	public Reimbursement addStatusUpdateToReimbursement(Reimbursement reimbursement, String status, String notes) {
		Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd 'at' hh:mm:ss z");  
        String strDate = dateFormat.format(date);  
        
        reimbursement.setStatusDate(strDate);
        reimbursement.setStatus(status);
        reimbursement.setNotes(notes);
        
		return this.serDao.updateReimbursement(reimbursement);
	}

	@Override
	public boolean deleteReimbursement(Reimbursement reimbursement) {
		return this.serDao.deleteReimbursement(reimbursement);
	}

}
