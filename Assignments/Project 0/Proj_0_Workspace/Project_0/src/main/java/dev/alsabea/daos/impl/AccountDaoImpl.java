package dev.alsabea.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.alsabea.connectionUtils.ConnectionUtils;
import dev.alsabea.daos.AccountDao;
import dev.alsabea.entities.Account;
import dev.alsabea.exceptions.DaoException;

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

	@Override
	public int create(Account t) {
		final String insertMySql= "INSERT INTO proj_0_db.account (customer_id, account_name, balance)"
				+ " VALUES (?, ?, ?) ";
		Connection con= ConnectionUtils.getConnection();
		ResultSet rs= null;
		int createdAccountId = -1;
		try (PreparedStatement ps= con.prepareStatement(insertMySql, Statement.RETURN_GENERATED_KEYS)){
			ps.setInt(1, t.getCustomerId());
			ps.setString(2, t.getAccountName());
			ps.setInt(3, t.getBalance());
			if (ps.executeUpdate()!= 1)
				throw new DaoException("sql query did not update the expected number of rows");
			rs = ps.getGeneratedKeys();
			rs.next();
			createdAccountId = rs.getInt("account_id");
		} catch (Exception e){
			e.printStackTrace();
		}
		return createdAccountId;
	}

	@Override
	public boolean delete(int id) {
		final String deleteSql= "DELETE FROM proj_0_db.account WHERE account_id = ?";
		
		Connection con= ConnectionUtils.getConnection();
		
		try (PreparedStatement ps= con.prepareStatement(deleteSql)){
			ps.setInt(1,id);
			if (ps.executeUpdate()!= 1)
				throw new DaoException("sql query did not update the expected number of rows");
		} catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Account retrieveById(int id) {
		final String retrieveSql= "SELECT * FROM proj_0_db.account WHERE account_id= ?";
		Connection con= ConnectionUtils.getConnection();
		ResultSet rs= null;
		try (PreparedStatement ps= con.prepareStatement(retrieveSql)){
			ps.setInt(1, id);
			rs= ps.executeQuery();
			rs.next();
		} catch (Exception e){
			e.printStackTrace();
		}

		return extractFromRs(rs);
	}

	@Override
	public boolean update(Account t) {
		
		int check=-1;
		
		final String updateSql="UPDATE proj_0_db.account "
				+ "SET customer_id= ? , account_name= ?, balance = ? WHERE account_id= ?;";

		Connection con= ConnectionUtils.getConnection();

		try (PreparedStatement ps= con.prepareStatement(updateSql)){
			ps.setInt(1,t.getCustomerId());
			ps.setString(2, t.getAccountName());
			ps.setInt(3, t.getBalance());
			ps.setInt(4, t.getAccountId());
			check= ps.executeUpdate();
			if (check!= 1) {
				System.out.println();
				throw new DaoException("sql query did not update the expected number of rows");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<Account> getAllCustomerAccounts(int id) {
		final String sqlStmt= "SELECT * FROM proj_0_db.account WHERE customer_id = ?";
		
		Connection con= ConnectionUtils.getConnection();
		ResultSet rs= null;
		List<Account> accts= new ArrayList<>();
		
		try (PreparedStatement ps= con.prepareStatement(sqlStmt)){
			ps.setInt(1, id);
			rs= ps.executeQuery();
			
			while (rs.next()) {
				accts.add(extractFromRs(rs));
			}

			
		} catch (Exception e){
			e.printStackTrace();
		}

		return accts;
	}

	private Account extractFromRs(ResultSet rs) {
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
