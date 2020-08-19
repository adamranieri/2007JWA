package dev.nauman.services;

import java.util.List;

import dev.nauman.entities.Manager;

public interface ManagerService {

	Manager getManagerById(int mId);
	List<Manager> getAllManagers();
	
	Manager updateManager(Manager manager);
	
	Manager loginManager(String username, String password);
}
