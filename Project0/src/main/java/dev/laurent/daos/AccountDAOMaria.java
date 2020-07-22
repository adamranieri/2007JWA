package dev.laurent.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.laurent.entities.Account;
import dev.laurent.utils.ConnectionUtil;

public class AccountDAOMaria implements AccountDAO {
	
	private static AccountDAO adao = null;
	
	private AccountDAOMaria() {
		
	}
	
	public static AccountDAO getAccountDAOMaria() {
		
		if(adao == null) {
			adao = new AccountDAOMaria();
		}
		
		return adao;
	}
	

	@Override
	public Account createAccount(Account account) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO bank_db.account VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setInt(2, account.getcId());
			ps.setString(3, account.getAccName());
			ps.setDouble(4, account.getBalance());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("a_id");
			account.setAccId(key);
			
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Account> getAllAccounts() {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.account";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Set<Account> accounts = new HashSet<Account>();
			
			while(rs.next()) {
				Account account = new Account();
				account.setAccId(rs.getInt("a_id"));
				account.setcId(rs.getInt("c_id"));
				account.setAccName(rs.getString("acc_name"));
				account.setBalance(rs.getDouble("balance"));
				accounts.add(account);
			}
			return accounts;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account getAccountById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Account account = new Account();
			account.setAccId(rs.getInt("a_id"));
			account.setcId(rs.getInt("c_id"));
			account.setAccName(rs.getString("acc_name"));
			account.setBalance(rs.getDouble("balance"));
			
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Account> getAccountsByCustomerId(int id) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			Set<Account> accounts = new HashSet<Account>();
			
			while(rs.next()) {
				Account account = new Account();
				account.setAccId(rs.getInt("a_id"));
				account.setcId(rs.getInt("c_id"));
				account.setAccName(rs.getString("acc_name"));
				account.setBalance(rs.getDouble("balance"));
				accounts.add(account);
			}
			return accounts;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account updateAccount(Account account) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE bank_db.account SET c_id = ?, acc_name = ?, balance = ? WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getcId());
			ps.setString(2, account.getAccName());
			ps.setDouble(3, account.getBalance());
			ps.setInt(4, account.getAccId());
			ps.execute();
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteAccountById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM bank_db.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			if (rows>0) {
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
