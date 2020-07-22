package dev.nauman.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.nauma.utils.ConnectionUtil;
import dev.nauman.entities.Account;
import dev.nauman.exceptions.NegativeBalanceException;

public class AccountDAOImpl implements AccountDAO {

	private static AccountDAOImpl adao = null;

	private AccountDAOImpl() {
	}

	public static AccountDAOImpl getAccountDAOImpl() {
		if(adao == null) {
			adao = new AccountDAOImpl();
		}
		return adao;
	}

	@Override
	public Account createAccount(Account account) {

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO project_zero_db.account VALUES(?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, account.getaId());
			ps.setInt(2, account.getcId());
			ps.setString(3, account.getAccountName());
			ps.setDouble(4, account.getBalance());
			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			account.setaId(rs.getInt("a_id"));

			return account;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Account getAccountByAId(int aId) {

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM project_zero_db.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aId);
			ResultSet rs = ps.executeQuery();
			rs.next();

			Account account = new Account();
			account.setaId(rs.getInt("a_id"));
			account.setcId(rs.getInt("cust_id"));
			account.setAccountName(rs.getString("account_name"));
			try {
				account.setBalance(rs.getDouble("balance"));
			} catch (NegativeBalanceException e) {
				e.printStackTrace();
			}

			return account;
		}catch (SQLException e) {
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
				account.setaId(rs.getInt("a_id"));
				account.setcId(rs.getInt("cust_id"));
				account.setAccountName(rs.getString("account_name"));

				try {
					account.setBalance(rs.getDouble("balance"));
				} catch (NegativeBalanceException e) {
					e.printStackTrace();
				}
				accounts.add(account);
			}

			return accounts;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Set<Account> getAccountsByCId(int id) {

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM project_zero_db.account WHERE cust_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			Set<Account> accounts = new HashSet<Account>();

			while(rs.next()) {
				Account account = new Account();
				account.setaId(rs.getInt("a_id"));
				account.setcId(rs.getInt("cust_id"));
				account.setAccountName(rs.getString("account_name"));

				try {
					account.setBalance(rs.getDouble("balance"));
				} catch (NegativeBalanceException e) {
					e.printStackTrace();
				}
				accounts.add(account);
			}

			return accounts;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Account updateAccount(Account account) {

		try(Connection conn = ConnectionUtil.getConnection()){

			String sql = "UPDATE project_zero_db.account SET cust_id = ?, account_name = ?, balance = ? WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getcId());
			ps.setString(2, account.getAccountName());
			ps.setDouble(3, account.getBalance());
			ps.setInt(4, account.getaId());
			ps.execute();

			return account;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean deleteAccountByAId(int aId) {

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM project_zero_db.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aId);
			int affectedRows = ps.executeUpdate();

			if(affectedRows>0) {
				return true;
			}return false;

		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
