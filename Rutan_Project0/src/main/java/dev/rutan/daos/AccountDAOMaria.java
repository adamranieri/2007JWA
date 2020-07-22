package dev.rutan.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.rutan.entities.Account;
import dev.rutan.utils.ConnectionUtil;

public class AccountDAOMaria implements AccountDAO{

	private static AccountDAO adao = null;
	
	private AccountDAOMaria() {
		
	}

	public static AccountDAO getAccountDAO() {
		if (adao == null)
			adao = new AccountDAOMaria();
		return adao;
	}
	
	public Account createAccount(Account account) {
		// try with resources. Will auto close the connection for us when we finish out interaction
				try(Connection conn = ConnectionUtil.getConnection()){
					String sql = "INSERT INTO bank_db.account VALUES (?,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, 0);
					ps.setInt(2, account.getcId());
					ps.setString(3, account.getAccountName());
					ps.setInt(4, account.getBalance());
					// leave with empty parameters
					ps.execute();
					
					ResultSet rs = ps.getGeneratedKeys();
					rs.next();
					int key = rs.getInt("s_id");
					account.setaId(key);
					
					return account;
					
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
	}

	public Account getAccountById(int aId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aId);
			
			ResultSet rs = ps.executeQuery();
			
			Account account = new Account();

			while(rs.next()) {	
				account.setaId(rs.getInt("a_id"));
				account.setcId(rs.getInt("c_id"));
				account.setAccountName(rs.getString("account_name"));
				account.setBalance(rs.getInt("balance"));
			}			
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Set<Account> getAllAccounts() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.account";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			Set<Account> accounts = new HashSet<Account>();
			
			while(rs.next()) {		
				Account account = new Account();
				account.setaId(rs.getInt("a_id"));
				account.setcId(rs.getInt("c_id"));
				account.setAccountName(rs.getString("account_name"));
				account.setBalance(rs.getInt("balance"));
				accounts.add(account);
			}
			return accounts;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Set<Account> getAllAccountsByCustomerId(int cId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.account WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cId);
			
			ResultSet rs = ps.executeQuery();
			Set<Account> accounts = new HashSet<Account>();
			
			while(rs.next()) {		
				Account account = new Account();
				account.setaId(rs.getInt("a_id"));
				account.setcId(rs.getInt("c_id"));
				account.setAccountName(rs.getString("account_name"));
				account.setBalance(rs.getInt("balance"));
				accounts.add(account);
			}
			return accounts;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Account updateAccount(Account account) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE bank_db.account SET c_id = ?, account_name = ?, balance = ? WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getcId());
			ps.setString(2, account.getAccountName());
			ps.setInt(3, account.getBalance());
			ps.setInt(4, account.getaId());
			
			ps.execute();

			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteAccount(int aId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM bank_db.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aId);

			int rows = ps.executeUpdate();
			if(rows>0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteAllAccountsByCustomerId(int cId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM bank_db.account WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cId);

			int rows = ps.executeUpdate();
			if(rows>0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
