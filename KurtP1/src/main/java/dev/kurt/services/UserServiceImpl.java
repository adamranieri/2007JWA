package dev.kurt.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.kurt.daos.EmployeeDAO;
import dev.kurt.daos.EmployeeDAOHibernate;
import dev.kurt.daos.ManagerDAO;
import dev.kurt.daos.ManagerDAOHibernate;
import dev.kurt.dtos.LoginDTO;
import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;
import dev.kurt.entities.User;
import dev.kurt.exceptions.InvalidLoginException;

public class UserServiceImpl implements UserService {
	
	public ManagerDAO manDao = new ManagerDAOHibernate();
	public EmployeeDAO empDao = new EmployeeDAOHibernate();
	
	public UserServiceImpl() {
		super();
	}
	
	public UserServiceImpl(ManagerDAO manDao, EmployeeDAO empDao) {
		super();
		this.manDao = manDao;
		this.empDao = empDao;
	}
	
	@Override
	public User loginUser(LoginDTO dto) throws InvalidLoginException {
		List<Employee> empUsers = empDao.getAllEmployees();
		List<Manager> manUsers = manDao.getAllManagers();
		
		for(Employee e : empUsers) {
			if(e.getEmpUsername().equals(dto.getUsername()) && e.getEmpPassword().equals(dto.getPassword())) {
				return empDao.getEmployeeByLogin(e.getEmpUsername(), e.getEmpPassword());
			}
		}
		
		for(Manager m : manUsers) {
			if(m.getManUsername().equals(dto.getUsername()) && m.getManPassword().equals(dto.getPassword())) {
				return manDao.getManagerByLogin(m.getManUsername(), m.getManPassword());
			}
		}
				
		throw new InvalidLoginException();
	}

}
