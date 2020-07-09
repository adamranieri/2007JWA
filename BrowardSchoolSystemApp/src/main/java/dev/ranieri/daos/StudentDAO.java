package dev.ranieri.daos;

import java.util.List;

import dev.ranieri.entities.Student;

public interface StudentDAO {
	
	
	Student createStudent(Student student);
	
	Student getStudentById(int id);
	List<Student> getAllStudent();
	List<Student> getAllStudentsBySchoolId(int id);
	
	Student updateStudent(Student student);
	
	boolean deleteStudent(Student student);

}
