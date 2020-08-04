package dev.kurt.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import dev.kurt.entities.Manager;
import dev.kurt.utils.HibernateUtil;

public class ManagerDAOHibernate implements ManagerDAO {
	
	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	
	
	@Override
	public Manager createManager(Manager manager) {
		Session sess = sf.openSession();
		sess.beginTransaction();		
		
		sess.save(manager);
		
		sess.getTransaction().commit();
		sess.close();
		
		return manager;
	}

	@Override
	public Manager getManagerById(int id) {
		Session sess = sf.openSession();	
		Manager manager = sess.get(Manager.class, id);
		
		sess.close();
		
		return manager;
	}

	@Override
	public Manager updateManager(Manager manager) {
		Session sess = sf.openSession();
		sess.beginTransaction();		
		
		sess.update(manager);
		
		sess.getTransaction().commit();
		sess.close();
		
		return manager;
	}

	@Override
	public boolean deleteManager(Manager manager) {
		Session sess = sf.openSession();
		sess.beginTransaction();		
		
		sess.delete(manager);
		
		sess.getTransaction().commit();
		sess.close();
		
		return true;
	}

	@Override
	public List<Manager> getAllManagers() {
		Session sess = sf.openSession();
		Criteria crit = sess.createCriteria(Manager.class);
		List<Manager> managers = crit.list();
		
		sess.close();
		
		return managers;
	}

	@Override
	public Manager getManagerByLogin(String user, String pass) {
		Session sess = sf.openSession();	
		Criteria crit = sess.createCriteria(Manager.class);
		crit.add(Restrictions.like("manUsername",user));
		crit.add(Restrictions.like("manPassword",pass));
		List<Manager> managers = crit.list();
		
		sess.close();
		
		return managers.get(0);
	}
}
