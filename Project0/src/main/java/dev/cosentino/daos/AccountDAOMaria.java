package dev.cosentino.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.cosentino.resources.Account;
import dev.cosentino.utils.ConnectionUtil;

public class AccountDAOMaria implements AccountDAO{
	
	public static AccountDAO adao = null;
	
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
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO project_zero_db.account VALUES (?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setString(2, account.getAccountName());
			ps.setFloat(3, account.getBalance());
			ps.setInt(4, account.getCustomerId());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("account_id");
			account.setAccountId(key);
			return account;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account getAccountById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM project_zero_db.account WHERE account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Account account = new Account();
			account.setAccountId(rs.getInt("account_id"));
			account.setAccountName(rs.getString("accountName"));
			account.setBalance(rs.getFloat("balance"));
			account.setCustomerId(rs.getInt("customer_id"));
			
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Account> getAllAccounts() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM project_zero_db.account";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Set<Account> accounts = new HashSet<Account>();
			
			while(rs.next()) {
				Account account = new Account();
				account.setAccountId(rs.getInt("account_id"));
				account.setAccountName(rs.getString("accountName"));
				account.setBalance(rs.getFloat("balance"));
				account.setCustomerId(rs.getInt("customer_id"));
				accounts.add(account);
			}
			return accounts;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account updateAccount(Account account) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE project_zero_db.account SET accountName =?, balance =?, customer_id =? WHERE account_id =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, account.getAccountName());
			ps.setFloat(2, account.getBalance());
			ps.setInt(3, account.getCustomerId());
			ps.setInt(4, account.getAccountId());
			ps.execute();
			
			return account;
		
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteAccount(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM project_zero_db.account WHERE account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			
			if(rows > 0) {
				return true;
			}
			else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Set<Account> getAccountsByCustomerId(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM project_zero_db.account WHERE customer_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			Set<Account> accounts = new HashSet<Account>();
			
			while(rs.next()) {
				Account account = new Account();
				account.setAccountId(rs.getInt("account_id"));
				account.setAccountName(rs.getString("accountName"));
				account.setBalance(rs.getFloat("balance"));
				account.setCustomerId(rs.getInt("customer_id"));
				accounts.add(account);
			}
			return accounts;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
