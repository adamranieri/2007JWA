package dev.alsabea.services.mocktests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.alsabea.doas.EmployeeDao;
import dev.alsabea.entities.Employee;
import dev.alsabea.entities.Manager;
import dev.alsabea.services.EmployeeServices;
import dev.alsabea.services.impl.EmployeeServicesImpl;

class MockTestEmployeeService {

	private static EmployeeDao eDao;


	private static EmployeeServices eServ;

	@BeforeAll
	final static void init() {

		eDao = Mockito.mock(EmployeeDao.class);

		eServ = new EmployeeServicesImpl(eDao);
	}

	final Employee buildEmployee(long id) {

		Employee emp = new Employee();
		emp.setEmpId(id);
		emp.setFirstName("testFirstName");
		emp.setLastName("testLastName");
		emp.setUsername("testUserName");
		emp.setPassword("testPassword");
		emp.setMgr(new Manager(1));
		return emp;
	}

	@Test
	final void testCreateInstance() {

		Employee emp = buildEmployee(0);

		Mockito.when(eDao.createInstance(emp)).thenReturn(emp.getEmpId() + 1);

		Assertions.assertNotEquals(0, eServ.createInstance(emp));

	}

	@Test
	final void testRetrieveById() {

		Employee emp = buildEmployee(1);

		Mockito.when(eDao.retrieveById(1)).thenReturn(emp);

		Assertions.assertEquals(1, eServ.retrieveById(1).getEmpId());

	}

	@Test
	final void testRetrieveByUsernameAndPassword() {
		Employee emp = buildEmployee(1);

		Mockito.when(eDao.retrieveByUsernameAndPassword(emp.getUsername(), emp.getPassword())).thenReturn(emp);

		Assertions.assertEquals(1,
				eServ.retrieveByUsernameAndPassword(emp.getUsername(), emp.getPassword()).getEmpId());
	}

	@Test
	final void testUpdate() {
		Employee emp = buildEmployee(1);

		Mockito.when(eDao.update(emp)).thenReturn(true);
		Assertions.assertTrue(eServ.update(emp));

	}

	@Test
	final void testDeleteById() {

		Mockito.when(eDao.deleteById(1)).thenReturn(true);
		Assertions.assertTrue(eServ.deleteById(1));
	}

}
