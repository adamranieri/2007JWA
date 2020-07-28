package dev.alsabea.doas;

import java.util.List;

import dev.alsabea.entities.Employee_Manager;

public interface EmployeeManagerDao extends CRUD<Employee_Manager>{

	List<Employee_Manager> retrieveAll();
}
