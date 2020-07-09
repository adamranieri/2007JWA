package dev.ranieri.servicetests;

import static org.junit.Assert.*;

import org.junit.Test;

import dev.ranieri.entities.School;
import dev.ranieri.services.SchoolService;
import dev.ranieri.services.SchoolServiceImpl;

public class SchoolServicetests {
	
	private static SchoolService schoolserv = new SchoolServiceImpl();

	@Test
	public void establishSchool() {
		
		School school = new School();
		school.setName("Coral Springs High");
		school.setPhone(77777777);
		school.setCapacity(1000);
		
		schoolserv.establishSchool(school);
		System.out.println(school);
	}

}
