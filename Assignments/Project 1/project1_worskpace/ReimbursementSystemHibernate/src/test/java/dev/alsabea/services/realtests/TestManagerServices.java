package dev.alsabea.services.realtests;

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

import dev.alsabea.entities.Manager;
import dev.alsabea.services.ManagerServices;
import dev.alsabea.services.impl.ManagerServicesImpl;
import dev.alsabea.setupteardown.SetUpAndTearDown;

@TestMethodOrder(OrderAnnotation.class)
class TestManagerServices {

	private static ManagerServices mServ = ManagerServicesImpl.getInstance();

	@BeforeAll
	final static void setup() {
		SetUpAndTearDown.setup();
	}

	@AfterAll
	final static void teardown() {
		SetUpAndTearDown.teardown();
	}

	@Test
	@Order(4)
	final void testCreateInstance() {
		Manager mgr = new Manager();
		mgr.setFirstName("testFirstName");
		mgr.setLastName("testLastName");
		mgr.setUsername("testUserName");
		mgr.setPassword("testPassword");

		long generatedId = mServ.createInstance(mgr);

		Assertions.assertNotEquals(0, generatedId);
	}

	@ParameterizedTest
	@CsvSource({ "1, John, Doe", "2, rick, brick" })
	@Order(2)
	final void testRetrieveById(long id, String firstName, String lastName) {
		Manager m = mServ.retrieveById(id);

		Assertions.assertEquals(firstName, m.getFirstName());
		Assertions.assertEquals(lastName, m.getLastName());
	}

	@ParameterizedTest
	@ValueSource(ints = { 100, 200, 300, 400 })
	@Order(3)
	final void testRetrieveById(long id) {
		Assertions.assertNull(mServ.retrieveById(id));
	}

	@Test
	final void testUpdate() {
		Manager mgr = new Manager();
		mgr.setFirstName("testFirstNameToBeUpdated");
		mgr.setLastName("testLastNameToBeUpdated");
		mgr.setUsername("testUserNameToBeUpdated");
		mgr.setPassword("testPasswordToBeUpdated");

		long generatedId = mServ.createInstance(mgr);

		Manager mgr2 = new Manager();
		mgr2.setMgrId(generatedId);
		mgr2.setFirstName("testFirstNameUpdated");
		mgr2.setLastName("testLastNameUpdated");
		mgr2.setUsername("testUserNameUpdated");
		mgr2.setPassword("testPasswordUpdated");

		Assertions.assertTrue(mServ.update(mgr2));
	}

	@Test
	final void testUpdateNegative() {

		Manager mgr2 = new Manager();
		mgr2.setMgrId(789);
		mgr2.setFirstName("testFirstNameUpdated");
		mgr2.setLastName("testLastNameUpdated");
		mgr2.setUsername("testUserNameUpdated");
		mgr2.setPassword("testPasswordUpdated");

		Assertions.assertFalse(mServ.update(mgr2));
	}

	@Test
	final void testDeleteById() {
		Manager mgr = new Manager();
		mgr.setFirstName("testFirstNameToBeDeleted");
		mgr.setLastName("testLastNameToBeDeleted");
		mgr.setUsername("testUserNameToBeDeleted");
		mgr.setPassword("testPasswordToBeDeleted");

		long generatedId = mServ.createInstance(mgr);

		Assertions.assertTrue(mServ.deleteById(generatedId));

	}

	@Test
	final void testDeleteByIdNegative() {
		Assertions.assertFalse(mServ.deleteById(78));
	}

}
