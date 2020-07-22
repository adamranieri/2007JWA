package dev.zak.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.zak.entities.Account;
import dev.zak.utilities.ConnectionUtility;

public class AccountDaoMaria implements AccountDaoInterface{

	private static AccountDaoInterface accountDao = null;
	private AccountDaoMaria() {
		super();
	}
	
	public static AccountDaoInterface getAccountDaoMaria() {
		if(accountDao == null)
			accountDao = new AccountDaoMaria();
		return accountDao;
	}
	
	
	private Set<Account> getFieldsFromDB(PreparedStatement ps) {
		ResultSet rs;
		Set<Account> accounts = new HashSet<Account>();
		
		try {
			rs = ps.executeQuery();
			while(rs.next()) {
				Account a = new Account();
				a.setaId(rs.getInt("account_id"));
				a.setcId(rs.getInt("customer_id"));
				a.setAccountName(rs.getString("account_name"));
				a.setBalance(rs.getFloat("balance"));
				accounts.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return accounts;
	}

	public Account createAccount(int cid, Account a) {
		try (Connection con = ConnectionUtility.getConnection()){
			String sql = "INSERT INTO bank_db.account (customer_id,account_name,balance) VALUES (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, a.getcId());
			ps.setString(2, a.getAccountName());
			ps.setFloat(3, a.getBalance());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("s_id");
			a.setaId(key);
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account getAccountById(int cid, int id) {
		return getAccountById(id);
	}

	@Override
	public Account getAccountById(int id) {

		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM bank_db.account WHERE account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			Set<Account> accounts = this.getFieldsFromDB(ps);
			if(accounts.size()>0)
				return this.getFieldsFromDB(ps).iterator().next();
			else
				return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Set<Account> getAllAccountsByCustomerId(int cid) {

		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM bank_db.account WHERE customer_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cid);
			return this.getFieldsFromDB(ps);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Account> getAllAccounts() {
		
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM bank_db.account";
			PreparedStatement ps = conn.prepareStatement(sql);
			return this.getFieldsFromDB(ps);
							
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}

	@Override
	public Account updateAccount(Account a) {
		
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "UPDATE bank_db.account SET customer_id = ?, account_name = ?, balance = ? WHERE account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getcId());
			ps.setString(2, a.getAccountName());
			ps.setFloat(3, a.getBalance());
			ps.setInt(4, a.getaId());
			int numberOfUpdates = ps.executeUpdate();
			if(numberOfUpdates>0)
				return a;
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account updateAccount(int cid, Account a) {
		return updateAccount(a);
	}

	@Override
	public boolean deleteAccount(int id) {
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "DELETE FROM bank_db.account WHERE account_id = ?";// and customer_id= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			// ps.setInt(2, cid);
			int updatedRecords = ps.executeUpdate();
			if(updatedRecords>0)
				return true;
			return false;				
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean deleteAccount(int cid, int id) {
		return deleteAccount(id);
	}

	@Override
	public Set<Account> getAccountLessThan(int cid, int balance) {
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM bank_db.account WHERE customer_id = ? AND balance < ?  ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.setInt(2, balance);

			return this.getFieldsFromDB(ps);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Account> getAccountGreaterThan(int cid, int balance) {
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM bank_db.account WHERE customer_id = ? AND balance > ?  ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.setInt(2, balance);
			return this.getFieldsFromDB(ps);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Set<Account> getAccountBalanceBtween(int cid, int balance1, int balance2) {
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM bank_db.account WHERE customer_id = ? AND balance BETWEEN ? AND ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.setInt(2, balance1);
			ps.setInt(3, balance2);

			return this.getFieldsFromDB(ps);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
