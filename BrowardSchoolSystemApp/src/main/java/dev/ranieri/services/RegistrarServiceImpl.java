package dev.ranieri.services;

import dev.ranieri.daos.SchoolDAO;
import dev.ranieri.daos.SchoolDAOlocal;
import dev.ranieri.daos.SchoolDAOmaria;
import dev.ranieri.daos.StudentDAO;
import dev.ranieri.daos.StudentDAOlocal;
import dev.ranieri.daos.StudentDAOmaria;
import dev.ranieri.entities.School;
import dev.ranieri.entities.Student;

public class RegistrarServiceImpl implements RegistrarService{
	
	private SchoolDAO schooldao = new SchoolDAOmaria();
	private StudentDAO studentdao = new StudentDAOmaria();

	public Student enrollStudent(Student student) {
		this.studentdao.createStudent(student);
		return student;
	}

	public Student enrollStudent(Student student, School school) {
		student.setSchoolId(school.getSchoolId());
		this.studentdao.createStudent(student);		
		school.getStudents().add(student);
		return student;
	}

	public Student expelStudent(Student student) {
		this.studentdao.deleteStudent(student);
		School school = schooldao.getSchoolById(student.getSchoolId());
		school.getStudents().remove(student);
		student.setSchoolId(-1);
		return student;
	}

	public Student transferStudent(Student student, School school) {
		School oldschool = schooldao.getSchoolById(student.getSchoolId());
		oldschool.getStudents().remove(student);
		
		student.setSchoolId(school.getSchoolId());
		this.studentdao.updateStudent(student);	
		school.getStudents().add(student);
		return student;
	}

}
