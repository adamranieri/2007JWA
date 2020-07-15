package dev.noah.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import dev.noah.entities.Customer;
import dev.noah.utils.ConnectionUtil;

public class CustomerDAOMaria implements CustomerDAO{

	private static CustomerDAO cdao = null;
	
	private CustomerDAOMaria()
	{
		
	}
	
	public static CustomerDAO getCustomerDAOMaria() {
		
		if(cdao == null) {
			cdao = new CustomerDAOMaria();
		}
		
		return cdao;
	}

	@Override
	public Customer createCustomer(Customer customer) {
		/*try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO monschool_db.school VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setString(2, customer.getName());
			ps.setInt(3, customer.getCapacity());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("s_id");
			school.setsId(key);
			return school;
			
			}catch(SQLException e){
				e.printStackTrace();
				return null;
			}*/
		return null;
	}

	@Override
	public Customer getCustomerByCId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCustomer(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
