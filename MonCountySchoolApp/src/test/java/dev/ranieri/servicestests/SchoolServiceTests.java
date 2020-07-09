package dev.ranieri.servicestests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import dev.ranieri.daos.SchoolDAOLocal;
import dev.ranieri.entities.School;
import dev.ranieri.exceptions.NegativeCapcityException;
import dev.ranieri.services.SchoolService;
import dev.ranieri.services.SchoolServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
class SchoolServiceTests {
	
	private static SchoolService sserv = new SchoolServiceImpl();

	@Test
	@Order(1)
	void establishSchool() {		
		School brierSide = new School(0,"BrierSide", 60);
		sserv.establishSchool(brierSide);
		Assertions.assertNotEquals(0, brierSide.getsId());		
	}
	
	@Test
	@Order(2)
	void increaseCapacity() throws NegativeCapcityException {
		School brier = sserv.getSchoolById(1);
		sserv.increaseCapcity(brier, 25);
		Assertions.assertEquals(85, brier.getCapacity());
	}
	
	@Test
	@Order(3)
	void renameSchool() {
		School brier = sserv.getSchoolById(1);
		sserv.renameSchool(brier, "Wickerfield");
		Assertions.assertEquals("Wickerfield",brier.getName());
	}

	// Negative test
	
	void capcityIsNegative() {
		
		Exception e = assertThrows(NegativeCapcityException.class, ()->{
			School brier = sserv.getSchoolById(1);
			sserv.increaseCapcity(brier, -1000);		
		});
		
		Assertions.assertEquals("School capcity cannot be less than 0", e.getMessage());
		
	}

}






