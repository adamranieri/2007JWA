package dev.kusch.services;

import java.util.List;

import dev.kusch.daos.ManagerDAO;
import dev.kusch.daos.ManagerDAOHibernate;
import dev.kusch.entities.Manager;
import dev.kusch.entities.Manager;

public class ManagerServiceImpl implements ManagerService {

	private static ManagerDAO mdao = ManagerDAOHibernate.getManagerDAOHibernate();
	
	@Override
	public Manager getManagerById(int id) {
		return mdao.getManagerById(id);
	}

	@Override
	public List<Manager> getManagerByUser(String user) {
		return mdao.getManagerByUser(user);
	}

	@Override
	public Manager updateUsername(Manager manager, String oldUser) {
		return mdao.updateManager(manager);
	}

	@Override
	public Manager updatePassword(Manager manager) {
		return mdao.updateManager(manager);
	}

	@Override
	public boolean loginManager(String username, String password) {
		List<Manager> usr = mdao.getManagerByUser(username);
		List<Manager> pass = mdao.getManagerByPass(password);
		if (usr.size() == 0 || pass.size() == 0) {
			return false;
		}
		if (usr.get(0).getPassword().compareTo(password) == 0) {
			if (pass.get(0).getUsername().compareTo(username) == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}
}
