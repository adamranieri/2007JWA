package dev.atan.servicetest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import dev.atan.entities.Account;
import dev.atan.entities.Customer;
import dev.atan.exceptions.NegativeBalance;
import dev.atan.services.AccountService;
import dev.atan.services.AccountServiceImpl;
import dev.atan.services.CustomerService;
import dev.atan.services.CustomerServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
class TestsCustomerServices {

	public static AccountService aServ = new AccountServiceImpl();
	public static CustomerService cServ = new CustomerServiceImpl();

	@BeforeAll
	static void setUp() {

		try(Connection conn = dev.atan.utility.ConnectionUtil.getConnection()){
			String sql = "CALL SET_UP_BANK_DB";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(1)
	void createCustomer1() {
		Customer john = new Customer(0, "John", "Pass", 0);
		cServ.createCustomer(john);
		Assertions.assertNotEquals(0, john.getcID());
	}

	@Test
	@Order(2)
	void createCustomer2() {
		Customer sia = new Customer(0, "Sia", "Pass2", 0);
		cServ.createCustomer(sia);
		Assertions.assertNotEquals(0, sia.getcID());
	}
	
	@Test
	@Order(3)
	void createCustomer3() {
		Customer mark = new Customer(0, "Mark", "PassM", 0);
		cServ.createCustomer(mark);
		Assertions.assertNotEquals(0, mark.getcID());
		}
	
	@Test
	@Order(4)
	void createCustomer4() {
		Customer sean = new Customer(0, "Sean", "PassN", 0);
		cServ.createCustomer(sean);
		Assertions.assertNotEquals(0, sean.getcID());
		}
	
	@Test
	@Order(5)
	void createAccount1() throws NegativeBalance {
		Account one = new Account(0, 1, "Checking", 100);
		aServ.createAccount(one);
		Assertions.assertNotEquals(0, one.getaID());
		}
	
	@Test
	@Order(6)
	void createAccount2() throws NegativeBalance {
		Account two = new Account(0, 2, "Saving", 300);
		aServ.createAccount(two);
		Assertions.assertNotEquals(0, two.getaID());
		}
	
	@Test
	@Order(7)
	void createAccount3() throws NegativeBalance {
		Account three = new Account(0, 2, "Investment", 500);
		aServ.createAccount(three);
		Assertions.assertNotEquals(0, three.getaID());
		}
	

	@Test
	@Order(10)
	void getCustomerById() {		
		Customer john = cServ.getCustomerById(1);
		Assertions.assertEquals(1, john.getcID());
	}
	
	@Test
	@Order(11)
	void getCustomerByUserName() {
		Customer customer = cServ.getCustomerByUserName("John");
		Assertions.assertNotEquals(0,customer.getcID());
	}

	@Test
	@Order(12)
	void getAllCustomer() {
		List<Customer> customers = cServ.getAllCustomers();
		Assertions.assertNotEquals(0,customers.size());
		}
	

	@Test
	@Order(20)
	void updateCustomer() {
		Customer john = cServ.getCustomerById(1);
		john.setUserName("Johnatan");
		john = cServ.updateCustomer(john);
		Assertions.assertEquals("Johnatan", john.getUserName());	
	}
	
	@Test
	@Order(21)
	void renameCustomer() {
		Customer sia = cServ.getCustomerByUserName("Sia");
		sia = cServ.renameCustomer("Sia", "Sofia");
		Assertions.assertEquals("Sofia", sia.getUserName());
	}
	
	@Test
	@Order(22)
	void changePassword() {
		Customer sia = cServ.getCustomerByUserName("Sofia");
		sia = cServ.changePassword(sia, "NewPass");
		Assertions.assertEquals("NewPass", sia.getcPassword());
		}

	@Test
	@Order(30)
	void deleteCustomerById() {
		boolean result = cServ.deleteCustomerById(4);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(31)
	void deleteCustomerByUser() {
		boolean result = cServ.deleteCustomerByUserName("Mark");
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(32)
	void deleteAccount() {
		boolean result = aServ.deleteAccountById(1);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(35)
	void getAllCustomer2() {
		List<Customer> customers = cServ.getAllCustomers();
		Assertions.assertNotEquals(0,customers.size());
		}
	


	/*@AfterAll
	static void clearDown() {

		try(Connection conn = dev.atan.utility.ConnectionUtil.getConnection()){
			String sql = "CALL CLEAR_BANK_DB";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	} */

}
