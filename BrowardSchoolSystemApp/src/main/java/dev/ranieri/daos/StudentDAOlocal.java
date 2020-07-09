package dev.ranieri.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.ranieri.entities.Student;

public class StudentDAOlocal implements StudentDAO {
	
	private static Map<Integer,Student> student_table = new HashMap<Integer,Student>();
	private static int idGenerator = 201;

	public Student createStudent(Student student) {
		
		student.setStudentId(idGenerator);
		idGenerator++;
		
		student_table.put(student.getStudentId(), student);
		
		return student;
	}

	public Student getStudentById(int id) {
		return student_table.get(id);
	}

	public List<Student> getAllStudent() {
		List<Student> students = new ArrayList<Student>(student_table.values());
		return students;
	}

	public List<Student> getAllStudentsBySchoolId(int id) {
		
		List<Student> cohort = new ArrayList<Student>();
		
		for(Student s : getAllStudent()) {
			
			if(s.getSchoolId() == id)
				cohort.add(s);
		}
		
		
		return cohort;
	}

	public Student updateStudent(Student student) {
		
		student_table.put(student.getStudentId(), student);
		return student;
	}

	public boolean deleteStudent(Student student) {
		
		student_table.remove(student.getStudentId());
		return true;
	}

}
