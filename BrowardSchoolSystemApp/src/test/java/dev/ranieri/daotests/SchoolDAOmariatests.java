package dev.ranieri.daotests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dev.ranieri.daos.SchoolDAO;
import dev.ranieri.daos.SchoolDAOmaria;
import dev.ranieri.entities.School;

public class SchoolDAOmariatests {

	public static SchoolDAO schooldao = new SchoolDAOmaria();
	
	@Test
	public void getSchoolById() {
		
		School coral = schooldao.getSchoolById(1);
		System.out.println(coral);
	}
	
	@Test
	public void deleteSchool() {
		School school = schooldao.getSchoolById(1);
		schooldao.deleteSchool(school);
		
	}
	
	@Test
	public void updateSchool() {
		School coral = schooldao.getSchoolById(1);
		coral.setName("Super Coral Glades");
		schooldao.updateSchool(coral);
		System.out.println(coral);
		
	}
	
	@Test
	public void getAllSchools() {
		List<School> schools = schooldao.getAllSchools();
		System.out.println(schools);
	}
	
	@Test
	public void getSchoolByName() {
		School douglas = schooldao.getSchoolByName("Stoneman Douglas");
		System.out.println(douglas);
	}
	
	@Test
	public void createSchool() {
		
		School douglas = new School();
		douglas.setName("Stoneman Douglas");
		douglas.setPhone(1111111);
		douglas.setCapacity(3000);
		
		schooldao.createSchool(douglas);
		System.out.println(douglas);
		
	}

}
