package dev.ranieri.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.ranieri.entities.Student;
import dev.ranieri.utils.ConncetionUtil;

public class StudentDAOmaria implements StudentDAO {

	@Override
	public Student createStudent(Student student) {
		
		try(Connection conn = ConncetionUtil.createConnection()){
			String sql = "INSERT INTO browardschooldb.STUDENT VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setString(2, student.getName());
			ps.setInt(3, student.getSchoolId());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();// gets first record
			
			int key = rs.getInt("STUDENT_ID");
			student.setStudentId(key);
			return student;		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Student getStudentById(int id) {
		
		try(Connection conn = ConncetionUtil.createConnection()){
			String sql = "SELECT * FROM STUDENT WHERE STUDENT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();			
			rs.next();
			
			Student student = new Student();			
			student.setStudentId(rs.getInt("STUDENT_ID"));
			student.setName(rs.getString("NAME"));
			student.setSchoolId(rs.getInt("SCHOOL_ID"));
			
			return student;			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<Student> getAllStudent() {
		

		try(Connection conn = ConncetionUtil.createConnection()){
			String sql = "SELECT * FROM STUDENT";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			
			List<Student> students = new ArrayList<Student>();
			
			while(rs.next()) {
				
				Student student = new Student();			
				student.setStudentId(rs.getInt("STUDENT_ID"));
				student.setName(rs.getString("NAME"));
				student.setSchoolId(rs.getInt("SCHOOL_ID"));
				students.add(student);
				
			}
			
			return students;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Student> getAllStudentsBySchoolId(int id) {
		
		try(Connection conn = ConncetionUtil.createConnection()){
			String sql = "SELECT * FROM STUDENT WHERE SCHOOL_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			
			List<Student> students = new ArrayList<Student>();
			
			while(rs.next()) {
				
				Student student = new Student();			
				student.setStudentId(rs.getInt("STUDENT_ID"));
				student.setName(rs.getString("NAME"));
				student.setSchoolId(rs.getInt("SCHOOL_ID"));
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
		
		try(Connection conn = ConncetionUtil.createConnection()){
			
			String sql = "UPDATE browardschooldb.STUDENT SET NAME = ?, SCHOOL_ID = ? WHERE STUDENT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setInt(2, student.getSchoolId());
			ps.setInt(3, student.getStudentId());
			ps.execute();
			
			return student;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public boolean deleteStudent(Student student) {
		
		try(Connection conn = ConncetionUtil.createConnection()){
			String sql = "DELETE FROM STUDENT WHERE STUDENT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, student.getStudentId());
			boolean success = ps.execute();
			
			return success;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

}
