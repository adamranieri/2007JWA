package dev.alsabea.doas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.alsabea.connection.ConnectionEstablisher;
import dev.alsabea.doas.impl.EmployeeDaoImpl;
import dev.alsabea.entities.Employee;

@TestMethodOrder(OrderAnnotation.class)
class TestEmployeeDao {

	private static EmployeeDao  empDao = EmployeeDaoImpl.getInstance();
	
	@Test @Order(3)
	final void testCreateInstance() {
		/*
		 * (firstName , lastName , username , password , emp_role, mgr_id)
		 */
		Employee emp = new Employee();
		emp.setFirstName("testFirstName");
		emp.setLastName("testLastName");
		emp.setUsername("testUserName");
		emp.setPassword("testPassword");
		emp.setEmpRole("testEmpRole");
		emp.setMgrId(3);
		
		
		int generatedId= empDao.createInstance(emp);
		
		Assertions.assertNotEquals(0, generatedId);
		
	}

	@Test @Order(1)
	final void testRetrieveById() {
		Employee emp = empDao.retrieveById(1);
		//( emp_id, first_name , last_name , username , password , emp_role, mgr_id)
		Assertions.assertNotNull(emp);
		Assertions.assertEquals("John", emp.getFirstName());
		Assertions.assertEquals("Doe", emp.getLastName());
		
		Assertions.assertEquals("johnUser", emp.getUsername());
		Assertions.assertEquals("johnPass", emp.getPassword());
		Assertions.assertEquals("MGR", emp.getEmpRole() );
		Assertions.assertEquals(0, emp.getMgrId()); //since it is of type number, it will return 0 in case of null
		
	}
	
	@Test  @Order(2)
	final void testRetrieveAll() {
		List<Employee> emps = empDao.retrieveAll();
		//( emp_id, first_name , last_name , username , password , emp_role, mgr_id)
		Assertions.assertEquals(10, emps.size());
		
	}

	@Test
	final void testUpdate() {
		Employee emp = new Employee();
		emp.setFirstName("testFirstNameToBeUpdated");
		emp.setLastName("testLastNameToBeUpdated");
		emp.setUsername("testUserNameToBeUpdated");
		emp.setPassword("testPasswordToBeUpdated");
		emp.setEmpRole("testEmpRoleToBeUpdated");
		emp.setMgrId(3);
		
		
		int generatedId= empDao.createInstance(emp);
		
		Employee emp_updated = new Employee();
		emp_updated.setEmpId(generatedId);
		emp_updated.setFirstName("testFirstNameUpdated___");
		emp_updated.setLastName("testLastNameUpdated___");
		emp_updated.setUsername("testUserNameUpdated___");
		emp_updated.setPassword("testPasswordUpdated___");
		emp_updated.setEmpRole("testEmpRoleUpdated____");
		emp_updated.setMgrId(3);
		
		
		Assertions.assertTrue(empDao.update(emp_updated));
		
	}

	@Test  
	final void testDeleteById() {
		Employee emp = new Employee();
		emp.setFirstName("testFirstNameToBeDeleted");
		emp.setLastName("testLastNameToBeDeleted");
		emp.setUsername("testUserNameToBeDeleted");
		emp.setPassword("testPasswordToBeDeleted");
		emp.setEmpRole("testEmpRoleToBeDeleted");
		emp.setMgrId(3);
		
		
		int generatedId= empDao.createInstance(emp);
		
		Assertions.assertTrue(empDao.deleteById(generatedId));
		
		
	}

	
	@AfterAll
	final static void cleanUp() {
		String sql = "DELETE FROM reimbursement_system_db.emp_mgr  WHERE first_name LIKE 'test%';";
		Connection con = ConnectionEstablisher.getConnection();
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
