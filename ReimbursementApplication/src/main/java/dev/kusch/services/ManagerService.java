package dev.kusch.services;

import java.util.List;

import dev.kusch.entities.Manager;

public interface ManagerService {
	
	Manager getManagerById(int id);
	List<Manager> getManagerByUser(String user);
	
	Manager updateUsername(Manager manager, String oldUser);
	Manager updatePassword(Manager manager);
	
	boolean loginManager(String username, String password);

}
