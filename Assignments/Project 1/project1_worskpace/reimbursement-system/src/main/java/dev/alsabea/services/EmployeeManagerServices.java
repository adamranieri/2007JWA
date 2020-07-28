package dev.alsabea.services;

import java.util.List;

import dev.alsabea.entities.Employee_Manager;

public interface EmployeeManagerServices {

	
	int createInstance(Employee_Manager t);
	
	Employee_Manager retrieveById(int key);
	
	boolean update (Employee_Manager t);
	
	boolean deleteById (int key);
	
	List<Employee_Manager> retrieveAll();
}
