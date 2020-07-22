package dev.cosentino.servicestests;

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

import dev.cosentino.exceptions.CustomerDoesNotExistException;
import dev.cosentino.resources.Account;
import dev.cosentino.resources.Customer;
import dev.cosentino.services.AccountService;
import dev.cosentino.services.AccountServiceImpl;
import dev.cosentino.services.CustomerService;
import dev.cosentino.services.CustomerServiceImpl;
import dev.cosentino.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceTests {

	public static CustomerService cserv = new CustomerServiceImpl();
	public static AccountService aserv = new AccountServiceImpl();
	
	@BeforeAll
	static void setUp() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_projectdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void createCustomer() {
		Customer customer = new Customer(0, "myusername", "password");
		cserv.createNewCustomer(customer);
		Assertions.assertEquals(1, customer.getCustomerId());
	}
	
	@Test
	@Order(2)
	void getCustomerByAccountId() throws CustomerDoesNotExistException {
		Account account = new Account(0,"accountname",16,1);
		aserv.createAccount(account);
		Customer customer = cserv.getCustomerByAccountId(1);
		Assertions.assertEquals(1, customer.getCustomerId());
	}
	
	@Test
	@Order(3)
	void getCustomerById() {
		Customer customer = cserv.getCustomerById(1);
		Assertions.assertEquals(1, customer.getCustomerId());
	}
	
	
	@Test
	@Order(4)
	void getAllCustomers() {
		Customer customer = new Customer(0,"another customer","mypassword");
		cserv.createNewCustomer(customer);
		Set<Customer> customers = cserv.getAllCustomers();
		Assertions.assertEquals(2, customers.size());
	}
	
	@Test
	@Order(5)
	void getCustomerByUsername() {
		Customer customer = cserv.getCustomerByUsername("myusername");
		Assertions.assertEquals("myusername", customer.getUsername());
	}
	
	@Test
	@Order(6)
	void updateUsername() throws CustomerDoesNotExistException{
		Customer customer = cserv.getCustomerById(1);
		customer.setUsername("new user");
		cserv.updateCustomer(customer);
		Assertions.assertEquals("new user", customer.getUsername());
	}
	
	@Test
	@Order(7)
	void deleteCustomer() {
		Customer customer = cserv.getCustomerById(2);
		boolean result = cserv.deleteCustomer(customer);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(8)
	void deleteCustomerFail() throws CustomerDoesNotExistException {
		Account account = new Account(0,"acc",30,1);
		aserv.createAccount(account);
		Customer customer = cserv.getCustomerById(1);
		boolean result = cserv.deleteCustomer(customer);
		Assertions.assertEquals(false, result);
	}
	
	@AfterAll
	@Test
	static void tearDown() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_projectdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
