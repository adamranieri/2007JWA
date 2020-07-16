package dev.ranieri.daos;

import java.util.Set;

import dev.ranieri.entities.Student;

public interface StudentDAO {
	
	// Create
	Student createStudent(Student student);
	
	//read
	Set<Student> getAllStudents();	
	Student getStudentById(int id);
	Set<Student> getStudentsBySchoolId(int id);
	
	//update 
	Student updateStudent(Student student);
	
	//delete
	boolean deleteStudentById(int id);
	
	

}
