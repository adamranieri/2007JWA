package dev.kusch.daos;

import java.util.List;

import dev.kusch.entities.Employee;

public interface EmployeeDAO {
	
	// This API will not allow creates
	
	// READ operation
	Employee getEmployeeById(int id);
	List<Employee> getEmployeeByUser(String username);
	List<Employee> getEmployeeByPass(String password);
	
	// UPDATE operation
	Employee updateEmployee(Employee employee);
	
	// Because we are not allowing create, we aren't allowing delete either

}
