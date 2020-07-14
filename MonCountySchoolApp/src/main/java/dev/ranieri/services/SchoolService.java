package dev.ranieri.services;

import dev.ranieri.entities.School;
import dev.ranieri.exceptions.NegativeCapcityException;

// services usually have CRUD operations as well as higher level operations
public interface SchoolService {
	
	// CRUD Like operations 
	School establishSchool(School school);
	School getSchoolById(int id);
	School updateSchool(School school);
	boolean deleteSchoolById(int id);
	
	
	//Higher level operations that are not just pure CRUD
	/**
	 * This method increases a school's capcity by a certain amount
	 * 
	 * @param school
	 * @param amount
	 * @return
	 */
	School increaseCapcity(School school, int amount) throws NegativeCapcityException;
	
	School renameSchool(School school, String newName);
	
	

}
