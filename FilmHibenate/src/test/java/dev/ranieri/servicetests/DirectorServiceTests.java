package dev.ranieri.servicetests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dev.ranieri.entities.Director;
import dev.ranieri.entities.Movie;
import dev.ranieri.services.DirectorService;
import dev.ranieri.services.DirectorServiceImpl;

class DirectorServiceTests {
	
	public static DirectorService dirserv = new DirectorServiceImpl();

	@Test
	void createDirector() {
		Director dir = new Director(0,"Adam Abrams");
		dirserv.createDirector(dir);
	}
	
	@Test
	void addMovie() {
		Director dir = dirserv.getDirectorById(2);
		Movie movie = new Movie (0,"There will be blood",160,dir);
		dirserv.addMovieToDirector(dir, movie);
	}

}
