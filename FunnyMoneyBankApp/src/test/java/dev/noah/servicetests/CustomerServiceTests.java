package dev.noah.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


import dev.noah.entities.Customer;
import dev.noah.services.CustomerService;
import dev.noah.services.CustomerServiceImpl;
import dev.noah.utils.ConnectionUtil;

import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceTests {

	public static CustomerService cserv = new CustomerServiceImpl();
	
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
	
	//Positive Tests
	
	@Test
	@Order(1)
	void createCustomer() {
		Customer cus = new Customer(0,"TheCoolUser", "Bigbadpassword"); 
		cserv.createCustomer(cus);
		Assertions.assertEquals(1, cus.getcId());
	}
	
	@Test
	@Order(2)
	void getAllCustomers() {
		Customer cus = new Customer(0,"sdfdssfsf", "sdfsdsfdffsd");
		cserv.createCustomer(cus);
		cus = new Customer(0,"asdadasdad", "asdadaddada");
		cserv.createCustomer(cus);
		cus = new Customer(0,"adadasdadasdad", "fsfdfsfsdfsdfsd");
		cserv.createCustomer(cus);
		cus = new Customer(0, "sfsdfsdfsfsdfs", "sffdsfsdfsd");
		cserv.createCustomer(cus);
		Set<Customer> getCustomers = cserv.getAllCustomers();
		Assertions.assertEquals(5, getCustomers.size());
	}
	
	@Test
	@Order(3)
	void getCustomer() {
		Customer cus = cserv.getCustomerByCId(4);
		Assertions.assertEquals(4,cus.getcId());
	}

	
	@Test
	@Order(4)
	void updateCustomer() {
		Customer cus = cserv.getCustomerByCId(1);
		cus.setUsername("SummerSalt");
		cserv.updateCustomer(cus);
		
		Assertions.assertEquals("SummerSalt", cserv.getCustomerByCId(1).getUsername());
	}


	
	@Test
	@Order(6)
	void deleteCustomerByCId() {
		boolean result = cserv.deleteCustomerByCId(3);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(7)
	void GetCustomerByUsername() {
		Customer cus = cserv.getCustomerByCId(1);
		Assertions.assertEquals("SummerSalt", cserv.getCustomerByUsername(cus.getUsername()).getUsername());
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
