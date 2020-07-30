package dev.kurt.services;

import java.util.List;

import dev.kurt.daos.ManagerDAO;
import dev.kurt.daos.ManagerDAOHibernate;
import dev.kurt.entities.Manager;

public class ManagerServiceImpl implements ManagerService {

	public ManagerDAO manDao = new ManagerDAOHibernate();
	
	public ManagerServiceImpl() {
		super();
	}
	
	public ManagerServiceImpl(ManagerDAO manDao) {
		super();
		this.manDao = manDao;
	}
	
	
	@Override
	public Manager createManager(Manager manager) {
		return this.manDao.createManager(manager);
	}

	@Override
	public Manager getManagerById(int id) {
		return this.manDao.getManagerById(id);
	}

	@Override
	public Manager getManagerByLogin(String user, String pass) {
		return this.manDao.getManagerByLogin(user, pass);
	}

	@Override
	public List<Manager> getAllManagers() {
		return this.manDao.getAllManagers();
	}

	@Override
	public Manager updateManager(Manager manager) {
		return this.manDao.updateManager(manager);
	}

	@Override
	public boolean deleteManager(Manager manager) {
		return this.manDao.deleteManager(manager);
	}

	
	

}
