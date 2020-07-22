package dev.alsabea.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.alsabea.connectionUtils.ConnectionUtils;
import dev.alsabea.entities.Account;
import dev.alsabea.exceptions.NegativeBalanceException;
import dev.alsabea.services.impl.AccountServicesImpl;

@TestMethodOrder(OrderAnnotation.class)
class TestAccountServices {

	AccountServices aServices = AccountServicesImpl.getAccountServicesInstance();

	@Test
	@Order(1)
	void testRetrieveById() {
		Account a = aServices.retrieveById(100);
		Assertions.assertEquals("savings", a.getAccountName());

	}

	@Test
	@Order(2)
	void testRetrieveByIdNegative() {
		Account a = aServices.retrieveById(898098);
		Assertions.assertNull(a);

	}

	@Test
	void testCreateAccount() {
		Account a = new Account();
		a.setCustomerId(5);
		a.setAccountName("test_create_name");
		a.setBalance(800);

		try {
			Assertions.assertNotEquals(0, aServices.create(a));
		} catch (NegativeBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testCreateAccountNegative() {
		Account a = new Account();
		a.setCustomerId(5);
		a.setAccountName("test_create_name");
		a.setBalance(-800);

		Assertions.assertThrows(NegativeBalanceException.class, () -> {
			aServices.create(a);
		});
	}

	@Test
	void testDeleteById() {
		Account a = new Account();
		a.setCustomerId(5);
		a.setAccountName("test_to_be_deleted");
		a.setBalance(800);

		int id = 0;
		try {
			id = aServices.create(a);
		} catch (NegativeBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assertions.assertTrue(aServices.delete(id));

	}

	@Test
	void testUpdate() {
		Account a = new Account();
		a.setCustomerId(5);
		a.setAccountName("testToBeUpdatedName");
		a.setBalance(800);

		int id = 0;
		try {
			id = aServices.create(a);
			Account aUpdated = new Account();
			aUpdated.setAccountId(id);
			aUpdated.setCustomerId(5);
			aUpdated.setAccountName("testUpdatedName");
			aUpdated.setBalance(1000);
			Assertions.assertTrue(aServices.update(aUpdated));
		} catch (NegativeBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void testUpdateNegative() {
		Account a = new Account();
		a.setCustomerId(5);
		a.setAccountName("testToBeUpdatedName");
		a.setBalance(800);

		int id = 0;
		try {
			id = aServices.create(a);
		} catch (NegativeBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Account aUpdated = new Account();
		aUpdated.setAccountId(id);
		aUpdated.setCustomerId(5);
		aUpdated.setAccountName("testUpdatedName");
		aUpdated.setBalance(-1000);
		
		Assertions.assertThrows(NegativeBalanceException.class, () -> {
			aServices.update(aUpdated);
		});

	}

	@Test
	void getAccountsWithBalanceLessThanWithList() {

		List<Account> accts = aServices.retrieveAllAccounts(1);
		List<Account> acctsReturnedFromBalanceFunction = null;
		try {
			acctsReturnedFromBalanceFunction = aServices.balanceLessThan(200, accts);
		} catch (NegativeBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assertions.assertEquals(2, acctsReturnedFromBalanceFunction.size());
	}

	@Test
	void getAccountsWithBalanceLessThanWithListNegative() {

		List<Account> accts = aServices.retrieveAllAccounts(1);
		Assertions.assertThrows(NegativeBalanceException.class, () -> {
			aServices.balanceLessThan(-200, accts);
		});
	}

	@Test
	void getAccountsWithBalanceGreaterThanWithList() {
		List<Account> accts = aServices.retrieveAllAccounts(1);
		List<Account> acctsReturnedFromBalanceFunction = null;
		try {
			acctsReturnedFromBalanceFunction = aServices.balanceGreaterThan(200, accts);
		} catch (NegativeBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assertions.assertEquals(3, acctsReturnedFromBalanceFunction.size());
	}

	@Test
	void getAccountsWithBalanceGreaterThanWithListNegative() {

		List<Account> accts = aServices.retrieveAllAccounts(1);
		Assertions.assertThrows(NegativeBalanceException.class, () -> {
			aServices.balanceGreaterThan(-200, accts);
		});

	}
	
	
	@Test
	void testGetAccounts() {
		List<Account> accountsList = new ArrayList<>();

		accountsList = aServices.retrieveAllAccounts( /* customer_id */ 1);

		Assertions.assertEquals(5, accountsList.size());

	}

	@AfterAll
	static void removeTestingStuff() {
		String sql = "delete from proj_0_db.account where account_name LIKE 'test%';";
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
