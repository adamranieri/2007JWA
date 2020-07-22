package dev.zak.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.zak.entities.Transaction;
import dev.zak.utilities.ConnectionUtility;

public class TransactionDaoMaria implements TransactionDaoInterface{

	private static TransactionDaoInterface tDao = null;
	
	private TransactionDaoMaria() {
		super();
	}
	
	public static TransactionDaoInterface getTransactionDaoMaria() {
		if(tDao == null) {
			tDao = new TransactionDaoMaria();
		}
		return tDao;
	}
	
	private Set<Transaction> getFieldsFromDB(PreparedStatement ps){
		ResultSet rs;
		Transaction t;
		Set<Transaction> transactions = new HashSet<Transaction>();
		try {
			rs = ps.executeQuery();
			while(rs.next()) {
				
				t = new Transaction();
				t.settId(rs.getInt("transaction_id"));
				t.setaId(rs.getInt("account_id"));
				t.setPrevBalance(rs.getFloat("previous_balance"));
				t.setFinalBalance(rs.getFloat("final_balance"));
				transactions.add(t);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactions;
		
	}
	
	@Override
	public Transaction createTransaction(Transaction t) {
		try(Connection con = ConnectionUtility.getConnection()){
			String sql = "INSERT INTO bank_db.transaction_log (account_id, previous_balance, final_balance) VALUES (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, t.getaId());
			ps.setFloat(2, t.getPrevBalance());
			ps.setFloat(3, t.getFinalBalance());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			t.settId(rs.getInt("transaction_id"));
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
		
	}

	@Override
	public Transaction getTransactionById(int tId) {
		Set<Transaction> transactions = new HashSet<Transaction>();
		Transaction t = null;
		try(Connection con = ConnectionUtility.getConnection()){
			
			String sql = "SELECT * FROM bank_db.transaction_log WHERE transaction_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, tId);
			ps.execute();
			transactions = getFieldsFromDB(ps);
			t = transactions.isEmpty() ? null : transactions.iterator().next();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public Set<Transaction> getAllTransactionsByAccount(int aId) {
		Set<Transaction> transactions = new HashSet<Transaction>();
		try(Connection con = ConnectionUtility.getConnection()){
			
			String sql = "SELECT * FROM bank_db.transaction_log WHERE account_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aId);
			ps.execute();
			transactions = getFieldsFromDB(ps);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}

	@Override
	public Set<Transaction> getAllTransactions() {
		Set<Transaction> transactions = new HashSet<Transaction>();
		try(Connection con = ConnectionUtility.getConnection()){
			
			String sql = "SELECT * FROM bank_db.transaction_log";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
			transactions = getFieldsFromDB(ps);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}

	@Override
	public Transaction updateTransation(Transaction t) {
		// Updating a Transaction means that it is a different transaction
		return null;
	}

	@Override
	public boolean DeleteTransactionById(int tId) {
		try(Connection con = ConnectionUtility.getConnection()){
			String sql = "DELETE FROM bank_db.transaction_log WHERE transaction_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, tId);
			int numberOfDeletes = ps.executeUpdate();
			if(numberOfDeletes>0)
				return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
				return false;
	}

	@Override
	public boolean DeleteAllTransactionsByAccount(int aId) {
		try(Connection con = ConnectionUtility.getConnection()){
			String sql = "DELETE FROM bank_db.transaction_log WHERE acccount_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aId);
			int numberOfDeletes = ps.executeUpdate();
			if(numberOfDeletes>0)
				return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
				return false;
	}

	@Override
	public boolean DeleteAllTransactionsint() {
		try(Connection con = ConnectionUtility.getConnection()){
			String sql = "DELETE FROM bank_db.transaction_log";
			PreparedStatement ps = con.prepareStatement(sql);
			int numberOfDeletes = ps.executeUpdate();
			if(numberOfDeletes>0)
				return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
				return false;
	}
	

}
