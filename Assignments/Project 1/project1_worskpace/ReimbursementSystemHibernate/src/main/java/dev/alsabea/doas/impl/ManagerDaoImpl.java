package dev.alsabea.doas.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import dev.alsabea.connection.HibernateConnectionEstablisher;
import dev.alsabea.doas.ManagerDao;
import dev.alsabea.entities.Manager;

public class ManagerDaoImpl implements ManagerDao {

	

	private static SessionFactory sf = HibernateConnectionEstablisher.getSessionFactory();
	private static ManagerDaoImpl md;
	
	
	
	private ManagerDaoImpl() {
		super();
	}

	public static ManagerDaoImpl getInstance() {
		if (md ==null)
			md = new ManagerDaoImpl();
		return md;
	}

	
	
	@Override
	public long createInstance(Manager t) {
		long generatedId = 0;
		try (Session s = sf.openSession()) {

			s.beginTransaction();

			generatedId = ((Long) s.save(t)).longValue();

			s.getTransaction().commit();
		}

		return generatedId;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Manager retrieveByUsernameAndPassword(String username, String password) {

		try (Session sess = sf.openSession()) {

			@SuppressWarnings("deprecation")
			Criteria crit  = sess.createCriteria(Manager.class);
			crit.add(Restrictions.like("username", username));
			crit.add(Restrictions.ilike("password", password));
			return (Manager) crit.uniqueResult();
		} catch(HibernateException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	public Manager retrieveById(long key) {
		
		return sf.openSession().get(Manager.class, key);
	}

	@Override
	public boolean update(Manager t) {

		
		try(Session s= sf.openSession()){
			s.beginTransaction();
			s.update(t);
			s.getTransaction().commit();
			return true;
		}catch(javax.persistence.OptimisticLockException e) {
			//e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean deleteById(long key) {

		try(Session s = sf.openSession()){
			s.beginTransaction();
			s.delete(new Manager(key));
			s.getTransaction().commit();
			return true;
		} catch (javax.persistence.OptimisticLockException e) {
			//e.printStackTrace();
			return false;
		}
	}







}
