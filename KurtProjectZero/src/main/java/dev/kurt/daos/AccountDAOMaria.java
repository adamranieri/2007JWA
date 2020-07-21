package dev.kurt.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.kurt.entities.Account;
import dev.kurt.utils.ConnectionUtil;

public class AccountDAOMaria implements AccountDAO{

	
	private static AccountDAO acdao = null;

	private AccountDAOMaria() {
	}

	public static AccountDAO getAccountDAOMaria() {

		if (acdao == null) {
			acdao = new AccountDAOMaria();
		}

		return acdao;
	}
	
	@Override
	public Account createAccount(Account account) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO KurtBankDB.account VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setString(2, account.getAccountName());
			ps.setDouble(3, account.getAccountBalance());
			ps.setInt(4, account.getcId());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys(); // returns a virtual table
			rs.next();// moves you to the first record in that table
			int key = rs.getInt("a_id");
			account.setaId(key);
			
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Account> getAllAccounts() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM KurtBankDB.account";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Set<Account> accounts = new HashSet<Account>();
			
			while(rs.next()) {
				
				Account account = new Account();
				account.setaId(rs.getInt("a_id"));
				account.setAccountName(rs.getString("account_name"));
				account.setAccountBalance(rs.getDouble("account_balance"));
				account.setcId(rs.getInt("c_id"));				
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
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM KurtBankDB.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery(); // get us back a table with all the information
			rs.next();// move table to first record
			
			Account account = new Account();
			account.setaId(rs.getInt("a_id"));
			account.setAccountName(rs.getString("account_name"));
			account.setAccountBalance(rs.getDouble("account_balance"));
			account.setcId(rs.getInt("c_id"));
			
			return account;
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}

	@Override
	public Set<Account> getAccountsByCustomerId(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM KurtBankDB.account WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			Set<Account> accounts = new HashSet<Account>();
			
			while(rs.next()) {
				
				Account account = new Account();
				account.setaId(rs.getInt("a_id"));
				account.setAccountName(rs.getString("account_name"));
				account.setAccountBalance(rs.getDouble("account_balance"));
				account.setcId(rs.getInt("c_id"));				
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
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE KurtBankDB.account SET account_name = ?, account_balance = ? , c_id = ? WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, account.getAccountName());
			ps.setDouble(2, account.getAccountBalance());
			ps.setInt(3, account.getcId());
			ps.setInt(4, account.getaId());
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
			String sql = "DELETE FROM KurtBankDB.account WHERE a_id =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			if(rows>0) {
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
