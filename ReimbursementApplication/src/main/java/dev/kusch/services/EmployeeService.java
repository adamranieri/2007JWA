package dev.kusch.services;

import java.util.List;

import dev.kusch.entities.Employee;

public interface EmployeeService {
	
	Employee getEmployeeById(int id);
	List<Employee> getEmployeeByUser(String user);
	
	Employee updateUsername(Employee employee, String oldUser);
	Employee updatePassword(Employee employee);

	boolean loginEmployee(String username, String password);
}
