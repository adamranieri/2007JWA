package dev.ranieri.daos;

import java.util.List;



import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import dev.ranieri.entities.Director;
import dev.ranieri.utils.HibernateUtil;

public class DirectorDAOhibenate implements DirectorDAO{
	
	// session factory creates sessions
	private static SessionFactory sf = HibernateUtil.getSessionFactory();

	@Override
	public Director createDirector(Director director) {
		// session is the primary interface for performing CRUD operations in Hibernate
		Session sess = sf.openSession(); // open a session
		sess.beginTransaction(); // begin a transaction
		
		
		sess.save(director); // this will save our director object		
		
		sess.getTransaction().commit(); // commit the transaction
		sess.close(); // close a session
		
		return director;
	}

	@Override
	public Director getDirectorById(int id) {
		Session sess = sf.openSession(); 
				
		Director director = sess.get(Director.class, id);
		sess.close(); 
		return director;
	}

	@Override
	public Director updateDirector(Director director) {
		Session sess = sf.openSession(); 
		sess.beginTransaction(); 
		
		sess.update(director);
		
		sess.getTransaction().commit(); 
		sess.close(); 
		
		return director;
	}

	@Override
	public boolean deleteDirector(Director director) {
		
		Session sess = sf.openSession(); 
		sess.beginTransaction(); 
		
		sess.delete(director);
		
		sess.getTransaction().commit(); 
		sess.close(); 
		
		return true;
	}

	@Override
	public List<Director> getDirectorsByName(String name) {
		
//		try(Session sess = sf.openSession()){
//			
//			// criteria interface for querying in hibernate
//			// very OOP centric
//			
//			Criteria crit = sess.createCriteria(Director.class);
//			crit.add(Restrictions.like("name", name)); // you can add as many restrictions as you want
//			List<Director> directors = crit.list();
//			
//			return directors;
//			
//		}
		
		// HQL can also be used to query
		// Hibernate Query Language . OOP 
		
		try(Session sess = sf.openSession()){
			
			
			// HQL does not reference the table it references the fields
			String hql = "FROM Director d where d.name = '"+name +"'";
			Query q = sess.createQuery(hql);
			
			List<Director> directors = q.list();
			return directors;
			
		}
		
	}

	@Override
	public List<Director> getAllDirectors() {
		Session sess = sf.openSession();
		Criteria crit = sess.createCriteria(Director.class);
		List<Director> directors = crit.list();
			
		return directors;
	}

}
