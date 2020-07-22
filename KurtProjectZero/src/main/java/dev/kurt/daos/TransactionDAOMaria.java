package dev.kurt.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import dev.kurt.entities.Account;
import dev.kurt.entities.Transaction;
import dev.kurt.utils.ConnectionUtil;

public class TransactionDAOMaria implements TransactionDAO{

	private static TransactionDAO traDao = null;

	private TransactionDAOMaria() {
	}

	public static TransactionDAO getTransactionDAOMaria() {

		if (traDao == null) {
			traDao = new TransactionDAOMaria();
		}

		return traDao;
	}

	@Override
	public Transaction createTransaction(Transaction transaction) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO KurtBankDB.transaction VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setDouble(2, transaction.getPrevBalance());
			ps.setDouble(3, transaction.getFinalBalance());
			ps.setInt(4, transaction.getaId());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys(); // returns a virtual table
			rs.next();// moves you to the first record in that table
			int key = rs.getInt("t_id");
			transaction.setaId(key);
			
			return transaction;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Transaction getTransactionById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM KurtBankDB.transaction WHERE t_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery(); // get us back a table with all the information
			rs.next();// move table to first record
			
			Transaction transaction  = new Transaction();
			transaction.settId(rs.getInt("t_id"));
			transaction.setPrevBalance(rs.getDouble("previous_balance"));
			transaction.setFinalBalance(rs.getDouble("final_balance"));
			transaction.setaId(rs.getInt("a_id"));
			
			return transaction;
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<Transaction> getAllTransactions() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM KurtBankDB.transaction";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			
			while(rs.next()) {
				
				Transaction transaction = new Transaction();
				transaction.settId(rs.getInt("t_id"));
				transaction.setPrevBalance(rs.getDouble("previous_balance"));
				transaction.setFinalBalance(rs.getDouble("final_balance"));
				transaction.setaId(rs.getInt("a_id"));				
				transactions.add(transaction);
				
			}
			
			return transactions;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<Transaction> getTransactionsByAccountId(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM KurtBankDB.transaction WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			
			while(rs.next()) {
				
				Transaction transaction = new Transaction();
				transaction.settId(rs.getInt("t_id"));
				transaction.setPrevBalance(rs.getDouble("previous_balance"));
				transaction.setFinalBalance(rs.getDouble("final_balance"));
				transaction.setaId(rs.getInt("a_id"));				
				transactions.add(transaction);
			}
			
			return transactions;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Transaction updateTransaction(Transaction transaction) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE KurtBankDB.transaction SET a_id = ?, previous_balance = ? , final_balance = ? WHERE t_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, transaction.getaId());
			ps.setDouble(2, transaction.getPrevBalance());
			ps.setDouble(3, transaction.getFinalBalance());
			ps.setInt(4, transaction.gettId());
			ps.execute();
			
			return transaction;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
	}

	@Override
	public boolean deleteTransactionById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM KurtBankDB.transaction WHERE t_id =?";
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

	@Override
	public Transaction getFirstTransactionForAccount(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM KurtBankDB.transaction WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery(); // get us back a table with all the information
			rs.next();// move table to first record
			
			Transaction transaction  = new Transaction();
			transaction.settId(rs.getInt("t_id"));
			transaction.setPrevBalance(rs.getDouble("previous_balance"));
			transaction.setFinalBalance(rs.getDouble("final_balance"));
			transaction.setaId(rs.getInt("a_id"));
			
			return transaction;
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	

	
}
