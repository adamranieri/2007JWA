package dev.alsabea.doas;

import java.util.List;

import dev.alsabea.entities.Employee;
import dev.alsabea.entities.ReimbursementRequest;

public interface EmployeeDao extends CRUD<Employee>{

	List<Employee> retrieveAll();
	
}
