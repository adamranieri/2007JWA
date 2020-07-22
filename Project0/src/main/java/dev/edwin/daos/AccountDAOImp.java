package dev.edwin.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.edwin.entities.Account;
import dev.edwin.utils.ConnectionUtil;

public class AccountDAOImp implements AccountDAO 
{

	private static AccountDAO aDao = null;
	
	private AccountDAOImp()
	{
		
	}
	
	public static AccountDAO getAccountDAO()
	{
		if(aDao == null)
		{
			aDao = new AccountDAOImp();
			return aDao;
		}
		else
			return aDao;
				
	}
	
	public Account createAccount(Account account) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO project0_db.ACCOUNT VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setInt(2, account.getcId());
			ps.setString(3, account.getAccountName());
			ps.setDouble(4, account.getBalance());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("aId");
			account.setaId(key);
			
			return account;			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Account getAccountById(int aId) {
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "SELECT * FROM project0_db.ACCOUNT WHERE AID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aId);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Account account = new Account();
			account.setAccountName(rs.getString("ACOUNTNAME"));
			account.setaId(rs.getInt("AID"));
			account.setcId(rs.getInt("CID"));
			account.setBalance(rs.getDouble("BALANCE"));
			
			return account;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Account> getAllAccounts() {
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "SELECT * FROM project0_db.ACCOUNT";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
				
			List<Account> accounts = new ArrayList<Account>(); 
			Account account;
			while(rs.next())
			{
				account = new Account();
				account.setAccountName(rs.getString("ACOUNTNAME"));
				account.setaId(rs.getInt("AID"));
				account.setcId(rs.getInt("CID"));
				account.setBalance(rs.getDouble("BALANCE"));
				
				accounts.add(account);
			}
			
			return accounts;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}



	

	@Override
	public Account updateAccount(Account account) {
		try(Connection conn = ConnectionUtil.getConnection())
		{
		
			String sql = "UPDATE project0_db.ACCOUNT SET BALANCE = ?, ACOUNTNAME = ?  WHERE AID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, account.getBalance());
			ps.setString(2, account.getAccountName());
			ps.setInt(3, account.getaId());
			
			if(ps.executeUpdate() > 0)
			{
				return account;
			}
			
			return null;
			
		}catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean deleteAccount(int aId) {
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "DELETE FROM project0_db.ACCOUNT WHERE AID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aId);
			
			if(ps.executeUpdate() > 0)
			{
				return true;
			}
			
			return false;
			
		}catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public List<Account> getAccountsByCustomerId(int cId) {
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "SELECT * FROM project0_db.ACCOUNT WHERE CID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cId);
			
			ResultSet rs = ps.executeQuery();
			
			List<Account> accounts = new ArrayList<Account>();
			Account account;
			while(rs.next())
			{
				account = new Account();
				
				account.setAccountName(rs.getString("ACOUNTNAME"));
				account.setaId(rs.getInt("AID"));
				account.setBalance(rs.getDouble("BALANCE"));
				account.setcId(rs.getInt("CID"));
				
				accounts.add(account);
			
			}
			
			return accounts;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
