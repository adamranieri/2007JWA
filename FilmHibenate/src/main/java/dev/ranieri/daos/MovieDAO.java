package dev.ranieri.daos;

import java.util.List;

import dev.ranieri.entities.Movie;

public interface MovieDAO {
	
	Movie createMovie(Movie movie);
	
	Movie getMovieById(int id);
	
	Movie updateMovie(Movie movie);
	
	boolean deleteMovie(Movie movie);
	
	List<Movie> getAllMovies();

}
