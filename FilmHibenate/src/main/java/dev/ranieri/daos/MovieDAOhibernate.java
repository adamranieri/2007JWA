package dev.ranieri.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dev.ranieri.entities.Movie;
import dev.ranieri.utils.HibernateUtil;

public class MovieDAOhibernate implements MovieDAO {
	
	private static SessionFactory sf = HibernateUtil.getSessionFactory();

	@Override
	public Movie createMovie(Movie movie) {
		Session sess = sf.openSession();
		sess.beginTransaction();
		
		sess.save(movie);
		
		sess.getTransaction().commit();
		sess.close();
		
		return movie;
	}

	@Override
	public Movie getMovieById(int id) {
		
		Session sess = sf.openSession();		
		Movie movie = sess.get(Movie.class, id);		
		sess.close();
		
		return movie;
		
	}

	@Override
	public Movie updateMovie(Movie movie) {
		
		Session sess = sf.openSession();
		sess.beginTransaction();
		
		sess.update(movie);
		
		sess.getTransaction().commit();
		sess.close();
		
		return movie;
	}

	@Override
	public boolean deleteMovie(Movie movie) {
		
		Session sess = sf.openSession();
		sess.beginTransaction();
		
		sess.delete(movie);
		
		sess.getTransaction().commit();
		sess.close();
		
		return true;
	}

	@Override
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
