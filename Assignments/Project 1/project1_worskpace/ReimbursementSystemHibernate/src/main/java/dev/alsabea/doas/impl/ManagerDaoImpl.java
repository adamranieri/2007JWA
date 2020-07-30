package dev.alsabea.doas.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dev.alsabea.connection.HibernateConnectionEstablisher;
import dev.alsabea.doas.ManagerDao;
import dev.alsabea.entities.Employee;
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
	public List<Manager> retrieveAll() {

		try (Session sess = sf.openSession()) {

			@SuppressWarnings("deprecation")
			Criteria crit = sess.createCriteria(Manager.class);
			return crit.list();

		}

	}
	
	@Override
	public Manager retrieveById(long key) {
		
		return sf.openSession().get(Manager.class, key);
	}

	@Override
	public boolean update(Manager t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(long key) {
		// TODO Auto-generated method stub
		return false;
	}







}
