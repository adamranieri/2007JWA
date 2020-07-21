package dev.noah.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.noah.entities.Account;
import dev.noah.exceptions.NegativeBalanceException;
import dev.noah.utils.ConnectionUtil;

public class AccountDAOMaria implements AccountDAO {

	public static AccountDAO accountDAOMaria = null;

	private AccountDAOMaria() {

	}

	public static AccountDAO getAccountDaoMaria() {
		if (accountDAOMaria == null) {
			return accountDAOMaria = new AccountDAOMaria();
		}
		return accountDAOMaria;
	}

	@Override
	public Account createAccount(Account account) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO bank_db.account VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setInt(2, account.getcId());
			ps.setString(3, account.getAccountName());
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
	public Account getAccount(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bank_db.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			rs.next();
			int key = rs.getInt("a_id");
			Account account = new Account();
			account.setaId(rs.getInt("a_id"));
			account.setcId(rs.getInt("c_id"));
			account.setAccountName(rs.getString("a_name"));
			account.setBalance(rs.getDouble("bal"));

			return account;

		} catch (SQLException | NegativeBalanceException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Account> getAllAccounts() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bank_db.account";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			Set<Account> accounts = new HashSet<Account>();
			
			while (rs.next()) {
				Account account = new Account();
				account.setaId(rs.getInt("a_id"));
				account.setcId(rs.getInt("c_id"));
				account.setAccountName(rs.getString("a_name"));
				account.setBalance(rs.getDouble("bal"));
				accounts.add(account);
			}
			
			return accounts;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NegativeBalanceException e) {
			
			e.printStackTrace();
			return null; 
		}
	}
	

	@Override
	public Account updateAccount(Account account) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE bank_db.account SET c_id = ?, a_name = ?, bal = ? WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getcId());
			ps.setString(2, account.getAccountName());
			ps.setDouble(3, account.getBalance());
			ps.setDouble(4, account.getaId());
			ps.execute();

			return account;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean deleteAccount(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Delete FROM bank_db.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			int row = ps.executeUpdate();
			if(row > 0) {
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
	public Set<Account> getAllAccountsByCId(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bank_db.account WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			Set<Account> accounts = new HashSet<Account>();
			
			while (rs.next()) {

				Account account = new Account();
				account.setaId(rs.getInt("a_id"));
				account.setcId(rs.getInt("c_id"));
				account.setAccountName(rs.getString("a_name"));
				account.setBalance(rs.getDouble("bal"));
				
				accounts.add(account);
			}
			
			return accounts;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NegativeBalanceException e) {
			
			e.printStackTrace();
			return null;
		}
	}

}
