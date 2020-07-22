package dev.winder.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import dev.winder.bankappexceptions.InsufficientFundsException;
import dev.winder.entities.BankAccount;
import dev.winder.entities.Transaction;
import dev.winder.utils.ConnectionSQLUtil;

public class BankTransactionDAOMaria implements BankTransactionDAO {

	
	
	
	private static BankTransactionDAO tDao= BankTransactionDAOMaria.getBankTransactionDAOMaria();
	
	private static BankAccountDAO bDao = BankAccountDAOMaria.getBankAccountDAOMaria();
	
	//private constructor only to be called for getting the singleton instance in the getInstance method
	private BankTransactionDAOMaria() {
	}
	
	public static BankTransactionDAO getBankTransactionDAOMaria() {
		
		if(tDao == null) {
			
			tDao = new BankTransactionDAOMaria();
		}
			return tDao;
	}//end getBankAccountDAOMaria

	@Override
	public Transaction depositFunds(Transaction transaction, double d) {
		
		try(Connection BankDBConn = ConnectionSQLUtil.getConnection()){
			
			String sqlStatement = "INSERT INTO BankDB.transactions VALUES (?,?,?,?)";
			
			PreparedStatement ps = BankDBConn.prepareStatement(sqlStatement,Statement.RETURN_GENERATED_KEYS);
			
			BankAccount bankAccount = bDao.getBankAccountByAid(transaction.getaId());
			
			bankAccount.setAccountBalance(transaction.getPrevBalance() + d);
			
			ps.setInt(1, 0);
			ps.setDouble(2,transaction.getPrevBalance());
			ps.setDouble(3, transaction.getPrevBalance() + d);
			ps.setInt(4, transaction.getaId());
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			rs.next();
			
			transaction.setTransactionId(rs.getInt("tId"));
			transaction.setPrevBalance(transaction.getPrevBalance());
			
			String formatted = new DecimalFormat("#0.00").format(bankAccount.getAccountBalance());
			
			Double finalBal =Double.parseDouble(formatted);
			
			transaction.setFinalBalance(finalBal);
			transaction.setaId(transaction.getaId());
			
			bDao.updateBankAccount(bankAccount);
			
		
			
			return transaction;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	
	
	}

	@Override
	public Transaction withdrawal(Transaction transaction, double d) throws InsufficientFundsException {
		
	
	try(Connection BankDBConn = ConnectionSQLUtil.getConnection()){
			
			String sqlStatement = "INSERT INTO BankDB.transactions VALUES (?,?,?,?)";
			
			PreparedStatement ps = BankDBConn.prepareStatement(sqlStatement,Statement.RETURN_GENERATED_KEYS);
			
			BankAccount bankAccount = bDao.getBankAccountByAid(transaction.getaId());
			
			bankAccount.setAccountBalance(transaction.getPrevBalance() - d);

			
			if(d > transaction.getPrevBalance()) {
				
				throw new InsufficientFundsException();
			}
			
			
			ps.setInt(1, 0);
			ps.setDouble(2,transaction.getPrevBalance());
			ps.setDouble(3, transaction.getPrevBalance() - d);
			ps.setInt(4, transaction.getaId());
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			rs.next();
			
			transaction.setTransactionId(rs.getInt("tId"));
			transaction.setPrevBalance(transaction.getPrevBalance());
			
			String formatted = new DecimalFormat("#0.00").format(bankAccount.getAccountBalance());
			
			Double finalBal =Double.parseDouble(formatted);
			
			transaction.setFinalBalance(finalBal);
			transaction.setaId(transaction.getaId());
			
			bDao.updateBankAccount(bankAccount);
			
			return transaction;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}catch(InsufficientFundsException ex) {
			ex.printStackTrace();
			return null;
		}
	
		
		
	
	}
	
	
	
	
	





	
	
	
	
	
	
	
}
