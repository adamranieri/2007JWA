package dev.alsabea.doas;

import static org.junit.jupiter.api.Assertions.fail;

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
	@Order(4)
	final void testCreateInstance() {
		Manager mgr = new Manager();
		mgr.setFirstName("testFirstName");
		mgr.setLastName("testLastName");
		mgr.setUsername("testUserName");
		mgr.setPassword("testPassword");
	
		
		long generatedId= mDao.createInstance(mgr);
		
		Assertions.assertNotEquals(0, generatedId);
	}
	
	@Test
	@Order(1)
	final void testRetrieveAll() {
		Assertions.assertEquals(3, mDao.retrieveAll().size());
	}

	@ParameterizedTest
	@CsvSource({"1, John, Doe, 2, 1" , 
				"2, rick, brick, 2, 3 "})
	@Order(2)
	final void testRetrieveById(long id, String firstName, String lastName,
			int empsSize, int numOfReqs) {
		Manager m= mDao.retrieveById(id);
		
		Assertions.assertEquals(firstName, m.getFirstName());
		Assertions.assertEquals(lastName, m.getLastName());
		Assertions.assertEquals(empsSize, m.getEmps().size());
		Assertions.assertEquals(numOfReqs, m.getReqs().size());
	}

	@ParameterizedTest
	@ValueSource(ints = {100, 200, 300, 400})
	@Order(3)
	final void testRetrieveById(long id) {
		Assertions.assertNull(mDao.retrieveById(id));
	}

	
	
	@Test
	final void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testDeleteById() {
		fail("Not yet implemented"); // TODO
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
