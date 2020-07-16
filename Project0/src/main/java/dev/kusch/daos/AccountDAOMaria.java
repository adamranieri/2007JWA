package dev.kusch.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.kusch.entities.Account;
import dev.kusch.utils.ConnectionUtil;

public class AccountDAOMaria implements AccountDAO {
	
	public static AccountDAO sdao = null;
	
	private AccountDAOMaria() {
		
	}
	
	public static AccountDAO getAccountDAOMaria() {
		if (sdao == null) {
			sdao = new AccountDAOMaria();
		}
		return sdao;
	}

	@Override
	public Account createAccount(Account account) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO bankAPI_db.account VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setInt(2, account.getcId());
			ps.setString(3, account.getName());
			ps.setDouble(4, account.getBalance());
			
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("a_id");
			account.setaId(key);
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account getAccountById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bankAPI_db.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Account account = new Account();
			account.setaId(rs.getInt("a_id"));
			account.setName(rs.getString("name"));
			account.setBalance(rs.getInt("balance"));
			
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Account> getAllAccounts() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bankAPI_db.account";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			Set<Account> accounts = new HashSet<Account>();
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Account account = new Account();
				account.setaId(rs.getInt("a_id"));
				account.setName(rs.getString("name"));
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
	public Account updateAccount(Account account) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE bankAPI_db.account SET name = ?, Balance = ? WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, account.getName());
			ps.setDouble(2, account.getBalance());
			ps.setInt(3,  account.getaId());
			
			ps.execute();
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteAccount(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM bankAPI_db.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			
			return ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}