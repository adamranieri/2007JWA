package dev.kurt.services;

import java.util.List;

import dev.kurt.entities.Employee;

public interface EmployeeService {
	
	Employee createEmployee(Employee employee);
	
	Employee getEmployeeById(int id);
	Employee getEmployeeByLogin(String user, String pass);
	List<Employee> getAllEmployees();
	
	boolean deleteEmployee(Employee employee);
}
