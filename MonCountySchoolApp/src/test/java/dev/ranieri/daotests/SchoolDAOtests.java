package dev.ranieri.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import dev.ranieri.daos.SchoolDAO;
import dev.ranieri.daos.SchoolDAOLocal;
import dev.ranieri.entities.School;

// JUnit does not run your tests in order UNLESS you define it
@TestMethodOrder(OrderAnnotation.class) // necessary to order your tests
class SchoolDAOtests {
	
	public static SchoolDAO sdao = SchoolDAOLocal.getSchoolDAO();

	@Test
	@Order(1)
	void createSchool() {
		School monHigh = new School(0,"Monongalia Highschool", 600);// all entities have an id of zero until you save/create them
		// JSON format {"sId":0, "name": "Monongalia Highschool", "capacity": 600}
		
		sdao.createSchool(monHigh);
		Assertions.assertNotEquals(0, monHigh.getsId());
	}
	
	@Test
	@Order(2)
	void getSchoolById() {		
		School monHigh = sdao.getSchoolById(1);
		Assertions.assertEquals(1, monHigh.getsId());
	}
	
	@Test
	@Order(3)
	void getAllSchools() {
		School suncrestElem = new School(0,"Suncrest Elementary",15);
		sdao.createSchool(suncrestElem);
		Set<School> schools = sdao.getAllSchools();
		Assertions.assertEquals(2,schools.size());
	}
	
	@Test
	@Order(4)
	void updateSchool() {
		School mon = sdao.getSchoolById(1);
		mon.setName("Downtown Highschool");
		mon = sdao.updateSchool(mon); //saves the changes to that school
		Assertions.assertEquals("Downtown Highschool", mon.getName());
		System.out.println(mon);
		
	}
	
	@Test
	@Order(5)
	void deleteSchool() {
		boolean result = sdao.deleteSchool(1);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(6)
	void deleteSchoolNegative() {
		boolean result = sdao.deleteSchool(10);
		Assertions.assertEquals(false, result);
	}
	

}
