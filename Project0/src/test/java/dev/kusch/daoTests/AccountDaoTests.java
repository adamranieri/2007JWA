package dev.kusch.daoTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import dev.kusch.daos.AccountDAO;
import dev.kusch.daos.AccountDAOLocal;
import dev.kusch.entities.Account;
import dev.kusch.services.AccountServices;

class AccountDaoTests {

	public static AccountDAO cdao = AccountDAOLocal.getAccountDAO();

	@Test
	@Order(1)
	void testCreateBasic() {
		Account testAcc = new Account("VacationFund", 30.00, 1, 0);
		cdao.createAccount(testAcc);
		Assertions.assertNotEquals(0, testAcc.getcId());
	}

	@Test
	@Order(2)
	void getAccountByIdBasic() {
		Account getAcc = cdao.getAccountById(1);
		Assertions.assertEquals(1, getAcc.getcId());
	}

	@Test
	@Order(3)
	void getAllAccounts() {
		Account testAcc2 = new Account("Retirement Fund", 1000, 1, 0);
		cdao.createAccount(testAcc2);

		Set<Account> fullAcc = cdao.getAllAccounts();
		Assertions.assertEquals(2, fullAcc.size());
	}

	@Test
	@Order(4)
	void updateAccountBasic() {
		Account vacat = cdao.getAccountById(1);
		vacat.setBalance(2000);
		vacat = cdao.updateAccount(vacat);
		Assertions.assertEquals(2000, vacat.getBalance());
	}

	@Test
	@Order(5)
	void deleteAccount() {
		boolean result = cdao.deleteAccount(2);
		Assertions.assertEquals(true, result);
	}

	@Test
	@Order(6)
	void deleteAccountNegative() {
		boolean result = cdao.deleteAccount(20);
		Assertions.assertEquals(false, result);
	}
}
