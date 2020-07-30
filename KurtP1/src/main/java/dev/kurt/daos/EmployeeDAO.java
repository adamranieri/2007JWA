package dev.kurt.daos;

import java.util.List;

import dev.kurt.entities.Employee;

public interface EmployeeDAO {
	
	Employee createEmployee(Employee employee);
	
	Employee getEmployeeById(int id);
	List<Employee> getAllEmployees();
	Employee getEmployeeByLogin(String user, String pass);
	
	
	
	Employee updateEmployee(Employee employee);
	
	boolean deleteEmployee(Employee employee);
	
}
