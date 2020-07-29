package dev.ranieri.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dev.ranieri.daos.DirectorDAO;
import dev.ranieri.daos.DirectorDAOhibenate;
import dev.ranieri.daos.MovieDAO;
import dev.ranieri.daos.MovieDAOhibernate;
import dev.ranieri.entities.Movie;

public class MovieServiceImpl implements MovieService {
	
	// Services are dependent on DAOS
	// A highly scalable way of developing our layers is through dependency injection
	// this is creating out service with instances of the objects needed to perform my method
	private MovieDAO mdao;
	private DirectorDAO ddao;
	
	
	// consturctor that creates out implementation with completed daos
	public MovieServiceImpl() {
		super();
		this.ddao = new DirectorDAOhibenate();
		this.mdao = new MovieDAOhibernate();
	}
	
	// This is dependency injection, We are creating our instance with the dependecies passed in via the constrcutor
	public MovieServiceImpl(MovieDAO mdao, DirectorDAO ddao) {
		this.ddao = ddao;
		this.mdao = mdao;		
	}

	// these methods are unsuitable for mocking. No real logic or code in the service method
	@Override
	public List<Movie> getAllMovies() {
		return this.mdao.getAllMovies();
	}

	@Override
	public Movie getMovieById(int id) {
		return this.getMovieById(id);
	}

	@Override // method suitable for mocking. Performs logic that is independeing of the daos
	public List<Movie> orderByRutime(boolean ascdending) {
		List<Movie> movies = this.mdao.getAllMovies();
		
		
		// comparator is a lambda that we can pass into sort that works identical to the compareTo method
		Comparator<Movie> runtimeOrderer = (movie1,movie2)->{
			if(movie1.getRuntime() <movie2.getRuntime() ) {
				return -1;
			}
			if(movie1.getRuntime() >movie2.getRuntime() ) {
				return 1;
			}
			return 0;
		};
		
		// callback function
		Collections.sort(movies, runtimeOrderer);
		
		if(ascdending == true) {
			return movies;
		}else {
			 Collections.reverse(movies);
			 return movies;
		}
		
		
	}

	@Override
	public int averageRuntimeOfMovies() {
		List<Movie> movies = this.mdao.getAllMovies();
		int sum = 0;
		
		for(Movie m : movies) {
			sum = sum + m.getRuntime();
		}
		
		return sum/movies.size();
	}

}
