package dev.atan.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dev.atan.entities.Account;
import dev.atan.entities.Customer;
import dev.atan.exceptions.NegativeBalance;
import dev.atan.utility.ConnectionUtil;

public class AccountDAOMaria implements AccountDAO{

	private static AccountDAO adao = null;

	private AccountDAOMaria() {

	}

	public static AccountDAO getAccountDAOMaria() {

		if(adao == null) {
			adao = new AccountDAOMaria();
		}

		return adao;
	}

	private static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();

	@Override
	public Account createAccount (Account account){

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO bank_db.ACCOUNT VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setInt(2, account.getcID());
			ps.setString(3, account.getaName());
			ps.setDouble(4, account.getaBalance());
			ps.execute();
						
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("aID");
			account.setaID(key);
			Customer customer = cdao.getCustomerById(account.getcID());
			customer.addAccountsToArr(account);
			//customer.setOpenAccounts(customer.getOpenAccountsArray().size());
			customer = cdao.updateCustomer(customer);
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
			String sql = "SELECT * FROM bank_db.ACCOUNT WHERE aID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			rs.next();

			Account account = new Account();
			account.setaID(rs.getInt("aID"));
			account.setcID(rs.getInt("cID"));
			account.setaName(rs.getString("aName"));
			account.setaBalance(rs.getDouble("aBalance"));

			return account;

		} catch (SQLException | NegativeBalance e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Account> getAllAccounts() {

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.ACCOUNT";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();

			List<Account> accounts = new ArrayList<Account>();

			while(rs.next()) {
				Account account = new Account();
				account.setaID(rs.getInt("aID"));
				account.setcID(rs.getInt("cID"));
				account.setaName(rs.getString("aName"));
				account.setaBalance(rs.getDouble("aBalance"));
				accounts.add(account);
				
			}

			return accounts;

		} catch (SQLException | NegativeBalance e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@Override
	public List<Account> getAccountsByBalanceGreaterThan(int greaterThan) {

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.ACCOUNT WHERE aBalance > ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, greaterThan);
			ResultSet rs = ps.executeQuery();

			List<Account> accounts = new ArrayList<Account>();

			while(rs.next()) {
				Account account = new Account();
				account.setaID(rs.getInt("aID"));
				account.setcID(rs.getInt("cID"));
				account.setaName(rs.getString("aName"));
				account.setaBalance(rs.getDouble("aBalance"));
				accounts.add(account);
				
			}

			return accounts;

		} catch (SQLException | NegativeBalance e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@Override
	public List<Account> getAccountsByBalanceLessThan(int lessThan) {

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.ACCOUNT WHERE aBalance < ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, lessThan);
			ResultSet rs = ps.executeQuery();

			List<Account> accounts = new ArrayList<Account>();

			while(rs.next()) {
				Account account = new Account();
				account.setaID(rs.getInt("aID"));
				account.setcID(rs.getInt("cID"));
				account.setaName(rs.getString("aName"));
				account.setaBalance(rs.getDouble("aBalance"));
				accounts.add(account);
				
			}

			return accounts;

		} catch (SQLException | NegativeBalance e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Account updateAccount(Account account) {

		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE bank_db.ACCOUNT SET cID = ?, aName = ?, aBalance = ? WHERE aID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
		
			ps.setInt(1, account.getcID());
			ps.setString(2, account.getaName());
			ps.setDouble(3, account.getaBalance());
			ps.setInt(4, account.getaID());
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
			String sql = "DELETE FROM bank_db.ACCOUNT WHERE aID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			Account account = adao.getAccountById(id);
			Customer customer = cdao.getCustomerById(account.getcID());
			int rows = ps.executeUpdate();
			customer.removeAccountsFromArr(account);
			customer.setOpenAccountsArray(cdao.getOpenAccountsPerCustomer(customer.getcID()));
			customer.setOpenAccounts(customer.getOpenAccountsArray().size());
			customer = cdao.updateCustomer(customer);
			
			
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
	
	@Override
	public List<Account> getAccountsByCustomerId(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.ACCOUNT WHERE cID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			List<Account> accounts = new ArrayList<Account>();
			
			while(rs.next()) {
				
				Account account = new Account();
				account.setaID(rs.getInt("aID"));
				account.setcID(rs.getInt("cID"));
				account.setaName(rs.getString("aName"));
				try {
					account.setaBalance(rs.getDouble("aBalance"));
				} catch (NegativeBalance e) {
					e.printStackTrace();
				}
				accounts.add(account);
				
			}
			
			return accounts;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}