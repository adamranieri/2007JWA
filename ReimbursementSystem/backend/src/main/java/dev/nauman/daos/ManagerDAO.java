package dev.nauman.daos;

import java.util.List;

import dev.nauman.entities.Manager;

public interface ManagerDAO {

	Manager getManagerById(int mId);
	List<Manager> getAllManagers();
	
	Manager updateManager(Manager manager);
}
