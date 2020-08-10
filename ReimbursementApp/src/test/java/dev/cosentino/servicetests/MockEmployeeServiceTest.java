package dev.cosentino.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.cosentino.daos.EmployeeDAO;
import dev.cosentino.entities.Employee;
import dev.cosentino.services.EmployeeService;
import dev.cosentino.services.EmployeeServiceImpl;

class MockEmployeeServiceTest {

	@Test 
	void updatePassword() { 
		Employee emp = new Employee(0, "FakeName", "FakeUserName","fakepassword","Employee");
	
		EmployeeDAO edao = Mockito.mock(EmployeeDAO.class);
		Mockito.when(edao.getEmployeeById(1)).thenReturn(emp);
		
		EmployeeService eserv = new EmployeeServiceImpl(edao);
		
		eserv.updatePassword(emp, "newpassword");
		Assertions.assertEquals("newpassword", emp.getPassword());
	}
	
	@Test
	void updateUsername() {
		Employee emp = new Employee(0, "FakeName", "FakeUserName","fakepassword","Employee");
		
		EmployeeDAO edao = Mockito.mock(EmployeeDAO.class);
		Mockito.when(edao.getEmployeeById(1)).thenReturn(emp);
		
		EmployeeService eserv = new EmployeeServiceImpl(edao);
		
		eserv.updateUsername(emp, "newUsername");
		Assertions.assertEquals("newUsername", emp.getUsername());
	}
	
	@Test
	void updateTitle() {
		Employee emp = new Employee(0, "FakeName", "FakeUserName","fakepassword","Employee");
		
		EmployeeDAO edao = Mockito.mock(EmployeeDAO.class);
		Mockito.when(edao.getEmployeeById(1)).thenReturn(emp);
		
		EmployeeService eserv = new EmployeeServiceImpl(edao);
		
		eserv.updateTitle(emp, "Manager");
		Assertions.assertEquals("Manager", emp.getTitle());
	}

}
