package dev.winder.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;


import dev.winder.entities.BankAccount;
import dev.winder.entities.Customer;
import dev.winder.utils.ConnectionSQLUtil;

public class BankAccountDAOMaria implements BankAccountDAO {
	
	
	private static BankAccountDAO bDao= BankAccountDAOMaria.getBankAccountDAOMaria();
	
	private static CustomerDAO cDao = CustomerDAOMaria.getCustomerDAOMaria();
	
	//private constructor only to be called for getting the singleton instance in the getInstance method
	private BankAccountDAOMaria() {
	}
	
	public static BankAccountDAO getBankAccountDAOMaria() {
		
		if(bDao == null) {
			
			bDao = new BankAccountDAOMaria();
		}
			return bDao;
	}//end getBankAccountDAOMaria
	
	@Override
	public BankAccount createBankAccount(BankAccount bankAccount) {
		
		try(Connection BankDBConn = ConnectionSQLUtil.getConnection()){
			
			
			
			String sqlStatement = "INSERT INTO BankDB.bankaccounts VALUES (?,?,?,?)";
			
			PreparedStatement ps = BankDBConn.prepareStatement(sqlStatement,Statement.RETURN_GENERATED_KEYS);
			
			
			
			//SQL will autoincrement
			ps.setInt(1,0);
			ps.setString(2, bankAccount.getAccountName());
			//initial balance is being set..
			ps.setDouble(3, bankAccount.getAccountBalance());
			ps.setInt(4, bankAccount.getCustomerId());
			ps.execute();
		
			//return the generated table
			ResultSet rs = ps.getGeneratedKeys();
			
			rs.next();
			//give bankAcount return object data from the virtual table result set
			bankAccount.setAccountId(rs.getInt("a_id"));
			bankAccount.setAccountName(rs.getString("aName"));
			bankAccount.setAccountBalance(rs.getDouble("aBal"));
			bankAccount.setCustomerId(rs.getInt("c_id"));
			
						
			return bankAccount;
			
		}catch(SQLException ex) {
			
			ex.printStackTrace();
			return null;
		}
		
	}//end createBankAccount

	@Override
	public BankAccount getBankAccountByAid(int id) {

		try(Connection BankDBConn = ConnectionSQLUtil.getConnection()){
			String sqlStatement = "SELECT * FROM BankDB.bankaccounts WHERE aId = ?";
			
			PreparedStatement ps = BankDBConn.prepareStatement(sqlStatement);
			//set the id for our SQL query
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
		
			
			BankAccount bankAccount = new BankAccount();
			bankAccount.setAccountId(rs.getInt("aId"));
			bankAccount.setAccountName(rs.getString("aName"));
			bankAccount.setAccountBalance(rs.getDouble("aBal"));
			bankAccount.setCustomerId(rs.getInt("c_id"));
			
			return bankAccount;
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
		
	}//end getBankAccountByAid

	@Override
	//method to retrieve all bank accounts in the bank branch
	public Set<BankAccount> getAllBankAccountsInBranch() {
		
		try(Connection BankDBConn = ConnectionSQLUtil.getConnection()){
			
			String sqlStatement = "SELECT * FROM BankDB.bankaccounts";
			
			PreparedStatement ps = BankDBConn.prepareStatement(sqlStatement);
			
			ResultSet rs = ps.executeQuery();
			
			Set<BankAccount>allbankaccounts = new HashSet<BankAccount>();
			
			while(rs.next()) {
			
			BankAccount curBankAccount = new BankAccount();
			
			curBankAccount.setAccountId(rs.getInt("aId"));
			curBankAccount.setAccountName(rs.getString("aName"));
			curBankAccount.setAccountBalance(rs.getDouble("aBal"));
			curBankAccount.setCustomerId(rs.getInt("c_id"));
			
			allbankaccounts.add(curBankAccount);
			
			}
			
			return allbankaccounts;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
		
	}//end getAllBankAccountsInBranch
	
	
	//retrieve this.customers account stored in BankDB.bankaccounts
	public Set<BankAccount> getAllCurrentCustomerAccounts(int cid){
		
		try(Connection BankDBConn = ConnectionSQLUtil.getConnection()){
			
			String sqlStatement = "SELECT * FROM BankDB.bankaccounts WHERE c_id = ?";
			
			PreparedStatement ps = BankDBConn.prepareStatement(sqlStatement);
			
			//retreive all of cid's accounts
			ps.setInt(1,cid);
			
			ResultSet rs = ps.executeQuery();
			
			Set<BankAccount>thisCustomersAccounts = new HashSet<BankAccount>();
			
			while(rs.next()) {
				
				BankAccount bankAccount = new BankAccount();
				
				bankAccount.setAccountId(rs.getInt("aId"));
				bankAccount.setAccountName(rs.getString("aName"));
				bankAccount.setAccountBalance(rs.getDouble("aBal"));
				bankAccount.setCustomerId(rs.getInt("c_id"));
				
				thisCustomersAccounts.add(bankAccount);
			}
			
			return thisCustomersAccounts;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}// end getAllCurrentCustomerAccounts

	@Override
	//were assuming only the name of the account is to be changed, aId shouldnt be changed since logically a person
	//would not have concern over the account id, and the balance should only be updated through a transaction.
	public BankAccount updateBankAccount(BankAccount bankAccount) {
		
		try(Connection BankDBConn = ConnectionSQLUtil.getConnection()){
			
			String sqlStatement = "UPDATE BankDB.bankaccounts SET aName = ?, aBal = ?, c_Id = ? WHERE aId = ?";
			PreparedStatement ps = BankDBConn.prepareStatement(sqlStatement);
			ps.setString(1, bankAccount.getAccountName());
			ps.setDouble(2, bankAccount.getAccountBalance());
			ps.setInt(3, bankAccount.getCustomerId());
			ps.setInt(4, bankAccount.getAccountId());
			ps.execute();
			
			return bankAccount;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		
		}
		
	}// end updateBankAccount

	@Override
	
	//delete bank account by a-id
	public Boolean deleteBankAccount(int aid) {
		
		try(Connection BankDBConn = ConnectionSQLUtil.getConnection()){
			
			String sqlStatement = "DELETE FROM BankDB.bankaccounts WHERE aId = ?";
			
			PreparedStatement ps = BankDBConn.prepareStatement(sqlStatement);
			
			ps.setInt(1, aid);
			
			int rows = ps.executeUpdate();
			
			if(rows > 0) {
				return true;
			}else {
				return false;
			}
			
			
		}catch(SQLException ex) {
			
			ex.printStackTrace();
			return false;
		}
		
	}// end deleteBankAccount

	@Override
	public Boolean removeThisCustomersAccounts(int cid) {
		
		try(Connection BankDBConn = ConnectionSQLUtil.getConnection()){
			
			String sqlStatement = "DELETE FROM BankDB.bankaccounts WHERE c_id = ?";
			
			PreparedStatement ps = BankDBConn.prepareStatement(sqlStatement);
			
			ps.setInt(1, cid);
			
			int rows = ps.executeUpdate();
			
			if(rows > 0) {
				return true;
			}else {
				return false;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	
	}//end removeThisCustomersAccounts

	@Override
	public BankAccount returnBalanceByAid(int aid) {
		
		try(Connection BankDBConn = ConnectionSQLUtil.getConnection()){
			
			String sqlStatement = "SELECT * FROM BankDB.bankaccounts WHERE aId = ?";
			
			PreparedStatement ps = BankDBConn.prepareStatement(sqlStatement);
			
			ps.setInt(1, aid);
			
			ResultSet rs = ps.executeQuery();
			
			
			rs.next();
			BankAccount bankAccount = new BankAccount();
			
			bankAccount.setAccountId(rs.getInt("aId"));
			bankAccount.setAccountName(rs.getString("aName"));
			bankAccount.setAccountBalance(rs.getDouble("aBal"));
			bankAccount.setCustomerId(rs.getInt("c_id"));
			
			
			
			
			return bankAccount;
			
		}catch(SQLException ex) {
			
			ex.printStackTrace();
			return null;
		}
		
	}//end returnBalanceByAid
	
	

	
	
	

}
