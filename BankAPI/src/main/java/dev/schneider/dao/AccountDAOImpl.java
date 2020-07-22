package dev.schneider.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.schneider.entities.Account;
import dev.schneider.entities.Customer;
//import dev.schneider.utils.ConnectionUtil;
import dev.schneider.utils.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {

	private static AccountDAOImpl dao = null;
	
	private AccountDAOImpl() {
		
	}
	
	public static AccountDAO getAccountDAO() {
		if (dao == null) {
			AccountDAO dao = new AccountDAOImpl();
			return dao;
		}
		else {
			return dao;
		}
	}
	
	@Override
	public Account createAccount(Account account) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO BankDB.account VALUES(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setInt(2, account.getcID());
			ps.setString(3, account.getAcctName());
			ps.setInt(4, account.getBalance());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next(); 
			int key = rs.getInt("aID");
			account.setAcctID(key);
			
			return account;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Set<Account> getAllCustomerAccounts(int cID) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM BankDB.account WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cID);

			ResultSet rs = ps.executeQuery();
			
			Set<Account> accounts = new HashSet<Account>();
			
			while(rs.next()) {
				Account account = new Account();
				account.setAcctID(rs.getInt("a_id"));
				account.setcID(rs.getInt("c_id"));
				account.setAcctName(rs.getString("name"));
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
	public Account getAccountByAcctID(int acctID) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM BankDB.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acctID);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Account account = new Account();
			account.setAcctID(rs.getInt("a_id"));
			account.setcID(rs.getInt("c_id"));
			account.setAcctName(rs.getString("name"));
			account.setBalance(rs.getInt("balance"));
			
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account updateAccount(Account account) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE BankDB.account SET name = ?, balance = ? WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, account.getAcctName());
			ps.setInt(2, account.getBalance());
			ps.setInt(3, account.getAcctID());
			ps.execute();	
			return account;				
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteAccount(int acctID) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM BankDB.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acctID);
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
