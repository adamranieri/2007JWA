package dev.alsabea.services.impl;

import java.util.List;

import dev.alsabea.doas.EmployeeManagerDao;
import dev.alsabea.doas.impl.EmployeeManagerDaoImpl;
import dev.alsabea.entities.Employee_Manager;
import dev.alsabea.services.EmployeeManagerServices;

public class EmployeeManagerServicesImpl implements EmployeeManagerServices {
	
	
	private static EmployeeManagerServicesImpl ems;
	
	private static EmployeeManagerDao emd= EmployeeManagerDaoImpl.getInstance();
	
	
	public static EmployeeManagerServicesImpl getInstance() {
		
		if (ems==null) 
			ems= new EmployeeManagerServicesImpl();
		return ems;
		
	}
	
	

	@Override
	public int createInstance(Employee_Manager t) {
		
		return emd.createInstance(t);
	}

	@Override
	public Employee_Manager retrieveById(int key) {
		
		return emd.retrieveById(key);
	}

	@Override
	public boolean update(Employee_Manager t) {
		return emd.update(t);
	}

	@Override
	public boolean deleteById(int key) {
		
		return emd.deleteById(key);
	}

	@Override
	public List<Employee_Manager> retrieveAll() {
		return emd.retrieveAll();
	}

}
