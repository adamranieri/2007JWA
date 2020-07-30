package dev.kurt.daos;

import java.util.List;

import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;

public interface EmployeeDAO {
	
	Employee createEmployee(Employee employee);
	
	Employee getEmployeeById(int id);
	List<Employee> getAllEmployees();
	Employee getEmployeeByLogin(String user, String pass);
	List<Employee> getEmployeesByManager(Manager manager);
	
	
	
	Employee updateEmployee(Employee employee);
	
	boolean deleteEmployee(Employee employee);
	
}
