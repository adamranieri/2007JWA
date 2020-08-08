package dev.alsabea.doas;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import dev.alsabea.connection.HibernateConnectionEstablisher;
import dev.alsabea.doas.impl.EmployeeDaoImpl;
import dev.alsabea.entities.Employee;
import dev.alsabea.entities.Manager;
import dev.alsabea.setupteardown.SetUpAndTearDown;

@TestMethodOrder(OrderAnnotation.class)
class TestEmployeeDao {

	private static EmployeeDaoImpl  empDao = EmployeeDaoImpl.getInstance();
	
	@BeforeAll
	@Test 
	final static void setup() {
		SetUpAndTearDown.setup();
	}
	
	@AfterAll
	@Test
	final static void teardown() {
		SetUpAndTearDown.teardown();
	}
	
	
	
	@Test @Order(5)
	final void testCreateInstance() {
		/*
		 * (firstName , lastName , username , password , emp_role, mgr_id)
		 */
		Employee emp = new Employee();
		emp.setFirstName("testFirstName");
		emp.setLastName("testLastName");
		emp.setUsername("testUserName");
		emp.setPassword("testPassword");
		Manager mgr= new Manager();
		mgr.setMgrId(1);
		emp.setMgr(mgr);
		
		long generatedId= empDao.createInstance(emp);
		
		Assertions.assertNotEquals(0, generatedId);
		
	}
	
	/**
	 * reason behind the test below, is because we cannot create an employee 
	 * and assign it to a non-existent manager.
	 */
	
	@ParameterizedTest 
	@ValueSource(ints = {90, 80})
	@Order(6)
	final void testCreateInstanceNegative(int mgrId) {
		/*
		 * (firstName , lastName , username , password , mgr_id)
		 */
		Employee emp = new Employee();
		emp.setFirstName("testFirstName");
		emp.setLastName("testLastName");
		emp.setUsername("testUserName");
		emp.setPassword("testPassword");
		emp.setMgr(new Manager (mgrId));

		
			 
		Assertions.assertEquals(-1, empDao.createInstance(emp));
		
		
	}
	
	
	
	@ParameterizedTest
	@CsvSource({"1, gregUser, gregPass", "3, jaxUser, jaxPass", 
				"6, robUser, robPass"})
	@Order(1)
	final void testRetrieveByUserAndPass(long eId, String user, String pass) {
		Assertions.assertEquals(eId, empDao.retrieveByUsernameAndPassword(user, pass).getEmpId());
	}

	
	@Test
	@Order (2)
	final void testRetrieveByUserAndPassNegative() {
		Assertions.assertNull(empDao.retrieveByUsernameAndPassword("testtest", "noPass"));
	}
	
	
	 @Test
	 @Order(3)
	final void testRetrieveById() {
		 Employee emp = empDao.retrieveById(1);
		//( emp_id, first_name , last_name , username , password , emp_role, mgr_id)
		Assertions.assertNotNull(emp);
		Assertions.assertEquals("greg", emp.getFirstName());
		Assertions.assertEquals("dreg", emp.getLastName());
		
		Assertions.assertEquals("gregUser", emp.getUsername());
		Assertions.assertEquals("gregPass", emp.getPassword());
		Assertions.assertEquals("John",emp.getMgr().getFirstName()); 
		Assertions.assertEquals(1, emp.getReqs().size());
		
	}
	
	@Test @Order(4)
	final void testRetrieveByIdNegative() {
		Employee emp = empDao.retrieveById(700);
		//( emp_id, first_name , last_name , username , password , emp_role, mgr_id)
		Assertions.assertNull(emp);
	}
	

	@Test
	final void testUpdate() {
		Employee emp = new Employee();
		emp.setFirstName("testFirstNameToBeUpdated");
		emp.setLastName("testLastNameToBeUpdated");
		emp.setUsername("testUserNameToBeUpdated");
		emp.setPassword("testPasswordToBeUpdated");
		
		Manager m = new Manager();
		m.setMgrId(3);
		
		emp.setMgr(m);
		
		long generatedId= empDao.createInstance(emp);
		
		Employee emp_updated = new Employee();
		emp_updated.setEmpId(generatedId);
		emp_updated.setFirstName("testFirstNameUpdated___");
		emp_updated.setLastName("testLastNameUpdated___");
		emp_updated.setUsername("testUserNameUpdated___");
		emp_updated.setPassword("testPasswordUpdated___");
	
		Manager mUpdated = new Manager();
		mUpdated.setMgrId(3);
		
		emp_updated.setMgr(mUpdated);
		
		Assertions.assertTrue(empDao.update(emp_updated));
		
	}

	@Test
	final void testUpdateNegative() {
		Employee emp = new Employee();
		emp.setEmpId(89);
		emp.setFirstName("testFirstNameUpdateNoneExistent");
		emp.setLastName("testLastNameUpdateNoneExistent");
		emp.setUsername("testUserNameUpdateNoneExistent");
		emp.setPassword("testPasswordUpdateNoneExistent");
		
		Manager m= new Manager();
		m.setMgrId(3);
		emp.setMgr(m);
		
		Assertions.assertFalse(empDao.update(emp));
		
	}

	@Test  
	final void testDeleteById() {
		Employee emp = new Employee();
		emp.setFirstName("testFirstNameToBeDeleted");
		emp.setLastName("testLastNameToBeDeleted");
		emp.setUsername("testUserNameToBeDeleted");
		emp.setPassword("testPasswordToBeDeleted");
		
		Manager m= new Manager();
		m.setMgrId(2);
		emp.setMgr(m);
		
		long generatedId= empDao.createInstance(emp);
		
		Assertions.assertTrue(empDao.deleteById(generatedId));
		
	}

	@Test  
	final void testDeleteByIdNegative() {
		Assertions.assertFalse(empDao.deleteById(9999));
	}
	
	
}