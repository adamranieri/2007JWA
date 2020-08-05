package dev.kurt.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dev.kurt.daos.EmployeeDAOHibernate;
import dev.kurt.daos.ManagerDAOHibernate;
import dev.kurt.daos.ReimbursementDAOHibernate;
import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;
import dev.kurt.entities.User;
import dev.kurt.exceptions.InvalidLoginException;
import dev.kurt.services.EmployeeService;
import dev.kurt.services.EmployeeServiceImpl;
import dev.kurt.services.ManagerService;
import dev.kurt.services.ManagerServiceImpl;
import dev.kurt.services.UserService;
import dev.kurt.services.UserServiceImpl;

class UserServiceTests {
	
	private static UserService uServ = new UserServiceImpl();
	private static ManagerService manServ = new ManagerServiceImpl(new ManagerDAOHibernate(), new EmployeeDAOHibernate());
	private static EmployeeService empServ = new EmployeeServiceImpl(new EmployeeDAOHibernate(), new ReimbursementDAOHibernate());
	private static Employee employee = new Employee(0,"kurt1997","pword","kurt","dawiec");
	private static Manager michael = new Manager(0,"gretzky@email.com","number1boss","Michael","Scott");
	
	
	@Test
	void test() throws InvalidLoginException {
		manServ.createManager(michael);
		empServ.createEmployee(employee);
		User user = uServ.loginUser(null);
		
		
		System.out.println(user.toString().charAt(0));
	}

}
