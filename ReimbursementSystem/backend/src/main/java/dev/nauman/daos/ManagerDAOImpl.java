package dev.nauman.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dev.nauman.entities.Manager;
import dev.nauman.utils.HibernateUtil;

public class ManagerDAOImpl implements ManagerDAO {

	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	private ManagerDAOImpl() {
	}
	private static ManagerDAOImpl mdao = null;
	public static ManagerDAOImpl getManagerDAOImpl() {
		if(mdao == null) {
			return new ManagerDAOImpl();
		}
		return mdao;
	}
	
	@Override
	public Manager getManagerById(int mId) {
		try(Session session = sf.openSession()) {
			
			return session.get(Manager.class, mId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Manager> getAllManagers() {
			try(Session session = sf.openSession()) {
			Criteria criteria = session.createCriteria(Manager.class);
				
			return criteria.list();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Manager updateManager(Manager manager) {
		if(this.getManagerById(manager.getmId()) == null) {
			return null;
		}
		try(Session session = sf.openSession()) {
			session.beginTransaction();
			
			session.update(manager);
			return manager;
		} catch (Exception e) {
			return null;
		}
	}

}
