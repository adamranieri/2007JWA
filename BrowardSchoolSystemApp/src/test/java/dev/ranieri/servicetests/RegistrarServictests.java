package dev.ranieri.servicetests;

import static org.junit.Assert.*;

import org.junit.Test;

import dev.ranieri.daos.StudentDAO;
import dev.ranieri.daos.StudentDAOlocal;
import dev.ranieri.entities.School;
import dev.ranieri.entities.Student;
import dev.ranieri.services.RegistrarService;
import dev.ranieri.services.RegistrarServiceImpl;
import dev.ranieri.services.SchoolService;
import dev.ranieri.services.SchoolServiceImpl;

public class RegistrarServictests {
	
	private static RegistrarService rs = new RegistrarServiceImpl();
	private static SchoolService ss = new SchoolServiceImpl();
	
	@Test
	public void test() {
		School glades = new School();
		glades.setName("Coral Glades");
		glades.setCapacity(1000);		

		Student adam = new Student();
		adam.setName("Adam");

		ss.establishSchool(glades);
		rs.enrollStudent(adam,glades);

		
	}

}
