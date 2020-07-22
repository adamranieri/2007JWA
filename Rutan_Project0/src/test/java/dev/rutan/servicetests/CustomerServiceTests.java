package dev.rutan.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import dev.rutan.entities.Account;
import dev.rutan.entities.Customer;
import dev.rutan.services.AccountService;
import dev.rutan.services.AccountServiceImpl;
import dev.rutan.services.CustomerService;
import dev.rutan.services.CustomerServiceImpl;
import dev.rutan.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceTests {

	private static CustomerService cserv = new CustomerServiceImpl();
	private static AccountService aserv = new AccountServiceImpl();
	
	@BeforeAll
	static void setUp() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_bank_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void createCustomer() {
		Customer customer = new Customer(0, "George", "password");
		Account account = new Account(0, 1, "Savings", 50);
		cserv.createCustomer(customer);
		aserv.createAccount(account);
		Assertions.assertEquals(1, customer.getcId());
	}
	
	@Test
	@Order(2)
	void getCustomerByCustomerId() {
		Customer customer = cserv.getCustomerById(1);
		Assertions.assertEquals(1, customer.getcId());
		Assertions.assertEquals(1, customer.getAccounts().size());
	}
	
	@Test
	@Order(3)
	void getAllCustomers() {
		Customer customer = new Customer(0, "Norman", "pass");
		Customer customer2 = new Customer(0, "Tim", "better password");
		
		cserv.createCustomer(customer);
		cserv.createCustomer(customer2);
		Account account = new Account(0, 2, "Savings", 50);
		aserv.createAccount(account);
		
		Set<Customer> customers = cserv.getAllCustomers();
		Assertions.assertEquals(3, customers.size());
	}

	@Test
	@Order(4)
	void updateCustomer() {
		Customer customer = new Customer(1, "George", "safer password");
		cserv.updateCustomer(customer);
		Assertions.assertEquals("safer password", customer.getPassword());
	}
	
	@Test
	@Order(5)
	void deleteCustomer() {
		boolean result = cserv.deleteCustomer(1);
		Assertions.assertEquals(true, result);
	}
	
	
	@Test
	@Order(6)
	void getAllCustomersByUsername() {
		Set<Customer> customers = cserv.getAllCustomersByUsername("Norman");
		for(Customer customer: customers) {
			Assertions.assertEquals("Norman", customer.getUsername());
		}
		Assertions.assertEquals(1, customers.size());
	}
	
	
	@AfterAll
	static void endTest() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
