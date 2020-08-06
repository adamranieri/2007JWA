package dev.kusch.services;

import java.util.List;

import dev.kusch.daos.EmployeeDAO;
import dev.kusch.daos.EmployeeDAOHibernate;
import dev.kusch.dtos.LoginDTO;
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
	public Employee loginEmployee(LoginDTO login) {
		List<Employee> usr = edao.getEmployeeByUser(login.getUsername());
		List<Employee> pass = edao.getEmployeeByPass(login.getPassword());
		if (usr.size() == 0 || pass.size() == 0) {
			return null;
		}
		if (usr.get(0).getPassword().compareTo(login.getPassword()) == 0) {
			if (pass.get(0).getUsername().compareTo(login.getUsername()) == 0) {
				return usr.get(0);
			} else {
				return null;
			}
		} else {
			return null;
		}
		
	}

}
