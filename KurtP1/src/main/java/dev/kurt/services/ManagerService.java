package dev.kurt.services;

import java.util.List;

import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;

public interface ManagerService {
	
	Manager createManager(Manager manager);
	
	Manager getManagerById(int id);
	Manager getManagerByLogin(String user, String pass);
	List<Manager> getAllManagers();
	
	Manager updateManager(Manager manager);
	Manager addEmployeeToManager(Manager manager, Employee employee);
	
	boolean deleteManager(Manager manager);
	
	

}
