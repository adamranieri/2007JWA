package dev.nauman.servicetests;

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

import dev.nauma.utils.ConnectionUtil;
import dev.nauman.entities.Customer;
import dev.nauman.services.CustomerService;
import dev.nauman.services.CustomerServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
class CustomerServicesTests {

	private static CustomerService cserv= new CustomerServiceImpl();
	
	@BeforeAll
	static void setUp() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "CALL set_up_projectzerodb()";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void createCustomerTest1() {
		Customer customer1 = new Customer(0, "frodo99","ohSam333");
		Customer customer2 = new Customer(0, "sam101","gafferRULES");
		Customer customer3 = new Customer(0, "stewardofgondor","farmerMaggot13");
		Customer customer4 = new Customer(0, "knightofrohan","IAMTALLERTHANPIPPIN");
		
		cserv.createCustomer(customer1);
		cserv.createCustomer(customer2);
		cserv.createCustomer(customer3);
		cserv.createCustomer(customer4);
		
		Assertions.assertNotEquals(0, customer1.getcId());
		Assertions.assertNotEquals(0, customer2.getcId());
		Assertions.assertNotEquals(0, customer3.getcId());
		Assertions.assertNotEquals(0, customer4.getcId());
	}
	@Test
	@Order(2)
	void getCustomerByCIdTest() {
		Customer customer = cserv.getCustomerByCId(2);
		
		Assertions.assertEquals(2, customer.getcId());
	}
	@Test
	@Order(3)
	void getAllCustomerTest() {
		Set<Customer> customers = cserv.getAllCustomers();
		
		Assertions.assertEquals(4, customers.size());
	}
	@Test
	@Order(4)
	void updateCustomerTest() {
		Customer cs = new Customer(1, "Mr. UnderHill", "ohSam333");
		cs= cserv.updateCustomer(cs);
		
		Assertions.assertEquals("Mr. UnderHill", cs.getUsername());
	}
	@Test
	@Order(5)
	void changePasswordTest() {
		cserv.changePassword(1, "thereAndBackAgain");
		Customer customer = cserv.getCustomerByCId(1);
		
		Assertions.assertEquals("thereAndBackAgain",customer.getPassword());
	}
	@Test
	@Order(6)
	void changeUsernameTest() {
		cserv.changeUsername(3, "Peregrin Took");
		Customer customer = cserv.getCustomerByCId(3);
		
		Assertions.assertEquals("Peregrin Took",customer.getUsername());
	}
	@Test
	@Order(7)
	void deleteCustomerByIdNegativeTest() {
		boolean result = cserv.deleteCustomerByCId(14);
		
		Assertions.assertEquals(false, result);
	}
	@Test
	@Order(8)
	void deleteCustomerByIdPositiveTest() {
		boolean result = cserv.deleteCustomerByCId(1);
		
		Assertions.assertEquals(true, result);
	}
	
	@AfterAll
	static void tearDown() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_projectzerodb()";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}