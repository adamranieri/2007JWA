package dev.ranieri.injections;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import dev.ranieri.entities.Student;
import dev.ranieri.utils.ConnectionUtil;

class SQLinjections {
	
	public static Student getStudentByUsername(String name ) {
			try(Connection conn = ConnectionUtil.getConnection()){
				String sql = "SELECT * FROM monschool_db.student WHERE name = '"+ name +"' ;";
				Statement state = conn.createStatement();
				ResultSet rs = state.executeQuery(sql);
				
				rs.next();
				
				Student student = new Student();
				student.setName(rs.getString("name"));
				student.setStuId(rs.getInt("stu_id"));
				student.setsId(rs.getInt("s_id"));
				
				rs.next();
				
				Student student2 = new Student();
				student2.setName(rs.getString("name"));
				student2.setStuId(rs.getInt("stu_id"));
				student2.setsId(rs.getInt("s_id"));
				
				System.out.println(student);
				System.out.println(student2);
				
			
				return student;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

	@Test
	void SQLinjection() {
		
		Student student = getStudentByUsername("'; DROP TABLE student;  -- Hacker Adam");
		System.out.println(student);
	}

}
