package dev.kusch.daos;

import java.util.List;

import javax.persistence.OptimisticLockException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import dev.kusch.entities.Employee;
import dev.kusch.entities.Manager;
import dev.kusch.utils.HibernateUtil;

public class ManagerDAOHibernate implements ManagerDAO {
	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	private static ManagerDAOHibernate dao = null;
	
	private ManagerDAOHibernate() {
		super();
	}
	
	public static ManagerDAOHibernate getManagerDAOHibernate() {
		if (dao == null) {
			dao = new ManagerDAOHibernate();
			return dao;
		}
		
		return dao;
	}

	@Override
	public Manager getManagerById(int id) {
		try (Session sess = sf.openSession()) {
			sess.beginTransaction();
			Manager manager = sess.get(Manager.class, id);
			sess.getTransaction().commit();
			return manager;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Manager> getManagerByUser(String username) {
		try (Session sess = sf.openSession()) {
			sess.beginTransaction();
			Criteria crit = sess.createCriteria(Manager.class);
			crit.add(Restrictions.like("username", username));
			List<Manager> managers = crit.list();
			sess.getTransaction().commit();
			return managers;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Manager> getManagerByPass(String password) {
		try (Session sess = sf.openSession()) {
			sess.beginTransaction();
			Criteria crit = sess.createCriteria(Manager.class);
			crit.add(Restrictions.like("password", password));
			List<Manager> managers = crit.list();
			sess.getTransaction().commit();
			return managers;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Manager updateManager(Manager manager) {
		try (Session sess = sf.openSession()) {
			sess.beginTransaction();
			sess.update(manager);
			sess.getTransaction().commit();
			return manager;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} catch (OptimisticLockException f) {
			f.printStackTrace();
			return null;
		}
	}
}
