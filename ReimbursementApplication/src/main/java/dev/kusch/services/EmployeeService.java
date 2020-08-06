package dev.kusch.services;

import java.util.List;

import dev.kusch.dtos.LoginDTO;
import dev.kusch.entities.Employee;

public interface EmployeeService {
	
	Employee getEmployeeById(int id);
	List<Employee> getEmployeeByUser(String user);

	Employee updateEmployee(Employee employee);

	Employee loginEmployee(LoginDTO login);
}
