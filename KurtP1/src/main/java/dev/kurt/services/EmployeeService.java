package dev.kurt.services;

import java.util.List;

import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;
import dev.kurt.entities.Reimbursement;

public interface EmployeeService {
	
	Employee createEmployee(Employee employee);
	
	Employee getEmployeeById(int id);
	Employee getEmployeeByLogin(String user, String pass);
	List<Employee> getAllEmployees();
	List<Employee> getEmployeesByManager(Manager manager);
	
	Employee updateEmployee(Employee employee);
	Employee addReimbursementToEmployee(Employee employee, Reimbursement reimbursement);
	
	boolean deleteEmployee(Employee employee);
}
