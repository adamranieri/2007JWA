package dev.ranieri.services;

import java.util.List;

import dev.ranieri.entities.Movie;

public interface MovieService {
	
	//CRUD
	
	List<Movie> getAllMovies();
	Movie getMovieById(int id);
	
	
	// A method to return movies by runtime
	List<Movie> orderByRutime(boolean ascdending);	
	int averageRuntimeOfMovies();
	

}
