package dev.alsabea.doas;

import dev.alsabea.entities.Employee;

public interface EmployeeDao extends CRUD<Employee>{

	Employee retrieveByUsernameAndPassword(String username, String password);
}
