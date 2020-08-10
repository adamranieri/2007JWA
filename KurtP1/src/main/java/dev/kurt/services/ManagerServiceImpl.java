package dev.kurt.services;

import java.util.List;

import dev.kurt.daos.EmployeeDAO;
import dev.kurt.daos.EmployeeDAOHibernate;
import dev.kurt.daos.ManagerDAO;
import dev.kurt.daos.ManagerDAOHibernate;
import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;

public class ManagerServiceImpl implements ManagerService {

	private ManagerDAO manDao; 
	private EmployeeDAO empDao;
	
	public ManagerServiceImpl() {
		super();
		this.manDao = new ManagerDAOHibernate();
		this.empDao = new EmployeeDAOHibernate();
	}
	
	public ManagerServiceImpl(ManagerDAO manDao, EmployeeDAO empDao) {
		super();
		this.manDao = manDao;
		this.empDao = empDao;
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

	@Override
	public Manager addEmployeeToManager(Manager manager, Employee employee) {
		employee.setManager(manager);
		manager.getEmployees().add(this.empDao.createEmployee(employee));
		return manager;
	}

	
	

}
