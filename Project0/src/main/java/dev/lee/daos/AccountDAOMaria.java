package dev.lee.daos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.lee.entities.Account;
import dev.lee.exceptions.NegativeBalanceException;
import dev.lee.utils.ConnectionUtil;

public class AccountDAOMaria implements AccountDAO{

	private static AccountDAO adao = null;
	
	private AccountDAOMaria() {}

	public static AccountDAO getAccountDAOMaria() {
		if (adao == null) {
			adao = new AccountDAOMaria();
		}
		return adao;
	}
	
	@Override
	public Account createAccount(Account account) throws NegativeBalanceException{
		
		if (account.getBalance().compareTo(new BigDecimal("0")) == -1) {
			throw new NegativeBalanceException();
		}
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO bank_db.account VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setInt(2, account.getcID());
			ps.setString(3, account.getAccountName());
			ps.setBigDecimal(4, account.getBalance());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("a_id");
			account.setaID(key);
			
			return account;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account getAccountByAccountId(int aId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aId);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Account account = new Account();
			account.setaID(rs.getInt("a_id"));
			account.setcID(rs.getInt("c_id"));
			account.setAccountName(rs.getString("account_name"));
			account.setBalance(rs.getBigDecimal("balance"));
			
			return account;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Set<Account> getAccountsByCustomerId(int cId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.account WHERE c_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cId);
			
			ResultSet rs = ps.executeQuery();
			
			Set<Account> accounts = new HashSet<Account>();
			while(rs.next()) {
				Account account = new Account();
				account.setaID(rs.getInt("a_id"));
				account.setcID(rs.getInt("c_id"));
				account.setAccountName(rs.getString("account_name"));
				account.setBalance(rs.getBigDecimal("balance"));
				accounts.add(account);
			}
			
			return accounts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Set<Account> getAccountsFilteredByBalance(int cId, String lower, String upper) {
		String sql;
		int type;
		
		if (cId != -1) {
			if(upper.equals("-1") && !lower.equals("-1")) {  		// Lower limit only
				sql = "SELECT * FROM  bank_db.account WHERE c_id= ? AND balance > ?";
				type = 1;
			}else if(lower.equals("-1") && !upper.equals("-1")){ 	// Upper limit only
				sql = "SELECT * FROM  bank_db.account WHERE c_id= ? AND balance < ?";
				type = 2;
			}else if(!lower.equals("-1") && !upper.equals("-1")){
				sql = "SELECT * FROM  bank_db.account WHERE c_id= ? AND balance > ? AND balance < ?";
				type = 3;
			}else {
				sql = "SELECT * FROM  bank_db.account WHERE c_id= ?";
				type = 4;
			}
		} else { 
			if(upper.equals("-1") && !lower.equals("-1")) {  		// Lower limit only
				sql = "SELECT * FROM  bank_db.account WHERE balance > ?";
				type = 5;
			}else if(lower.equals("-1") && !upper.equals("-1")){ 	// Upper limit only
				sql = "SELECT * FROM  bank_db.account WHERE balance < ?";
				type = 6;
			}else if(!lower.equals("-1") && !upper.equals("-1")){
				sql = "SELECT * FROM  bank_db.account WHERE balance > ? AND balance < ?";
				type = 7;
			}else {
				sql = "SELECT * FROM  bank_db.account";
				type = 8;
			}
		}

		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			switch (type) {
			case 1:	
				ps.setInt(1, cId);
				ps.setString(2, lower);
				break;
			case 2:
				ps.setInt(1, cId);
				ps.setString(2, upper);
				break;
			case 3:
				ps.setInt(1, cId);
				ps.setString(2, lower);
				ps.setString(3, upper);
				break;
			case 4:
				ps.setInt(1, cId);
				break;
			case 5:
				ps.setString(1, lower);
				break;
			case 6:
				ps.setString(1, upper);
				break;
			case 7:
				ps.setString(1, lower);
				ps.setString(2, upper);
				break;
			case 8:
				break;
			}
			
			ResultSet rs = ps.executeQuery(); 
			
			Set<Account> accounts = new HashSet<Account>();
			
			while(rs.next()) {
				Account account = new Account();
				account.setaID(rs.getInt("a_id"));
				account.setcID(rs.getInt("c_id"));
				account.setAccountName(rs.getString("account_name"));
				account.setBalance(rs.getBigDecimal("balance"));
				accounts.add(account);
			}
			return accounts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Set<Account> getAllAccounts() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.account";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Set<Account> accounts = new HashSet<Account>();
			
			while(rs.next()) {
				Account account = new Account();
				account.setaID(rs.getInt("a_id"));
				account.setcID(rs.getInt("c_id"));
				account.setAccountName(rs.getString("account_name"));
				account.setBalance(rs.getBigDecimal("balance"));
				accounts.add(account);
			}
			
			return accounts;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account updateAccount(Account account) throws NegativeBalanceException{
		BigDecimal zero = new BigDecimal("0");
		if (account.getBalance().compareTo(zero)== -1) {
			throw new NegativeBalanceException();
		}
		
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE bank_db.account SET c_id = ?, account_name = ?, balance = ? WHERE a_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getcID());
			ps.setString(2, account.getAccountName());
			ps.setBigDecimal(3, account.getBalance());
			ps.setInt(4, account.getaID());
			
			ps.execute();
			
			return account;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean deleteAccount(int aId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM bank_db.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aId);
			
			int rows = ps.executeUpdate();
			
			return rows != 0; 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
