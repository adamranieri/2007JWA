package dev.alsabea.doas;

import dev.alsabea.entities.Manager;

public interface ManagerDao extends CRUD<Manager>{

	Manager retrieveByUsernameAndPassword(String username, String password);
}
