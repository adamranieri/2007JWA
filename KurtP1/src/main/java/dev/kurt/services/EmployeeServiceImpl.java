package dev.kurt.services;

import java.util.List;

import dev.kurt.daos.EmployeeDAO;
import dev.kurt.daos.EmployeeDAOHibernate;
import dev.kurt.entities.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	
	public EmployeeDAO eDao = new EmployeeDAOHibernate();
	
	public EmployeeServiceImpl() {
		super();
	}
	
	public EmployeeServiceImpl(EmployeeDAO eDao) {
		super();
		this.eDao = eDao;
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
	public boolean deleteEmployee(Employee employee) {
		return this.eDao.deleteEmployee(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
