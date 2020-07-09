package dev.ranieri.daos;

import java.util.List;

import dev.ranieri.entities.School;

public interface SchoolDAO {
	
	// DAOs should have all CRUD functionality
	//CREATE
	//READ
	//UPDATE
	//DELETE
	
	// Use objects whenever you can
	// Return objects when you can
	School createSchool(School school);
		
	School getSchoolById(int id);
	School getSchoolByName(String name);
	List<School> getAllSchools();
	
	School updateSchool(School school);
	
	boolean deleteSchool(School School);
	
}
