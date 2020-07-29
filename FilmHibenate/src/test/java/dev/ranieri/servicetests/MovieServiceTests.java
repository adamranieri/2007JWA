package dev.ranieri.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.ranieri.daos.DirectorDAO;
import dev.ranieri.daos.MovieDAO;
import dev.ranieri.entities.Director;
import dev.ranieri.entities.Movie;
import dev.ranieri.services.MovieService;
import dev.ranieri.services.MovieServiceImpl;

class MovieServiceTests {

	@Test
	void exampleMock() {
		Director mcFake = new Director(0,"Fakey McFakeFace");
		Movie scaryMovie = new Movie(0,"Scary Movie", 100,mcFake);
		Movie spaceBalls = new Movie(0,"Spaceballs",90,mcFake);
		List<Movie> fakeMovies = new ArrayList<Movie>();
		fakeMovies.add(spaceBalls);
		fakeMovies.add(scaryMovie);
		
		
		MovieService mockService = Mockito.mock(MovieService.class); // returns a fake version of MovieService
		// we can edit this fake version to give results that we want
		Mockito.when(mockService.getAllMovies()).thenReturn(fakeMovies);
		
		
		System.out.println(mockService.getAllMovies());
	}
	
	// Often our code is dependent on the functionality in the layer below it
	// My services will not work correctly unless the dao methods that they call work correctly
	// Mocking is a way to imitate a perfectly working dependency so that I can test the functionality
	// solely in my layer
	
	@Test
	void orderByRuntimeAsc() {
		Director mcFake = new Director(0,"Fakey McFakeFace");
		Movie scaryMovie = new Movie(0,"Scary Movie", 100,mcFake);
		Movie spaceBalls = new Movie(0,"Spaceballs",90,mcFake);
		Movie nakedGun = new Movie(0,"Naked Gun",85,mcFake);
		
		List<Movie> fakeMovies = new ArrayList<Movie>();
		fakeMovies.add(spaceBalls);
		fakeMovies.add(scaryMovie);
		fakeMovies.add(nakedGun);
		
		MovieDAO mdao = Mockito.mock(MovieDAO.class);
		DirectorDAO ddao = Mockito.mock(DirectorDAO.class);
		Mockito.when(mdao.getAllMovies()).thenReturn(fakeMovies);
		
		MovieService mserv = new MovieServiceImpl(mdao,ddao);
		
		List<Movie> movies = mserv.orderByRutime(true);
		
		System.out.println(movies);
		
		Assertions.assertEquals(85, movies.get(0).getRuntime());
		Assertions.assertEquals(90, movies.get(1).getRuntime());
		Assertions.assertEquals(100, movies.get(2).getRuntime());
		
		
	}
	
	@Test
	void orderByRuntimeDsc() {
		Director mcFake = new Director(0,"Fakey McFakeFace");
		Movie scaryMovie = new Movie(0,"Scary Movie", 100,mcFake);
		Movie spaceBalls = new Movie(0,"Spaceballs",90,mcFake);
		Movie nakedGun = new Movie(0,"Naked Gun",85,mcFake);
		
		List<Movie> fakeMovies = new ArrayList<Movie>();
		fakeMovies.add(spaceBalls);
		fakeMovies.add(scaryMovie);
		fakeMovies.add(nakedGun);
		
		MovieDAO mdao = Mockito.mock(MovieDAO.class);
		DirectorDAO ddao = Mockito.mock(DirectorDAO.class);
		Mockito.when(mdao.getAllMovies()).thenReturn(fakeMovies);
		
		MovieService mserv = new MovieServiceImpl(mdao,ddao);
		
		List<Movie> movies = mserv.orderByRutime(false);
		
		System.out.println(movies);
		
		Assertions.assertEquals(100, movies.get(0).getRuntime());
		Assertions.assertEquals(90, movies.get(1).getRuntime());
		Assertions.assertEquals(85, movies.get(2).getRuntime());
		
		
	}
	
	@Test
	void averageRuntime() {
		
			Director mcFake = new Director(0,"Fakey McFakeFace");
			Movie scaryMovie = new Movie(0,"Scary Movie", 95,mcFake);
			Movie spaceBalls = new Movie(0,"Spaceballs",90,mcFake);
			Movie nakedGun = new Movie(0,"Naked Gun",85,mcFake);
			
			List<Movie> fakeMovies = new ArrayList<Movie>();
			fakeMovies.add(spaceBalls);
			fakeMovies.add(scaryMovie);
			fakeMovies.add(nakedGun);
			
			MovieDAO mdao = Mockito.mock(MovieDAO.class);
			DirectorDAO ddao = Mockito.mock(DirectorDAO.class);
			Mockito.when(mdao.getAllMovies()).thenReturn(fakeMovies);
			
			MovieService mserv = new MovieServiceImpl(mdao,ddao);
			
			int average = mserv.averageRuntimeOfMovies();
			Assertions.assertEquals(90, average);
		
	}
	
	
	
}
