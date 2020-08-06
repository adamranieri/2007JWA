package dev.kusch.services;

import java.util.List;

import dev.kusch.daos.ManagerDAO;
import dev.kusch.daos.ManagerDAOHibernate;
import dev.kusch.dtos.LoginDTO;
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
	public Manager updateManager(Manager manager) {
		return mdao.updateManager(manager);
	}

	@Override
	public Manager loginManager(LoginDTO login) {
		List<Manager> usr = mdao.getManagerByUser(login.getUsername());
		List<Manager> pass = mdao.getManagerByPass(login.getPassword());
		if (usr.size() == 0 || pass.size() == 0) {
			return null;
		}
		if (usr.get(0).getPassword().compareTo(login.getPassword()) == 0) {
			if (pass.get(0).getUsername().compareTo(login.getUsername()) == 0) {
				return usr.get(0);
			} else {
				return null;
			}
		} else {
			return null;
		}
		
	}
}
