package dev.kyle.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.kyle.entities.Account;
import dev.kyle.utils.ConnectionUtil;

public class AccountDAOMaria implements AccountDAO {

	private static AccountDAO adao = null;
	
	private AccountDAOMaria() {}
	
	public static AccountDAO getAccountDAOMaria() {
		if(adao == null) {
			adao = new AccountDAOMaria();
			return adao;
		} else {
			return adao;
		}
	}
	
	@Override
	public Account createAccount(Account a) {
		try(Connection conn = ConnectionUtil.getConnection() ) {
			String sql = "INSERT INTO bankdb.ACCOUNT VALUES(?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setInt(2, a.getCid());
			ps.setString(3, a.getAccName());
			ps.setDouble(4, a.getBalance());
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("a_id");
			
			a.setAid(key);
			
			return a;
						
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public Account getAccountById(int aid) {
		try(Connection conn = ConnectionUtil.getConnection() ) {
			String sql = "SELECT * FROM bankdb.ACCOUNT WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aid);
			
			ResultSet rs = ps.executeQuery(); 
			rs.next();
			
			Account a = new Account();
			a.setAid(rs.getInt("a_id"));
			a.setCid(rs.getInt("c_id"));
			a.setAccName(rs.getString("accountname"));
			a.setBalance(rs.getDouble("balance"));
			
			return a;
						
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Account> getAllAccounts() {
		try(Connection conn = ConnectionUtil.getConnection() ) {
			String sql = "SELECT * FROM bankdb.ACCOUNT";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Set<Account> accounts = new HashSet<Account>();
			
			while(rs.next()) {
				Account a = new Account();
				a.setAid(rs.getInt("a_id"));
				a.setCid(rs.getInt("c_id"));
				a.setAccName(rs.getString("accountname"));
				a.setBalance(rs.getDouble("balance"));
				accounts.add(a);
			}
			return accounts;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}

	@Override
	public Set<Account> getAccountsByCustomerId(int id) {
		try(Connection conn = ConnectionUtil.getConnection() ) {
			String sql = "SELECT * FROM bankdb.ACCOUNT WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			Set<Account> accounts = new HashSet<Account>();
			
			while(rs.next()) {
				Account a = new Account();
				a.setAid(rs.getInt("a_id"));
				a.setCid(rs.getInt("c_id"));
				a.setAccName(rs.getString("accountname"));
				a.setBalance(rs.getDouble("balance"));
				accounts.add(a);
			}
			return accounts;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account updateAccount(Account a) {
		try(Connection conn = ConnectionUtil.getConnection() ) {
			String sql = "UPDATE bankdb.ACCOUNT SET accountname = ?, balance = ? WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, a.getAccName());
			ps.setDouble(2, a.getBalance());
			ps.setInt(3, a.getAid());
			
			ps.execute();
			
			return a;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteAccount(int aid) {
		try(Connection conn = ConnectionUtil.getConnection() ) {
			String sql = "DELETE FROM bankdb.ACCOUNT WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aid);
			int rows = ps.executeUpdate();
			if (rows > 0) {
				return true;
			} else {
				return false;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
