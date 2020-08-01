package dev.kusch.services;

import java.util.List;

import dev.kusch.daos.EmployeeDAO;
import dev.kusch.daos.EmployeeDAOHibernate;
import dev.kusch.entities.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	private static EmployeeDAO edao = EmployeeDAOHibernate.getEmployeeDAOHibernate();
	
	@Override
	public Employee getEmployeeById(int id) {
		return edao.getEmployeeById(id);
	}

	@Override
	public List<Employee> getEmployeeByUser(String user) {
		return edao.getEmployeeByUser(user);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return edao.updateEmployee(employee);
	}

	@Override
	public boolean loginEmployee(String username, String password) {
		List<Employee> usr = edao.getEmployeeByUser(username);
		List<Employee> pass = edao.getEmployeeByPass(password);
		if (usr.size() == 0 || pass.size() == 0) {
			return false;
		}
		if (usr.get(0).getPassword().compareTo(password) == 0) {
			if (pass.get(0).getUsername().compareTo(username) == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}

}
