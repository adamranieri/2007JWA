package dev.alsabea.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dev.alsabea.entities.Account;
import dev.alsabea.entities.Customer;

public interface CustomerDao extends CRUD<Customer>{

	
	List<Account> getAccounts ( int id);
	
	int getIdByUsernameAndPassword(String username, String password);  //mainly used for testing purposes
	
	
	static Customer extractFromRs(ResultSet rs) {
		Customer c = new Customer();
		
		try {
			c.setCustomerId(rs.getInt("customer_id"));
			c.setUsername(rs.getString("username"));
			c.setPassword(rs.getString("password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return c;
	}
}
