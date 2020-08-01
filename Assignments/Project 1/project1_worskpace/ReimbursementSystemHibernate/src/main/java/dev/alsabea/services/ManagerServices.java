package dev.alsabea.services;

import dev.alsabea.entities.Manager;

public interface ManagerServices extends CRUD<Manager> {

	
	Manager retrieveByUsernameAndPassword(String username, String password);
}
