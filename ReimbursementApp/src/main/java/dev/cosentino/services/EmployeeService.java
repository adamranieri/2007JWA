package dev.cosentino.services;

import java.util.List;

import dev.cosentino.entities.Employee;

public interface EmployeeService {

	// Read
	List<Employee> getAllEmployees();
	Employee getEmployeeById(int id);
	Employee getEmployeeByUsername(String username);
	List<Employee> getEmployeeByName(String name);
		
	List<Employee> getAllManagers();
			
	Employee updatePassword(Employee employee, String password);
	Employee updateUsername(Employee employee, String username);
	Employee updateTitle(Employee employee, String title);
}