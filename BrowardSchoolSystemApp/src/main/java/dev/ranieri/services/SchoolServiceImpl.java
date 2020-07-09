package dev.ranieri.services;

import dev.ranieri.daos.SchoolDAO;
import dev.ranieri.daos.SchoolDAOlocal;
import dev.ranieri.daos.SchoolDAOmaria;
import dev.ranieri.entities.School;

public class SchoolServiceImpl implements SchoolService{
	
	private SchoolDAO schooldao = new SchoolDAOmaria();

	public School establishSchool(School school) {
		schooldao.createSchool(school);
		return school;
	} 

}
