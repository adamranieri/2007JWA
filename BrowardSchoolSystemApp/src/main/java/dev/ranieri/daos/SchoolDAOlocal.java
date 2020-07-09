package dev.ranieri.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.ranieri.entities.School;

public class SchoolDAOlocal implements SchoolDAO{
	
	private static Map<Integer,School> school_table = new HashMap<Integer,School>();
	private static int idGenerator = 101;

	// all entities have an id of 0
	// they have an id of 0 because they are not saved yet
	
	public School createSchool(School school) {	
		
		school.setSchoolId(idGenerator);
		idGenerator++;
		
		school_table.put(school.getSchoolId(), school);
		
		return school;
	}

	public School getSchoolById(int id) {
		return school_table.get(id);
	}


	public School getSchoolByName(String name) {
				
		for(School school : this.getAllSchools()) {
			
			if(school.getName().equals(name))
				return school;
			
		}
		
		return null;
	}

	public List<School> getAllSchools() {
		
		List<School> schools = new ArrayList<School>(school_table.values());		
		return schools;
	}

	
	public School updateSchool(School school) {
		school_table.put(school.getSchoolId(), school);
		return school;
	}

	public boolean deleteSchool(School school) {
		school_table.remove(school.getSchoolId());
		return true;
	}

}
