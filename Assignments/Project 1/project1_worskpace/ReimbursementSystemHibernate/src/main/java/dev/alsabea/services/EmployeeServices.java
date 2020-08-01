package dev.alsabea.services;

import dev.alsabea.entities.Employee;

public interface EmployeeServices extends CRUD<Employee> {

	
	Employee retrieveByUsernameAndPassword(String username, String password);
}
