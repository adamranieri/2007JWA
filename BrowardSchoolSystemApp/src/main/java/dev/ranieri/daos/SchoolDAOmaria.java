package dev.ranieri.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.ranieri.entities.School;
import dev.ranieri.utils.ConncetionUtil;

public class SchoolDAOmaria implements SchoolDAO{

	public School createSchool(School school) {
	
		try(Connection conn = ConncetionUtil.createConnection()){
			String sql = "INSERT INTO browardschooldb.SCHOOL VALUES (?,?,?,?)";
			// when you first save an object it has an id of 0
			// please return the primary key of the object we just saved
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setString(2,school.getName());
			ps.setInt(3, school.getPhone());
			ps.setInt(4, school.getCapacity());
			ps.execute(); // execute the insert statement
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("SCHOOL_ID");
			
			school.setSchoolId(key);
			return school;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public School getSchoolById(int id) {		
		// try with resources
		// It auto closes whatever resource you use
		// helpful syntax for avoiding cumbersome finally block		
		try(Connection conn = ConncetionUtil.createConnection()){
			String sql = "SELECT * FROM browardschooldb.SCHOOL WHERE SCHOOL_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			// result set contains our information
			// by default result points to before the first record returned
			ResultSet rs = ps.executeQuery();
			
			// moves cursor one spot to first record
			rs.next();
			
			School school = new School();
			
			school.setSchoolId(rs.getInt("SCHOOL_ID"));
			school.setName(rs.getString("NAME"));
			school.setPhone(rs.getInt("PHONE"));
			school.setCapacity(rs.getInt("CAPACITY"));
			
			return school;
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return null;
	}

	public School getSchoolByName(String name) {
		
		try(Connection conn = ConncetionUtil.createConnection()){
			String sql = "SELECT * FROM browardschooldb.SCHOOL WHERE NAME = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			School school = new School();
			
			school.setSchoolId(rs.getInt("SCHOOL_ID"));
			school.setName(rs.getString("NAME"));
			school.setPhone(rs.getInt("PHONE"));
			school.setCapacity(rs.getInt("CAPACITY"));
			
			return school;
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return null;		
	}

	public List<School> getAllSchools() {
		
		try(Connection conn = ConncetionUtil.createConnection()){
			String sql = "SELECT * FROM browardschooldb.SCHOOL";
			PreparedStatement ps = conn.prepareStatement(sql);			

			ResultSet rs = ps.executeQuery();
			
			List<School> schools = new ArrayList<School>();
			
			while(rs.next()) {
				
				School school = new School();				
				school.setSchoolId(rs.getInt("SCHOOL_ID"));
				school.setName(rs.getString("NAME"));
				school.setPhone(rs.getInt("PHONE"));
				school.setCapacity(rs.getInt("CAPACITY"));
				schools.add(school);
				
			}
					
			return schools;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
				
		
	}

	public School updateSchool(School school) {
		
		try(Connection conn = ConncetionUtil.createConnection()){
			
String sql = "UPDATE browardschooldb.SCHOOL SET NAME = ?, PHONE = ?, CAPACITY = ? WHERE SCHOOL_ID = ?";			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, school.getName());
			ps.setInt(2, school.getPhone());
			ps.setInt(3, school.getCapacity());
			ps.setInt(4, school.getSchoolId());
			boolean success = ps.execute();
		
			return school;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteSchool(School school) {
		
		try(Connection conn = ConncetionUtil.createConnection()){
			String sql = "DELETE FROM browardschooldb.SCHOOL WHERE SCHOOL_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, school.getSchoolId());
			boolean success = ps.execute();
			return success;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
