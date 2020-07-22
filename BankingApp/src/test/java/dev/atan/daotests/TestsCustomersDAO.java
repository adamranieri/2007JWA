package dev.atan.daotests;

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
import dev.atan.daos.CustomerDAO;
import dev.atan.daos.CustomerDAOMaria;
import dev.atan.entities.Customer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
class TestsCustomersDAO {
	
	public static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();
	
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
		Customer john = new Customer(0, "John", "PassJ", 0);
		cdao.createCustomer(john);
		Assertions.assertNotEquals(0, john.getcID());
		}
	
	@Test
	@Order(2)
	void createCustomer2() {
		Customer sia = new Customer(0, "Sia", "PassS", 0);
		cdao.createCustomer(sia);
		Assertions.assertNotEquals(0, sia.getcID());
		}
	
	@Test
	@Order(3)
	void createCustomer3() {
		Customer mark = new Customer(0, "Mark", "PassM", 0);
		cdao.createCustomer(mark);
		Assertions.assertNotEquals(0, mark.getcID());
		}
	
	@Test
	@Order(4)
	void createCustomer4() {
		Customer sean = new Customer(0, "Sean", "PassN", 0);
		cdao.createCustomer(sean);
		Assertions.assertNotEquals(0, sean.getcID());
		}
	
	@Test
	@Order(5)
	void getCustomerById() {		
		Customer john = cdao.getCustomerById(1);
		Assertions.assertEquals(1, john.getcID());
	}
	
	@Test
	@Order(10)
	void getAllCustomer() {
		List<Customer> customers = cdao.getAllCustomers();
		Assertions.assertNotEquals(0,customers.size());
	}
	
	@Test
	@Order(20)
	void updateCustomer() {
		Customer john = cdao.getCustomerById(1);
		john.setUserName("Johnatan");
		john = cdao.updateCustomer(john);
		Assertions.assertEquals("Johnatan", john.getUserName());	
	}
	
	@Test
	@Order(21)
	void updateCustomer2() {
		Customer john = cdao.getCustomerById(1);
		john.setOpenAccounts(2);
		john = cdao.updateCustomerAccounts(john);
		Assertions.assertEquals(2, john.getOpenAccounts());	
	}
	
	@Test
	@Order(22)
	void updateCustomer3() {
		Customer sia = cdao.getCustomerById(2);
		sia.setOpenAccounts(3);
		sia = cdao.updateCustomer(sia);
		Assertions.assertEquals(3, sia.getOpenAccounts());	
	}
	
	@Test
	@Order(30)
	void deleteCustomerById() {
		boolean result = cdao.deleteCustomerById(2);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(31)
	void deleteCustomerByUserName() {
		boolean result = cdao.deleteCustomerByUserName("Sean");
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(40)
	void getAllCustomer2() {
		List<Customer> customers = cdao.getAllCustomers();
		Assertions.assertNotEquals(0,customers.size());
	}
	
	@AfterAll
	static void clearDown() {
		
		try(Connection conn = dev.atan.utility.ConnectionUtil.getConnection()){
			String sql = "CALL CLEAR_BANK_DB";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	} 

}
