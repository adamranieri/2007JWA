package dev.ranieri.services;

import dev.ranieri.entities.School;
import dev.ranieri.entities.Student;

// service responsible for enrolling students
// expelling students
// transfer students
public interface RegistrarService {
	
	Student enrollStudent(Student student);
	Student enrollStudent(Student student, School school);
	
	Student expelStudent(Student student);
	
	Student transferStudent(Student student, School school);
	

}
