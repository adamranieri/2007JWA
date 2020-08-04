package dev.alsabea.services.impl;

import dev.alsabea.doas.EmployeeDao;
import dev.alsabea.doas.ReimbursementRequestDao;
import dev.alsabea.doas.impl.EmployeeDaoImpl;
import dev.alsabea.doas.impl.ReimbursementRequestDaoImpl;
import dev.alsabea.entities.Employee;
import dev.alsabea.services.EmployeeServices;

public class EmployeeServicesImpl implements EmployeeServices {
	
	
	private static EmployeeServicesImpl ems;
	
	private static EmployeeDao eDao= EmployeeDaoImpl.getInstance();
	private static ReimbursementRequestDao rrDao= ReimbursementRequestDaoImpl.getInstance();
	
	
	public static EmployeeServicesImpl getInstance() {
		
		if (ems==null) 
			ems= new EmployeeServicesImpl();
		return ems;
		
	}
	
	

	@Override
	public long createInstance(Employee t) {
		
		return eDao.createInstance(t);
	}

	@Override
	public Employee retrieveById(long key) {
		
		return eDao.retrieveById(key);
	}
	
	@Override
	public Employee retrieveByUsernameAndPassword(String username, String password) {
		
		return eDao.retrieveByUsernameAndPassword(username, password);
	}

	@Override
	public boolean update(Employee t) {
		return eDao.update(t);
	}

	@Override
	public boolean deleteById(long key) {
		
		return eDao.deleteById(key);
	}





}
