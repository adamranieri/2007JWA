package dev.alsabea.doas;

import java.util.List;

import dev.alsabea.entities.Manager;

public interface ManagerDao extends CRUD<Manager>{

	
	List<Manager> retrieveAll();
}
