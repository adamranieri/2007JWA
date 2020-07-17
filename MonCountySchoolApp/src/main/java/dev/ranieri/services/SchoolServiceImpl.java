package dev.ranieri.services;

import java.util.HashSet;
import java.util.Set;

import dev.ranieri.daos.SchoolDAO;
import dev.ranieri.daos.SchoolDAOLocal;
import dev.ranieri.daos.SchoolDAOMaria;
import dev.ranieri.daos.StudentDAO;
import dev.ranieri.daos.StudentDAOMaria;
import dev.ranieri.entities.School;
import dev.ranieri.entities.Student;
import dev.ranieri.exceptions.NegativeCapcityException;

public class SchoolServiceImpl implements SchoolService {
	
	private static SchoolDAO sdao = SchoolDAOMaria.getSchoolDAOMaria(); // we already have our basic CRUD operations
	private static StudentDAO studao = StudentDAOMaria.getStudentDAOMaria();
	
	@Override
	public School establishSchool(School school) {
		return sdao.createSchool(school);
	}

	@Override
	public School increaseCapcity(School school, int amount) throws NegativeCapcityException {
		
	
			
			if((amount + school.getCapacity())<0 )
				throw new NegativeCapcityException();
			
		
		
		school.setCapacity(school.getCapacity() + amount);
		return school;
	}

	@Override
	public School renameSchool(School school, String newName) {
		school.setName(newName);
		sdao.updateSchool(school);
		return school;
	}

	// a school can have students
	// our serivce should use the school dao to get the school object
	// then use our student dao to get students who go to that school and attach that to the object then 
	// return that completed object
	@Override
	public School getSchoolById(int id) {
		School school = sdao.getSchoolById(id);
		Set<Student> students = studao.getStudentsBySchoolId(id);
		if(school == null) {
			return null;
		}
		school.setStudents(students);		
		return school;
	}

	@Override
	public School updateSchool(School school) {		
		return sdao.updateSchool(school);
	}

	@Override
	public boolean deleteSchoolById(int id) {	
		return sdao.deleteSchool(id);
	}

	@Override
	public Set<School> getAllSchools() {
		return sdao.getAllSchools();
	}

	@Override
	public Set<School> getSchoolsByCapacityLessThan(int num) {
		
		Set<School> smallSchools = new HashSet<School>();
		
		for(School school : sdao.getAllSchools()) {
			
			if(school.getCapacity()<num) {
				smallSchools.add(school);
			}
		}
		
		return smallSchools;
	}

}
