package dev.alsabea.doas.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dev.alsabea.connection.HibernateConnectionEstablisher;
import dev.alsabea.doas.ReimbursementRequestDao;
import dev.alsabea.entities.ReimbursementRequest;

public class ReimbursementReqDaoHibImpl implements ReimbursementRequestDao {

	private static SessionFactory sf = HibernateConnectionEstablisher.getSessionFactory();

	private static ReimbursementReqDaoHibImpl rrDao;

	private ReimbursementReqDaoHibImpl() {
		super();
	}

	public static ReimbursementRequestDao getInstance() {
		if (rrDao == null)
			rrDao = new ReimbursementReqDaoHibImpl();
		return rrDao;
	}

	@Override
	public long createInstance(ReimbursementRequest t) {
		long generatedId = 0;
		try (Session s = sf.openSession()) {

			s.beginTransaction();

			generatedId = ((Long) s.save(t)).longValue();

			s.getTransaction().commit();
		}
		// no exceptions because checks should be made in the front end,
		// restrictions regarding primary and foreign keys must be done in the front
		// end.

		return generatedId;
	}

	@Override
	public ReimbursementRequest retrieveById(long key) {

		return sf.openSession().get(ReimbursementRequest.class, key);
	}

	
	@Override
	public boolean update(ReimbursementRequest t) {

		try (Session s = sf.openSession()) {
			s.beginTransaction();
			s.update(t);
			s.getTransaction().commit();
			return true;
		} catch (javax.persistence.OptimisticLockException e) {
			// e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteById(long key) {
		try (Session s = sf.openSession()) {
			s.beginTransaction();
			s.delete(new ReimbursementRequest(key));
			s.getTransaction().commit();
			return true;
		} catch (javax.persistence.OptimisticLockException e) {
			// e.printStackTrace();
			return false;
		}
	}

}
