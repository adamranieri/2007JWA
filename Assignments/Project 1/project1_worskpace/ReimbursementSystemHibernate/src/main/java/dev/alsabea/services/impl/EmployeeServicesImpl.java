package dev.alsabea.services.impl;

import java.util.List;

import dev.alsabea.doas.EmployeeDao;
import dev.alsabea.doas.impl.EmployeeDaoImpl;
import dev.alsabea.entities.Employee;
import dev.alsabea.services.EmployeeServices;

public class EmployeeServicesImpl implements EmployeeServices {
	
	
	private static EmployeeServicesImpl ems;
	
	private static EmployeeDao emd= EmployeeDaoImpl.getInstance();
	
	
	public static EmployeeServicesImpl getInstance() {
		
		if (ems==null) 
			ems= new EmployeeServicesImpl();
		return ems;
		
	}
	
	

	@Override
	public long createInstance(Employee t) {
		
		return emd.createInstance(t);
	}

	@Override
	public Employee retrieveById(long key) {
		
		return emd.retrieveById(key);
	}

	@Override
	public boolean update(Employee t) {
		return emd.update(t);
	}

	@Override
	public boolean deleteById(long key) {
		
		return emd.deleteById(key);
	}

	@Override
	public List<Employee> retrieveAll() {
		return emd.retrieveAll();
	}

}
