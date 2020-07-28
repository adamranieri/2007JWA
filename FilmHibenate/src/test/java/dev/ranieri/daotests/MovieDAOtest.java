package dev.ranieri.daotests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dev.ranieri.daos.MovieDAO;
import dev.ranieri.daos.MovieDAOhibernate;
import dev.ranieri.entities.Movie;

class MovieDAOtest {
	
	private static MovieDAO mdao = new MovieDAOhibernate();

	@Test
	void getMovieById() {
		
		Movie movie = mdao.getMovieById(3);	
		System.out.println(movie);
	}

}
