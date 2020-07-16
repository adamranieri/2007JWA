package dev.ranieri.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.ranieri.entities.School;
import dev.ranieri.utils.ConnectionUtil;

public class SchoolDAOMaria implements SchoolDAO{
	
	private static SchoolDAO sdao = null;
	
	private SchoolDAOMaria() {
		
	}
	
	public static SchoolDAO getSchoolDAOMaria() {
		
		if(sdao == null) {
			sdao = new SchoolDAOMaria();
		}
		
		return sdao;
	}
	

	@Override
	public School createSchool(School school) {
		
		// try with resources. Will auto close the connection for us when we finish our interaction
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO monschool_db.school VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setString(2, school.getName());
			ps.setInt(3, school.getCapacity());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("s_id");
			school.setsId(key);
			
			return school;			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public School getSchoolById(int id) {
		// try with resources. Will auto close the connection for us when we finish our interaction
				try(Connection conn = ConnectionUtil.getConnection()){
					String sql = "SELECT * FROM monschool_db.school WHERE s_id = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, id);
					
					ResultSet rs = ps.executeQuery();
					// ResultSet returns a table. the intial record it points is before the first record
					rs.next();// move the cursor to the first actual record
					
					School school = new School();
					school.setsId(rs.getInt("s_id"));
					school.setName(rs.getString("name"));
					school.setCapacity(rs.getInt("capacity"));
					
					return school;
					
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
	}

	@Override
	public Set<School> getAllSchools() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM monschool_db.school";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			// ResultSet returns a table. the intial record it points is before the first record
			
			Set<School> schools = new HashSet<School>();
			
			while(rs.next()) {
				School school = new School();
				school.setsId(rs.getInt("s_id"));
				school.setName(rs.getString("name"));
				school.setCapacity(rs.getInt("capacity"));
				schools.add(school);
			}
			
			return schools;
							
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}

	@Override
	public School updateSchool(School school) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE monschool_db.school SET name = ?, capacity = ? WHERE s_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, school.getName());
			ps.setInt(2, school.getCapacity());
			ps.setInt(3, school.getsId());
			ps.execute();
				
			return school;				
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteSchool(int id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM monschool_db.school WHERE s_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
								
			return ps.execute();				
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
