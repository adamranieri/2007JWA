package dev.alsabea.daos;

import java.sql.ResultSet;
import java.sql.SQLException;

import dev.alsabea.entities.Account;

public interface AccountDao extends CRUD<Account>{

	static Account extractFromRs(ResultSet rs) {
		Account a = new Account();
		
		try {
			a.setAccountId(rs.getInt("account_id"));
			a.setCustomerId(rs.getInt("customer_id"));
			a.setAccountName(rs.getString("account_name"));
			a.setBalance(rs.getInt("balance"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

}
