package dev.ranieri.daos;

import java.util.Set;

import dev.ranieri.entities.School;

// Data Access Object is designed to perform CRUD operations on the entity
// You should only be dealing with School objects in the School DAO. Don't this DAO to try and save anything else!

// You want your method signatures to be as object centric as possible
public interface SchoolDAO {
	
	//Create
	School createSchool(School school); // think of this as a save method
	
	//Read
	School getSchoolById(int id);
	Set<School> getAllSchools();
	
	//Update
	School updateSchool(School school);
	
	
	//Delete
	boolean deleteSchool(int id);
	
}
