package dev.nauman.services;

import java.util.List;


import dev.nauman.daos.ManagerDAO;
import dev.nauman.daos.ManagerDAOImpl;
import dev.nauman.entities.Manager;

public class ManagerServiceImpl implements ManagerService{

	
	private ManagerDAO mdao = ManagerDAOImpl.getManagerDAOImpl();
	private static ManagerServiceImpl mserv = null;
	private ManagerServiceImpl() {
	}
	
	public static ManagerServiceImpl getManagerServiceImpl() {
		if(mserv ==null) {
			mserv = new ManagerServiceImpl();
		}
		return mserv;
	}
	@Override
	public Manager getManagerById(int mId) {
		return mdao.getManagerById(mId);
	}

	@Override
	public List<Manager> getAllManagers() {
		return mdao.getAllManagers();
	}

	@Override
	public Manager updateManager(Manager manager) {
		return mdao.updateManager(manager);
	}

	@Override
	public Manager loginManager(String username, String password) {
		
		List<Manager> managers = this.getAllManagers();
		for(Manager manager : managers) {
			if(manager.getUsername().equals(username) && manager.getPassword().equals(password)) {

				return manager;
			}
		}

		
		return null;
	}

}
