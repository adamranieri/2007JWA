package dev.kurt.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dev.kurt.daos.EmployeeDAO;
import dev.kurt.daos.EmployeeDAOHibernate;
import dev.kurt.daos.ReimbursementDAO;
import dev.kurt.daos.ReimbursementDAOHibernate;
import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;
import dev.kurt.entities.Reimbursement;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO eDao;
	private ReimbursementDAO reiDao;
	
	
	public EmployeeServiceImpl() {
		super();
		this.eDao = new EmployeeDAOHibernate();
		this.reiDao = new ReimbursementDAOHibernate();
	}
	
	public EmployeeServiceImpl(EmployeeDAO eDao, ReimbursementDAO reiDao) {
		super();
		this.eDao = eDao;
		this.reiDao = reiDao;
	}
	
	@Override
	public Employee createEmployee(Employee employee) {
		return this.eDao.createEmployee(employee);
	}

	@Override
	public Employee getEmployeeById(int id) {
		return this.eDao.getEmployeeById(id);
	}

	@Override
	public Employee getEmployeeByLogin(String user, String pass) {
		return this.eDao.getEmployeeByLogin(user, pass);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return this.eDao.getAllEmployees();
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return this.eDao.updateEmployee(employee);
	}

	@Override
	public Employee addReimbursementToEmployee(Employee employee, Reimbursement reimbursement) {
		reimbursement.setEmployee(employee);
		reimbursement.setStatus("Pending");
		Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd 'at' hh:mm:ss z");  
        String strDate = dateFormat.format(date);  
        reimbursement.setSubmitDate(strDate);
		employee.getReimbursements().add(this.reiDao.createReimbursement(reimbursement));
		return this.eDao.updateEmployee(employee);
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		return this.eDao.deleteEmployee(employee);
	}

	@Override
	public List<Employee> getEmployeesByManager(Manager manager) {
		return this.eDao.getEmployeesByManager(manager);
	}
	

}
