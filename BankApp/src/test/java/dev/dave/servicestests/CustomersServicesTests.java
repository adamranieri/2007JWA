package dev.dave.servicestests;

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

import dev.dave.entities.Customers;
import dev.dave.services.CustomersServices;
import dev.dave.services.CustomersServicesImpl;
import dev.dave.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)

class CustomersServicesTests {
	
	private static CustomersServices cserv = CustomersServicesImpl.getCustomersServices();

	// to set up our DB tables
	
		@BeforeAll
		static void setUp() {
			
			try(Connection conn = ConnectionUtil.getConnection()){
				String sql = "CALL set_up_bank_db";
				CallableStatement cs = conn.prepareCall(sql);
				cs.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	@Test
	@Order(1)
	void CustomerSignUp() {
		Customers babyface = new Customers(0, "babyface07", "Produce101");
		cserv.CustomerSignUp(babyface);
		Assertions.assertNotEquals(0, babyface.getcID());
	}
	
	@Test
	@Order(2)
	void GetCustInfo() {
		Customers babyface = cserv.GetCustInfo(1);
		Assertions.assertEquals(1, babyface.getcID());
	}
	
	@Test
	@Order(3)
	void SearchCustomerByName() {
		Customers babyface = cserv.SearchCustomerByName("babyface07");
		Assertions.assertEquals("babyface07", babyface.getUsername());
	}
	
	@Test
	@Order(4)
	void updateCustomer() {
		Customers babyface = cserv.GetCustInfo(1);
		babyface.setUsername("Whitney80s");
		babyface = cserv.updateCustomer(babyface);
		Assertions.assertEquals("Whitney80s", babyface.getUsername());
		
	}
	
	@Test
	@Order(5)
	void ChangeCustName() {
		Customers babyface = cserv.GetCustInfo(1);
		cserv.ChangeCustName(babyface, "TheNeptunes");
		Assertions.assertEquals("TheNeptunes", babyface.getUsername());
	}
	
	@Test
	@Order(6)
	void ChangePassword() {
		Customers babyface = cserv.GetCustInfo(1);
		cserv.ChangePassword(babyface, "#SaySomeGood");
		Assertions.assertEquals("#SaySomeGood", babyface.getPassword());
	}
	
	@Test
	@Order(7)
	void DeleteProfile() {
		boolean isDeleted = cserv.DeleteProfile(1);
		Assertions.assertEquals(true, isDeleted);
	}
	
	@AfterAll
	static void tearDown() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_bank_db";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
