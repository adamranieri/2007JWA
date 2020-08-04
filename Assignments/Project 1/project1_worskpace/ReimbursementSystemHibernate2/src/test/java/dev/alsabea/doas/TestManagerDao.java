package dev.alsabea.doas;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import dev.alsabea.connection.HibernateConnectionEstablisher;
import dev.alsabea.doas.impl.ManagerDaoImpl;
import dev.alsabea.entities.Manager;


@TestMethodOrder(OrderAnnotation.class)
class TestManagerDao {

	private static ManagerDao mDao= ManagerDaoImpl.getInstance();
	
	
	@Test
	@Order(5)
	final void testCreateInstance() {
		Manager mgr = new Manager();
		mgr.setFirstName("testFirstName");
		mgr.setLastName("testLastName");
		mgr.setUsername("testUserName");
		mgr.setPassword("testPassword");
	
		
		long generatedId= mDao.createInstance(mgr);
		
		Assertions.assertNotEquals(-1, generatedId);
	}
	
	@ParameterizedTest
	@CsvSource({"1, johnUser, johnPass", "3, maryUser, maryPass" })
	@Order(1)
	final void testRetrieveByUserAndPass(long mId, String user, String pass) {
		Assertions.assertEquals(mId, mDao.retrieveByUsernameAndPassword(user, pass).getMgrId());
	}

	
	@Test
	@Order(2)
	final void testRetrieveByUserAndPassNegative() {
		Assertions.assertNull(mDao.retrieveByUsernameAndPassword("testtest", "noPass"));
	}

	
//	@ParameterizedTest
//	@CsvSource({"1, John, Doe, 2, 1" , 
//				"2, rick, brick, 2, 3 "})
//	@Order(3)
//	final void testRetrieveById(long id, String firstName, String lastName,
//			int empsSize, int numOfReqs) {
//		Manager m= mDao.retrieveById(id);
//		
//		Assertions.assertEquals(firstName, m.getFirstName());
//		Assertions.assertEquals(lastName, m.getLastName());
//		Assertions.assertEquals(empsSize, m.getEmps().size());
//		Assertions.assertEquals(numOfReqs, m.getReqs().size());
//	}

	
	@ParameterizedTest
	@CsvSource({"1, John, Doe, 2" , 
				"2, rick, brick, 2 "})
	@Order(3)
	final void testRetrieveById(long id, String firstName, String lastName,
			int empsSize) {
		Manager m= mDao.retrieveById(id);
		
		Assertions.assertEquals(firstName, m.getFirstName());
		Assertions.assertEquals(lastName, m.getLastName());
		Assertions.assertEquals(empsSize, m.getEmps().size());
	}

	
	
	@ParameterizedTest
	@ValueSource(ints = {100, 200, 300, 400})
	@Order(4)
	final void testRetrieveById(long id) {
		Assertions.assertNull(mDao.retrieveById(id));
	}

	
	
	@Test
	final void testUpdate() {
		Manager mgr = new Manager();
		mgr.setFirstName("testFirstNameToBeUpdated");
		mgr.setLastName("testLastNameToBeUpdated");
		mgr.setUsername("testUserNameToBeUpdated");
		mgr.setPassword("testPasswordToBeUpdated");
	
		
		long generatedId= mDao.createInstance(mgr);
		
		Manager mgr2 = new Manager();
		mgr2.setMgrId(generatedId);
		mgr2.setFirstName("testFirstNameUpdated");
		mgr2.setLastName("testLastNameUpdated");
		mgr2.setUsername("testUserNameUpdated");
		mgr2.setPassword("testPasswordUpdated");
		
		Assertions.assertTrue(mDao.update(mgr2));
	}

	
	@Test
	final void testUpdateNegative() {

		Manager mgr2 = new Manager();
		mgr2.setMgrId(789);
		mgr2.setFirstName("testFirstNameUpdated");
		mgr2.setLastName("testLastNameUpdated");
		mgr2.setUsername("testUserNameUpdated");
		mgr2.setPassword("testPasswordUpdated");
		
		Assertions.assertFalse(mDao.update(mgr2));
	}
	
	
	@Test
	final void testDeleteById() {
		Manager mgr = new Manager();
		mgr.setFirstName("testFirstNameToBeDeleted");
		mgr.setLastName("testLastNameToBeDeleted");
		mgr.setUsername("testUserNameToBeDeleted");
		mgr.setPassword("testPasswordToBeDeleted");
		
		
		long generatedId= mDao.createInstance(mgr);
		
		Assertions.assertTrue(mDao.deleteById(generatedId));
		
	}

	
	@Test
	final void testDeleteByIdNegative() {
		Assertions.assertFalse(mDao.deleteById(78));	
	}

	
	@AfterAll
	final static void cleanUp() {
		String sql = "DELETE FROM Manager  WHERE firstName LIKE 'test%'";
		try (Session s= HibernateConnectionEstablisher.getSessionFactory().openSession()){

			s.beginTransaction();
			s.createQuery(sql).executeUpdate();
			s.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
