package dev.alsabea.services;

import java.util.List;

import dev.alsabea.entities.Employee;

public interface EmployeeServices {

	
	long createInstance(Employee t);
	
	Employee retrieveById(long key);
	
	boolean update (Employee t);
	
	boolean deleteById (long key);
	
	List<Employee> retrieveAll();
}
