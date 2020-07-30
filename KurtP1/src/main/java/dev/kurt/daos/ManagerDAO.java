package dev.kurt.daos;

import java.util.List;

import dev.kurt.entities.Manager;

public interface ManagerDAO {
	
	Manager createManager(Manager manager);
	
	Manager getManagerById(int id);
	Manager getManagerByLogin(String username, String password);
	List<Manager> getAllManagers();
	
	Manager updateManager(Manager manager);
	
	boolean deleteManager(Manager manager);

}
