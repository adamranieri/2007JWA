package dev.ranieri.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import dev.ranieri.daos.DirectorDAO;
import dev.ranieri.daos.DirectorDAOhibenate;
import dev.ranieri.entities.Director;

@TestMethodOrder(OrderAnnotation.class) 
class DirectorDAOtests {
	
	private static DirectorDAO ddao = new DirectorDAOhibenate();
	

	@Test
	@Order(1)
	void createDirector() { 
		// an object with an ID of 0 is understood to not be a record in the database
		Director kyle = new Director(0,"Kyle Ford Coppola");
		ddao.createDirector(kyle);
		Assertions.assertNotEquals(0, kyle.getdId());
	}
	
	@Test
	@Order(2)
	void getDirectorById() {
		Director director = ddao.getDirectorById(1);
		Assertions.assertEquals(1, director.getdId());
	}
	
	@Test
	@Order(3)
	void updateDirector() {
		Director director = ddao.getDirectorById(1);
		director.setName("Kyle Kubrick");
		ddao.updateDirector(director);	
		Assertions.assertEquals("Kyle Kubrick",director.getName());
	}
	
	@Test
	@Order(4)
	void getDirectorsByName() {
		List<Director> directors = ddao.getDirectorsByName("Kyle Kubrick");
		Assertions.assertEquals(1, directors.size());
		
	}
	
	@Test
	@Order(5)
	void deleteDirector() {
		
		Director director = ddao.getDirectorById(1);	
		boolean result = ddao.deleteDirector(director);
		Assertions.assertEquals(true,result);

	}
	
	

}
