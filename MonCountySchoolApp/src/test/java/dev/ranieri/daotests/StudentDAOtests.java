package dev.ranieri.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.ranieri.daos.StudentDAO;
import dev.ranieri.daos.StudentDAOMaria;
import dev.ranieri.entities.Student;

class StudentDAOtests {
	
	private static StudentDAO studao = StudentDAOMaria.getStudentDAOMaria(); 

	@Test
	void createStudent() {
		Student tim = new Student(0,"Tim",5);
		studao.createStudent(tim);
		Assertions.assertNotEquals(0, tim.getStuId());
	}
	
	@Test
	void getStudentById() {
		Student tim = studao.getStudentById(1);
		Assertions.assertNotNull(tim);
		Assertions.assertEquals(1, tim.getStuId());
	}

	@Test
	void getStudentsBySchoolId() {		
		Set<Student> students = studao.getStudentsBySchoolId(5);
		Assertions.assertEquals(2, students.size());		
	}
	
}
