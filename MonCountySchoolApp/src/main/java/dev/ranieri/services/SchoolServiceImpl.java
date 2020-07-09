package dev.ranieri.services;

import dev.ranieri.daos.SchoolDAO;
import dev.ranieri.daos.SchoolDAOLocal;
import dev.ranieri.entities.School;
import dev.ranieri.exceptions.NegativeCapcityException;

public class SchoolServiceImpl implements SchoolService {
	
	private static SchoolDAO sdao = SchoolDAOLocal.getSchoolDAO(); // we already have our basic CRUD operations

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

	@Override
	public School getSchoolById(int id) {
		return sdao.getSchoolById(id);
	}

}
