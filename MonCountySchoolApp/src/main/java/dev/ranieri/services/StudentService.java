package dev.ranieri.services;

import java.util.Set;

import dev.ranieri.entities.School;
import dev.ranieri.entities.Student;

public interface StudentService {
	
	
	//CRUD
	//Create
	Student enrollStudent(Student student);
	
	//Read
	Student getStudentById(int id);

	Set<Student> getStudentsBySchoolId(int id);
	Set<Student> getStudentsBySchoolId(School school);
	Set<Student> getAllStudents();
	
	//update
	Student updateStudent(Student student);
	
	//delete
	boolean deleteStudent(int id);
	boolean deleteStudent(Student student);
	
		
	// Higher level service method
	// Transfer student
	Student transferStudent(Student student , School school);
	

}
