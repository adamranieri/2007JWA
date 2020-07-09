package dev.ranieri.daotests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dev.ranieri.daos.SchoolDAO;
import dev.ranieri.daos.SchoolDAOlocal;
import dev.ranieri.entities.School;
import junit.framework.Assert;

public class SchoolDAOtests {
	
	static SchoolDAO sdao = new SchoolDAOlocal();

	// All tests pass unless an exception or error is thrown
	// JUNIT does not care about your ordering
	// it will in a predetermined order outside of your control
	@Before
	public void setup() {
		School school = new School();
		school.setName("Coral Glades");
		school.setPhone(55555555);
		school.setCapacity(1000);		
		school = sdao.createSchool(school);
		
		School school2 = new School();
		school2.setName("Stoneman Douglass");
		school2.setPhone(9999999);
		school2.setCapacity(3000);		
		sdao.createSchool(school2);
		
	}
	
	
	@Test
	public void createSchoolTest() {
		
		School school = new School();
		school.setName("Coral Glades");
		school.setPhone(55555555);
		school.setCapacity(1000);
		
		school = sdao.createSchool(school);
		
		System.out.println(school);
		
		School school2 = new School();
		school2.setName("Stoneman Douglass");
		school2.setPhone(9999999);
		school2.setCapacity(3000);
		
		sdao.createSchool(school2);
		
		System.out.println(school2);
		
	}
	
	@Test
	public void getAllSchoolsTest() {	
		System.out.println(sdao.getAllSchools());
		
	}
	
	@Test
	public void getSchoolByNameTest() {
		School coral = sdao.getSchoolByName("Stoneman Douglass");
		System.out.println(coral);
	}

}
