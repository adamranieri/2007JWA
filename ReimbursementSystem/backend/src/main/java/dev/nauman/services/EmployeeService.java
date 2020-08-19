package dev.nauman.services;

import java.util.List;

import dev.nauman.entities.Employee;
import dev.nauman.entities.Manager;

public interface EmployeeService {

	Employee getEmployeeById(int eId);
	List<Employee> getAllEmployees();
	List<Employee> getAllManagers();
	
	Employee updateEmployee(Employee employee);
	
	Employee login(String username, String password);
	

}
