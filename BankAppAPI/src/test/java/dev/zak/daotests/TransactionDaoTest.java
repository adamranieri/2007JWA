package dev.zak.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.zak.daos.AccountDaoInterface;
import dev.zak.daos.AccountDaoMaria;
import dev.zak.daos.CustomerDaoInterface;
import dev.zak.daos.CustomerDaoMaria;
import dev.zak.daos.TransactionDaoInterface;
import dev.zak.daos.TransactionDaoMaria;
import dev.zak.entities.Account;
import dev.zak.entities.Customer;
import dev.zak.entities.Transaction;
import dev.zak.utilities.ConnectionUtility;

@TestMethodOrder(OrderAnnotation.class)
class TransactionDaoTest {

	public static AccountDaoInterface aDao =  AccountDaoMaria.getAccountDaoMaria();
	public static CustomerDaoInterface cDao =  CustomerDaoMaria.getCustomerDaoMaria();
	public static TransactionDaoInterface tDao =  TransactionDaoMaria.getTransactionDaoMaria();
	
	
	@BeforeAll
	static void setUp() {

		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "CALL set_up_bank_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(1)
	void createTransaction() {
		Customer c = new Customer(0,"Jon","Jon_password");
		cDao.createCustomer(c);
		Account a = new Account(0, 1,"Jon account",3000);
		aDao.createAccount(c.getcId(), a);
		

		Transaction t = new Transaction(0, 1,6000,7000);
		tDao.createTransaction(t);
		Assertions.assertNotEquals(0, t.gettId());
	}
	
	@Test
	@Order(2)
	void getTransactionById() {
		Transaction t = tDao.getTransactionById(1);
		Assertions.assertEquals(1, t.gettId());
	}

	@Test
	@Order(3)
	void getAllTransactionsByAccount() {
		Transaction t = new Transaction(0, 1,7000,5500);
		tDao.createTransaction(t);
		t = new Transaction(0, 1,5500,4000);
		tDao.createTransaction(t);
		
		Set<Transaction> transactions = tDao.getAllTransactionsByAccount(1);
		Assertions.assertEquals(3, transactions.size());
	}

	@Test
	@Order(4)
	void deleteTransactionsById() {
		Transaction t = new Transaction(0, 1,7000,5500);
		tDao.createTransaction(t);
		
		boolean isDeleted = tDao.DeleteTransactionById(t.gettId());
		Assertions.assertEquals(true, isDeleted);
	}

	@Test
	@Order(4)
	void deleteTransactionsByIdNegative() {
		boolean isDeleted = tDao.DeleteTransactionById(2000);
		Assertions.assertEquals(false, isDeleted);
	}
	
	@AfterAll
	static void tearDown() {
		
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "CALL tear_down_bank_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
