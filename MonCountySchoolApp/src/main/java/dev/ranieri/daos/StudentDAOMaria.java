package dev.ranieri.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.ranieri.entities.Student;
import dev.ranieri.utils.ConnectionUtil;

public class StudentDAOMaria implements StudentDAO {

	private static StudentDAO studao = null;

	private StudentDAOMaria() {
	}

	public static StudentDAO getStudentDAOMaria() {

		if (studao == null) {
			studao = new StudentDAOMaria();
		}

		return studao;
	}

	@Override
	public Student createStudent(Student student) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO monschool_db.student VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setString(2, student.getName());
			ps.setInt(3, student.getsId());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys(); // returns a virtual table
			rs.next();// moves you to the first record in that table
			int key = rs.getInt("stu_id");
			student.setStuId(key);
			
			return student;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
		
	}

	@Override
	public Set<Student> getAllStudents() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM monschool_db.student";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Set<Student> students = new HashSet<Student>();
			
			while(rs.next()) {
				
				Student student = new Student();
				student.setStuId(rs.getInt("stu_id"));
				student.setName(rs.getString("name"));
				student.setsId(rs.getInt("s_id"));				
				students.add(student);
				
			}
			
			return students;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Student getStudentById(int id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM monschool_db.student WHERE stu_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery(); // get us back a table with all the information
			rs.next();// move table to first record
			
			Student student = new Student();
			student.setStuId(rs.getInt("stu_id"));
			student.setName(rs.getString("name"));
			student.setsId(rs.getInt("s_id"));
			
			return student;
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}

	@Override
	public Set<Student> getStudentsBySchoolId(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM monschool_db.student WHERE s_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			Set<Student> students = new HashSet<Student>();
			
			while(rs.next()) {
				
				Student student = new Student();
				student.setStuId(rs.getInt("stu_id"));
				student.setName(rs.getString("name"));
				student.setsId(rs.getInt("s_id"));				
				students.add(student);
				
			}
			
			return students;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Student updateStudent(Student student) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE monschool_db.student SET name = ?, s_id = ? WHERE stu_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setInt(2, student.getsId());
			ps.setInt(3, student.getStuId());
			ps.execute();
			
			return student;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
		
		
	

	@Override
	public boolean deleteStudentById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM monschool_db.student WHERE stu_id =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			return ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
