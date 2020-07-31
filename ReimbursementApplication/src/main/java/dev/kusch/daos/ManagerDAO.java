package dev.kusch.daos;

import java.util.List;

import dev.kusch.entities.Manager;

public interface ManagerDAO {

	// This API will not allow creates

	// READ operation
	Manager getManagerById(int id);
	List<Manager> getManagerByUser(String username);
	List<Manager> getManagerByPass(String password);

	// UPDATE operation
	Manager updateManager(Manager manager);

	// Because we are not allowing create, we aren't allowing delete either

}
