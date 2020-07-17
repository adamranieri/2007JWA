package dev.ranieri.services;

import java.util.Set;

import dev.ranieri.daos.StudentDAO;
import dev.ranieri.daos.StudentDAOMaria;
import dev.ranieri.entities.School;
import dev.ranieri.entities.Student;

public class StudentServiceImpl implements StudentService {
	
	private static StudentDAO studao = StudentDAOMaria.getStudentDAOMaria();

	@Override
	public Student enrollStudent(Student student) {		
		return studao.createStudent(student);
	}

	@Override
	public Student getStudentById(int id) {
		return studao.getStudentById(id);
	}

	@Override
	public Set<Student> getStudentsBySchoolId(int id) {		
		return studao.getStudentsBySchoolId(id);
	}

	@Override
	public Set<Student> getStudentsBySchoolId(School school) {	
		return this.getStudentsBySchoolId(school.getsId());
	}

	@Override
	public Set<Student> getAllStudents() {	
		return studao.getAllStudents();
	}

	@Override
	public Student updateStudent(Student student) {
		return studao.updateStudent(student);
	}

	@Override
	public boolean deleteStudent(int id) {
		return studao.deleteStudentById(id);
	}

	@Override
	public boolean deleteStudent(Student student) {
		return this.deleteStudent(student.getStuId());
	}

	
	
	// higher level
	@Override
	public Student transferStudent(Student student, School school) {
	
		
		return null;
	}

}
