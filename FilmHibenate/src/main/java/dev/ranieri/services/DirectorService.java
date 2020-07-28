package dev.ranieri.services;

import dev.ranieri.entities.Director;
import dev.ranieri.entities.Movie;

public interface DirectorService {
	
	
	Director createDirector(Director director);
	
	Director addMovieToDirector(Director director, Movie movie);
	
	Director getDirectorById(int id);

}
