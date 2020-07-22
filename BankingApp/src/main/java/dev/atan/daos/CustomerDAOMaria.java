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


public class CustomerDAOMaria implements CustomerDAO{


	private static CustomerDAO cdao = null;
	
	private CustomerDAOMaria() {
		
	}
	
	public static CustomerDAO getCustomerDAOMaria() {
		
		if(cdao == null) {
			cdao = new CustomerDAOMaria();
		}
		
		return cdao;
	}
	

	@Override
	public Customer createCustomer(Customer customer) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO bank_db.CUSTOMER VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setString(2, customer.getUserName());
			ps.setString(3, customer.getcPassword());
			ps.setInt(4, 0);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("cID");
			customer.setcID(key);
			
			return customer;			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public Customer getCustomerById(int id) {
	
				try(Connection conn = ConnectionUtil.getConnection()){
					String sql = "SELECT * FROM bank_db.CUSTOMER WHERE cID = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, id);
					
					ResultSet rs = ps.executeQuery();
					rs.next();
					
					Customer customer = new Customer();
					customer.setcID(rs.getInt("cID"));
					customer.setUserName(rs.getString("userName"));
					customer.setcPassword(rs.getString("cPassword"));
					customer.setOpenAccountsArray(cdao.getOpenAccountsPerCustomer(id));
					customer.setOpenAccounts(customer.getOpenAccountsArray().size());
					
					return customer;
					
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
	}
	
	@Override
	public Customer getCustomerByUserName(String userName) {
	
				try(Connection conn = ConnectionUtil.getConnection()){
					String sql = "SELECT * FROM bank_db.CUSTOMER WHERE userName = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, userName);
					
					ResultSet rs = ps.executeQuery();
					rs.next();
					
					Customer customer = new Customer();
					customer.setcID(rs.getInt("cID"));
					customer.setUserName(rs.getString("userName"));
					customer.setcPassword(rs.getString("cPassword"));
					customer.setOpenAccountsArray(cdao.getOpenAccountsPerCustomer(customer.getcID()));
					customer.setOpenAccounts(customer.getOpenAccountsArray().size());
					
					return customer;
					
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
	}
	
	@Override
	public List<Account> getOpenAccountsPerCustomer(int id) {
	
				try(Connection conn = ConnectionUtil.getConnection()){
					String sql = "SELECT * FROM bank_db.ACCOUNT WHERE cID = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, id);
					
					ResultSet rs = ps.executeQuery();
						
					List<Account> openAccountsArray = new ArrayList<Account>();
					
					while(rs.next()) {
						Account account = new Account();
						account.setaID(rs.getInt("aID"));
						account.setcID(rs.getInt("cID"));
						account.setaName(rs.getString("aName"));
						account.setaBalance(rs.getDouble("aBalance"));
						openAccountsArray.add(account);		
						}
					
					return openAccountsArray;
									
				} catch (SQLException | NegativeBalance e) {
					e.printStackTrace();
					return null;
				}
			
			}

	@Override
	public List<Customer> getAllCustomers() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_db.CUSTOMER";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
				
			List<Customer> customers = new ArrayList<Customer>();
			
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setcID(rs.getInt("cID"));
				customer.setUserName(rs.getString("userName"));
				customer.setcPassword(rs.getString("cPassword"));
				customer.setOpenAccountsArray(cdao.getOpenAccountsPerCustomer(customer.getcID()));
				customer.setOpenAccounts(customer.getOpenAccountsArray().size());
				
				customers.add(customer);
				
				}
			
			return customers;
							
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE bank_db.CUSTOMER SET userName = ?, cPassword = ?, openAccounts = ? WHERE cID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getUserName());
			ps.setString(2, customer.getcPassword());
			ps.setInt(3, customer.getOpenAccounts());
			ps.setInt(4, customer.getcID());
			ps.execute();
			
			customer.setOpenAccountsArray(cdao.getOpenAccountsPerCustomer(customer.getcID()));
			
			return customer;				
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Customer updateCustomerAccounts(Customer customer) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE bank_db.CUSTOMER SET openAccounts = ? WHERE cID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customer.getOpenAccounts());
			ps.setInt(2, customer.getcID());
			ps.execute();
			return customer;				
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteCustomerById(int id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM bank_db.CUSTOMER WHERE cID = ?";
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
	public boolean deleteCustomerByUserName(String userName) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM bank_db.CUSTOMER WHERE UserName = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
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
