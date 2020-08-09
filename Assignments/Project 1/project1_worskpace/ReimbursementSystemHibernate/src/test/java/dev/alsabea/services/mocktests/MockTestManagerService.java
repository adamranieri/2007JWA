package dev.alsabea.services.mocktests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.alsabea.doas.ManagerDao;
import dev.alsabea.doas.impl.ManagerDaoImpl;
import dev.alsabea.entities.Manager;
import dev.alsabea.services.impl.ManagerServicesImpl;

class MockTestManagerService {

	private static ManagerDao mDao;
	private static ManagerServicesImpl mServ;

	@BeforeAll
	final static void init() {

		mDao = Mockito.mock(ManagerDaoImpl.class);

		mServ = new ManagerServicesImpl(mDao);
	}

	final Manager buildManager(long id) {
		Manager mgr = new Manager();

		mgr.setMgrId(id);
		mgr.setFirstName("testFirstName");
		mgr.setLastName("testLastName");
		mgr.setUsername("testUserName");
		mgr.setPassword("testPassword");
		return mgr;

	}

	@Test
	final void testCreateInstance() {
		Manager mgr = buildManager(0);

		Mockito.when(mDao.createInstance(mgr)).thenReturn(mgr.getMgrId() + 1);

		Assertions.assertEquals(1, mServ.createInstance(mgr));
	}

	@Test
	final void testRetrieveById() {
		Manager mgr = buildManager(1);
		Mockito.when(mDao.retrieveById(1)).thenReturn(mgr);
		Assertions.assertEquals("testFirstName", mServ.retrieveById(1).getFirstName());
	}

	@Test
	final void testRetrieveByUsernameAndPassword() {
		Manager mgr = buildManager(1);

		Mockito.when(mDao.retrieveByUsernameAndPassword(mgr.getUsername(), mgr.getPassword())).thenReturn(mgr);

		Assertions.assertEquals("testFirstName",
				mServ.retrieveByUsernameAndPassword("testUserName", "testPassword").getFirstName());
	}

	@Test
	final void testUpdate() {
		Manager mgr = buildManager(1);

		Mockito.when(mDao.update(mgr)).thenReturn(true);

		Assertions.assertTrue(mServ.update(mgr));
	}

	@Test
	final void testDeleteById() {

		Mockito.when(mDao.deleteById(1)).thenReturn(true);

		Assertions.assertTrue(mServ.deleteById(1));
	}

}
