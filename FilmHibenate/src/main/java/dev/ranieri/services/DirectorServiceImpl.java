package dev.ranieri.services;

import dev.ranieri.daos.DirectorDAO;
import dev.ranieri.daos.DirectorDAOhibenate;
import dev.ranieri.daos.MovieDAO;
import dev.ranieri.daos.MovieDAOhibernate;
import dev.ranieri.entities.Director;
import dev.ranieri.entities.Movie;

public class DirectorServiceImpl implements DirectorService {

	public MovieDAO mdao = new MovieDAOhibernate();
	public DirectorDAO ddao = new DirectorDAOhibenate();
	
	@Override
	public Director createDirector(Director director) {
		return this.ddao.createDirector(director);
	}

	@Override
	public Director addMovieToDirector(Director director, Movie movie) {
		movie.setDirector(director);
		director.getMovies().add(this.mdao.createMovie(movie));
		return director;
	}

	@Override
	public Director getDirectorById(int id) {		
		return this.ddao.getDirectorById(id);
	}

}
