package dev.nauman.daos;

import java.util.List;

import dev.nauman.entities.Employee;
import dev.nauman.entities.Manager;

public interface EmployeeDAO {

	Employee getEmployeeById(int id);
	List<Employee> getAllEmployees();
	List<Employee> getAllManagers();
	
	Employee updateEmployee(Employee employee);
	
	
	
	
}
