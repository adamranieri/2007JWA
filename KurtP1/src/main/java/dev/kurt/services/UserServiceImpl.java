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
	public User loginUser(LoginDTO dto) {
		List<Employee> empUsers = empDao.getAllEmployees();
		List<Manager> manUsers = manDao.getAllManagers();
		
		for(Employee e : empUsers) {
			if(e.getEmpUsername().equals(dto.getUsername()) && e.getEmpPassword().equals(dto.getPassword())) {
				return empDao.getEmployeeByLogin(e.getEmpUsername(), e.getEmpPassword());
			}
		}
		
		for(Manager m : manUsers) {
			if(m.getManUsername().equals(dto.getUsername()) && m.getManPassword().equals(dto.getPassword())) {
				System.out.println("if you're reading this you fixed it");
				return manDao.getManagerByLogin(m.getManUsername(), m.getManPassword());
			}
		}
				
		return null;
	}

}
