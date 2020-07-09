package dev.ranieri.daos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dev.ranieri.entities.School;

public class SchoolDAOLocal implements SchoolDAO {
	
	// DAOs should be singletons

	private static SchoolDAOLocal dao = null;
	
	private SchoolDAOLocal() {
		
	};
	
	public static SchoolDAO getSchoolDAO() {
		
		if(dao == null) {
			dao = new SchoolDAOLocal();
			return dao;
		}else {
			return dao;
		}
		
	}
	
	// we are going to emulate a database by using a map to store our schools in
	private Map<Integer,School> school_table = new HashMap<Integer,School>();
	
	private int counter = 1;

	@Override
	public School createSchool(School school) {
		school.setsId(counter);
		this.school_table.put(school.getsId(), school);
		this.counter++;	
		return school;
	}

	@Override
	public School getSchoolById(int id) {	
		return school_table.get(id);
	}

	@Override
	public Set<School> getAllSchools() {
		Set<School> schools = new HashSet<School>(school_table.values());
		return schools;
	}

	@Override
	public School updateSchool(School school) {
		school_table.put(school.getsId(), school);
		return school;
	}

	@Override
	public boolean deleteSchool(int id) {
		
		School s = school_table.remove(id);
		
		if(s != null) {
			return true;
		}else {
			return false;
		}
		
	}

}
