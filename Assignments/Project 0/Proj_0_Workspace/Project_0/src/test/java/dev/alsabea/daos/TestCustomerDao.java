package dev.alsabea.daos;

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

import dev.alsabea.connectionUtils.ConnectionUtils;
import dev.alsabea.daos.impl.CustomerDaoImpl;
import dev.alsabea.entities.Customer;

@TestMethodOrder(OrderAnnotation.class)
public class TestCustomerDao {

	CustomerDao custDao = CustomerDaoImpl.getCustomerDao();

	@Test
	@Order(1)
	void testRetrieveById() {

		Customer c = custDao.retrieveById(3);

		Assertions.assertEquals("rex", c.getUsername());
	}
	
	@Test 
	@Order(2)
	void testRetrieveByIdNegative() {

		Customer c = custDao.retrieveById(11);

		Assertions.assertNull(c);
	}
	

	@Test
	@Order(3)
	void testRetrieveByUserName() {

		Customer c = custDao.retrieveByUsername("rex").get(0);

		Assertions.assertEquals(3, c.getCustomerId());
	}
	
	@Test
	@Order(4)
	void testRetrieveByUserNameNegative() {

		List<Customer> c = custDao.retrieveByUsername("al;ksdjfsaldkjfsljf");

		Assertions.assertNull(c);
	}

	/*
	 * this will fail if we test create before it, because it will mess up the
	 * number of elements in the database before we use the create test, there would
	 * be 5 customers, after we use the create test, we would have 6 customers.
	 */
	@Test
	@Order(5)
	void testRetrieveAll() {

		List<Customer> c = custDao.retrieveAll();
		Assertions.assertEquals(5, c.size());
	}

	@Test
	void createCustomer() {
		Customer c = new Customer();
		c.setCustomerId(10001);
		c.setUsername("testName");
		c.setPassword("testPass");
		Assertions.assertNotEquals(0, custDao.create(c));
	}

	@Test
	void testDeleteById() {

		Customer c = new Customer();
		c.setUsername("testUserToBeDeleted");
		c.setPassword("testPassToBeDeleted");

		int idInDb = custDao.create(c);

		Assertions.assertTrue(custDao.delete(idInDb));
	}

	@Test
	void testUpdate() {

		Customer t = new Customer();
		String user = "testUserToBeUpdated";
		String pass = "testPassToBeUpdated";
		t.setUsername(user);
		t.setPassword(pass);

		int id = custDao.create(t);

		Customer c = new Customer();
		c.setCustomerId(id);
		c.setUsername("testUpdatedName");
		c.setPassword("testUpdatedPassword");

		Assertions.assertTrue(custDao.update(c));
	}

	@AfterAll
	static void removeTestingStuff() {
		String sql = "delete from customer where username LIKE 'test%';";
		Connection con = ConnectionUtils.getConnection();
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
