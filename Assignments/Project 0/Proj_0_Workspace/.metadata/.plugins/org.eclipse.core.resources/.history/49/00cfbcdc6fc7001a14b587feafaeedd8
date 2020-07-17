package dev.alsabea.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dev.alsabea.connectionUtils.ConnectionUtils;
import dev.alsabea.daos.AccountDao;
import dev.alsabea.daos.AccountDao;
import dev.alsabea.entities.Account;
import dev.alsabea.exceptions.DaoException;
import dev.alsabea.entities.Account;

public class AccountDaoImpl implements AccountDao{

private static AccountDaoImpl dao = null;
	
	private AccountDaoImpl() {
		
	};
	
	public static AccountDao getAccountDao() {
		
		if(dao == null) {
			dao = new AccountDaoImpl();
			return dao;
		}else {
			return dao;
		}
		
	}

	
	 private static final String insertSQL = "INSERT INTO account (customer_id, "
	 		+ "account_name, balance) VALUES (?, ?, ?);";

	@Override
	public boolean create(Account t) {
		final String insertMySql= "insert into proj_0_db.account (customer_id, account_name, ) values (?, ?) ";
		Connection con= ConnectionUtils.getConnection();
		
		try (PreparedStatement ps= con.prepareStatement(insertMySql)){
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			if (ps.executeUpdate()!= 1)
				throw new DaoException("sql query did not update the expected number of rows");
		} catch (Exception e){
			e.printStackTrace();
		}
		return true;
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account retrieveById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Account t) {
		// TODO Auto-generated method stub
		return false;
	}

	

	
	
}
