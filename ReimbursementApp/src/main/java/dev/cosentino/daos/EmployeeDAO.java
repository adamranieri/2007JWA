package dev.cosentino.daos;

import java.util.List;
import java.util.Set;

import dev.cosentino.entities.Employee;
import dev.cosentino.entities.Reimbursement;

public interface EmployeeDAO {
	
	
	// Read
	List<Employee> getAllEmployees();
	Employee getEmployeeById(int id);
	Employee getEmployeeByUsername(String username);
	List<Employee> getEmployeeByName(String name);
		
	List<Employee> getAllManagers();
	
	// Update
	Employee updateEmployee(Employee employee);
	
}
