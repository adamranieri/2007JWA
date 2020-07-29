package dev.ranieri.daotests;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import dev.ranieri.entities.Actor;
import dev.ranieri.entities.Director;
import dev.ranieri.entities.Movie;
import dev.ranieri.utils.HibernateUtil;

class MultiplicityTests {

	private SessionFactory sf = HibernateUtil.getSessionFactory();
	
	@Test
	// Whenever you have nested objects in an entity 
	// if the nested objects are a many (a list, or a set) Hibernate will not query and get them unless
	// you specifically asked or call a method that uses that list or set
	void lazyLoading() {
		Session sess = sf.openSession();
		
		Director steven = sess.get(Director.class, 1);
		System.out.println(steven);
		//System.out.println(steven.getMovies());
		
		sess.close();
		
		// lazy initalization exception because the movies were never queried and loaded into the director object
		System.out.println(steven.getMovies());
		
	}
	
	@Test
	void addMovie() {
		// we can add the movie object directly, Our movie object needs to have a director object within it
		// that way hibernate can read that director object and input in the database correctly
		Session  sess = sf.openSession();
		Director steven = sess.get(Director.class, 1);
		
		Movie m = new Movie(0,"Jaws",90,steven);
		// we need the steven object in our movie otherwise it won't save correctly with
		// steven as the director
		
		sess.beginTransaction();
		
		sess.save(m); // us saving the movie object
		
		sess.getTransaction().commit();
		sess.close();
		
				
	}
	
	@Test
	void addMovieThroughParent() {
		
		Session  sess = sf.openSession();
		Director steven = sess.get(Director.class, 1);
		
		Movie m = new Movie(0,"Jaws 2 Electric Boogaloo",95,steven);
		steven.getMovies().add(m);
		
		sess.beginTransaction();
		
		sess.update(steven);
		
		sess.getTransaction().commit();
		sess.close();
		
	}
	
	@Test
	void alterRuntime() {
		
		Session  sess = sf.openSession();
		Director steven = sess.get(Director.class, 1);
		
		steven.getMovies().get(1).setRuntime(150);
		
		sess.beginTransaction();
		
		sess.update(steven);
		
		sess.getTransaction().commit();
		sess.close();
		
	}
	
	@Test
	void viewActorMovies() {
		Session sess = sf.openSession();
		Actor actor = sess.get(Actor.class, 1);
		
		System.out.println(actor);
		System.out.println(actor.getMovies());
		
		sess.close();
	}
	
	@Test
	void viewMoviesActor() {
		Session sess = sf.openSession();
		Movie m = sess.get(Movie.class, 10);
		System.out.println(m);
		System.out.println(m.getActors());
		sess.close();
		
	}
	
	// General tips when using hiberante. Do not overuse hibernate. You have services that can help you
	// Let hibernate do the CRUD operations and querying
	// I generally like to update to entities directly rather than persisting through a parent
	// I generally like using the parent.getChildren() as a purely read operation
	
	
	
	

}
