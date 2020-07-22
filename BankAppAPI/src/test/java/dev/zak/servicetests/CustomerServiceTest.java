package dev.zak.servicetests;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.zak.services.CustomerServiceInterface;
import dev.zak.services.CustomerService;
import dev.zak.entities.Customer;
import dev.zak.utilities.ConnectionUtility;

@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceTest {

	public static CustomerServiceInterface cServ =  new CustomerService();
	
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
	void getAllCustomersNegative() {
		Set<Customer> Customers = cServ.getAllCustomers();
		Assertions.assertEquals(0,Customers.size());
	}
	
	@Test
	@Order(2)
	void createCustomer() {
		Customer c = new Customer(0,"Jon","Jon_password");
		cServ.createCustomer(c);
		Assertions.assertNotEquals(0, c.getcId());
	}
	
	@Test
	@Order(3)
	void getCustomerById() {
		int cid = 1;
		Customer c = cServ.getCustomerById(cid);
		Assertions.assertEquals(cid, c.getcId());
	}
	
	@Test
	@Order(4)
	void getCustomerByIdNegative() {
		int cid = 2;
		Customer c = cServ.getCustomerById(cid);
		Assertions.assertEquals(c, null);
	}
	
	@Test
	@Order(5)
	void getAllCustomers() {
		Customer c = new Customer(0,"Kevin","kevin_password");
		cServ.createCustomer(c);
		Set<Customer> Customers = cServ.getAllCustomers();
		Assertions.assertEquals(2,Customers.size());
	}
	
	@Test
	@Order(6)
	void updateCustomer() {
		String userName = "zak";
		Customer c = new Customer(1,userName, "zak password");
		c = cServ.updateCustomer(c); //saves the changes to that Customer
		Assertions.assertEquals(userName, c.getUsername());
		
	}
	
	@Test
	@Order(7)
	void updateCustomerNegative() {
		int cid = 4;
		Customer c = new Customer(cid,"zak username", "zak password");
		c = cServ.updateCustomer(c);
		Assertions.assertEquals(null, c);
		
	}

	@Test
	@Order(8)
	void deleteCustomer() {
		int cid = 3;
		Customer c = new Customer(0,"zak username", "zak password");
		cServ.createCustomer(c);
		int result = cServ.deleteCustomerById(cid);
		Assertions.assertEquals(1, result);
	}
	
	@Test
	@Order(9)
	void deleteCustomerNegative() {
		int cid = 10;
		int result = cServ.deleteCustomerById(cid);
		Assertions.assertNotEquals(1, result);
	}
	
	@Test
	@Order(10)
	void deleteCustomerByIdWithAllAccount() {
		int cid = 4;
		Customer c = new Customer(0,"zak username", "zak password");
		cServ.createCustomer(c);
		boolean result = cServ.deleteCustomerByIdWithAllAccount(cid);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(11)
	void deleteCustomerByIdWithAllAccountNegative() {
		int cid = 10;
		boolean result = cServ.deleteCustomerByIdWithAllAccount(cid);
		Assertions.assertEquals(false, result);
	}
	
	@AfterAll 
	static void tearDown() {
		try(Connection conn = ConnectionUtility.getConnection()){ 
			String sql ="CALL tear_down_bank_db"; 
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
