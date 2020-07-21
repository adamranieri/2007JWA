package dev.noah.daotests;

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

import dev.noah.daos.CustomerDAO;
import dev.noah.daos.CustomerDAOMaria;
import dev.noah.entities.Customer;
import dev.noah.utils.ConnectionUtil;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class CustomerDAOTests {

	public static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();

	@BeforeAll
	static void initialSetup() { 
		try(Connection con = ConnectionUtil.getConnection()){
			String sql = "CALL create_bank_tables";
			CallableStatement cs = con.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	// Positive Tests

	@Test
	@Order(1)
	void createCustomer() {
		Customer cus = new Customer(0, "TheLegend27", "SuperCoolPasswrod");
		cdao.createCustomer(cus);
		Assertions.assertEquals(1, cdao.getCustomerBycId(1).getcId());
													
	}

	@Test
	@Order(2)
	void getCustomerById() {

		Customer cusTest = cdao.getCustomerBycId(1);
		Assertions.assertEquals(1, cusTest.getcId());
	}

	@Test()
	@Order(3)
	void updateCustomer() {
		Customer cus = cdao.getCustomerBycId(1);
		cus.setUsername("TommyJohnson");
		cus = cdao.updateCustomer(cus);
		
		Assertions.assertEquals("TommyJohnson", cus.getUsername());
	}

	@Test()
	@Order(4)
	void getAllCustomers() {
		Customer cus = new Customer(0, "March111", "somekindofpassword");
		cdao.createCustomer(cus);
		cus = new Customer(0, "jeremy37", "passwordsLOL");
		cdao.createCustomer(cus);
		
		Set<Customer> allCustomers = cdao.getAllCustomers();
		Assertions.assertEquals(3, allCustomers.size());
	}
	
	@Test
	@Order(5)
	void deleteCustomerTest() {
		boolean cus = cdao.deleteCustomer(2);
		Assertions.assertEquals(true, cus);
	}
	
	
	@AfterAll
	static void cleanup() {
		try(Connection con = ConnectionUtil.getConnection()){
			String sql = "CALL drop_bank_tables";
			CallableStatement cs = con.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
