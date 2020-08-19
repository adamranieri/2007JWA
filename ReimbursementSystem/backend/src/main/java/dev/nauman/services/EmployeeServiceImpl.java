package dev.nauman.services;

import java.util.List;
import dev.nauman.daos.EmployeeDAO;
import dev.nauman.daos.EmployeeDAOImpl;
import dev.nauman.entities.Employee;

public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeDAO edao = EmployeeDAOImpl.getEmployeeDAOImpl();
	private static EmployeeServiceImpl eserv = null;
	private EmployeeServiceImpl() {
	}
	
	public static EmployeeServiceImpl getEmployeeServiceImpl() {
		if(eserv ==null) {
			eserv = new EmployeeServiceImpl();
		}
		return eserv;
	}
	@Override
	public Employee getEmployeeById(int eId) {
		return edao.getEmployeeById(eId);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return edao.getAllEmployees();
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return edao.updateEmployee(employee);
	}

	@Override
	public List<Employee> getAllManagers() {
		return edao.getAllManagers();
	}

	@Override
	public Employee login(String username, String password) {
		List<Employee> employees = this.getAllEmployees();
		for(Employee employee : employees) {
			if(employee.getPassword().equals(password) && employee.getUsername().equals(username)) {
					return employee;
			}
		}
		return null;
	}
	
	

}
