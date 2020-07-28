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
import dev.alsabea.doas.impl.EmployeeManagerDaoImpl;
import dev.alsabea.entities.Employee_Manager;

@TestMethodOrder(OrderAnnotation.class)
class TestEmployeeManagerDao {

	private static EmployeeManagerDao  empDao = EmployeeManagerDaoImpl.getInstance();
	
	@Test @Order(4)
	final void testCreateInstance() {
		/*
		 * (firstName , lastName , username , password , emp_role, mgr_id)
		 */
		Employee_Manager emp = new Employee_Manager();
		emp.setFirstName("testFirstName");
		emp.setLastName("testLastName");
		emp.setUsername("testUserName");
		emp.setPassword("testPassword");
		emp.setEmpRole("testEmpRole");
		emp.setMgrId(3);
		
		
		int generatedId= empDao.createInstance(emp);
		
		Assertions.assertNotEquals(0, generatedId);
		
	}
	
	@Test  @Order(1)
	final void testRetrieveAll() {
		List<Employee_Manager> emps = empDao.retrieveAll();
		//( emp_id, first_name , last_name , username , password , emp_role, mgr_id)
		Assertions.assertEquals(10, emps.size());
		
	}
	
	

	@Test @Order(2)
	final void testRetrieveById() {
		Employee_Manager emp = empDao.retrieveById(1);
		//( emp_id, first_name , last_name , username , password , emp_role, mgr_id)
		Assertions.assertNotNull(emp);
		Assertions.assertEquals("John", emp.getFirstName());
		Assertions.assertEquals("Doe", emp.getLastName());
		
		Assertions.assertEquals("johnUser", emp.getUsername());
		Assertions.assertEquals("johnPass", emp.getPassword());
		Assertions.assertEquals("MGR", emp.getEmpRole() );
		Assertions.assertEquals(0, emp.getMgrId()); //since it is of type number, it will return 0 in case of null
		
	}
	
	@Test @Order(3)
	final void testRetrieveByIdNegative() {
		Employee_Manager emp = empDao.retrieveById(700);
		//( emp_id, first_name , last_name , username , password , emp_role, mgr_id)
		Assertions.assertNull(emp);
	}

	@Test
	final void testUpdate() {
		Employee_Manager emp = new Employee_Manager();
		emp.setFirstName("testFirstNameToBeUpdated");
		emp.setLastName("testLastNameToBeUpdated");
		emp.setUsername("testUserNameToBeUpdated");
		emp.setPassword("testPasswordToBeUpdated");
		emp.setEmpRole("testEmpRoleToBeUpdated");
		emp.setMgrId(3);
		
		
		int generatedId= empDao.createInstance(emp);
		
		Employee_Manager emp_updated = new Employee_Manager();
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
	final void testUpdateNegative() {
		Employee_Manager emp = new Employee_Manager();
		emp.setEmpId(89);
		emp.setFirstName("testFirstNameUpdateNoneExistent");
		emp.setLastName("testLastNameUpdateNoneExistent");
		emp.setUsername("testUserNameUpdateNoneExistent");
		emp.setPassword("testPasswordUpdateNoneExistent");
		emp.setEmpRole("testEmpRoleUpdateNoneExistent");
		emp.setMgrId(3);
		
		Assertions.assertFalse(empDao.update(emp));
		
	}
	
	
	@Test  
	final void testDeleteById() {
		Employee_Manager emp = new Employee_Manager();
		emp.setFirstName("testFirstNameToBeDeleted");
		emp.setLastName("testLastNameToBeDeleted");
		emp.setUsername("testUserNameToBeDeleted");
		emp.setPassword("testPasswordToBeDeleted");
		emp.setEmpRole("testEmpRoleToBeDeleted");
		emp.setMgrId(3);
		
		
		int generatedId= empDao.createInstance(emp);
		
		Assertions.assertTrue(empDao.deleteById(generatedId));
		
		
	}

	@Test  
	final void testDeleteByIdNegative() {
		Assertions.assertFalse(empDao.deleteById(9999));
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
