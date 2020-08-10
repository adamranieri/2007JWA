package dev.cosentino.services;

import java.util.List;

import dev.cosentino.daos.EmployeeDAO;
import dev.cosentino.daos.EmployeeDAOhibernate;
import dev.cosentino.entities.Employee;

public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeDAO edao;
	
	public EmployeeServiceImpl() {
		super();
		this.edao = EmployeeDAOhibernate.getEmployeeDAOhibernate();
	}
	 
	public EmployeeServiceImpl(EmployeeDAO edao) {
		super();
		this.edao = edao;
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return this.edao.getAllEmployees();
	}

	@Override
	public Employee getEmployeeById(int id) {
		return this.edao.getEmployeeById(id);
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		return this.edao.getEmployeeByUsername(username);
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		return this.edao.getEmployeeByName(name);
	}

	@Override
	public List<Employee> getAllManagers() {
		return this.edao.getAllManagers();
	}

	@Override
	public Employee updatePassword(Employee employee, String password) {
		employee.setPassword(password);
		this.edao.updateEmployee(employee);
		return employee;
	}

	@Override
	public Employee updateUsername(Employee employee, String username) {
		employee.setUsername(username);
		this.edao.updateEmployee(employee);
		return employee;
	}

	@Override
	public Employee updateTitle(Employee employee, String title) {
		employee.setTitle(title);
		this.edao.updateEmployee(employee);
		return employee;
	}

}
