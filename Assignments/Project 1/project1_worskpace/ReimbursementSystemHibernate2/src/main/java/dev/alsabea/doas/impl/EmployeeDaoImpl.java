package dev.alsabea.doas.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import dev.alsabea.connection.HibernateConnectionEstablisher;
import dev.alsabea.doas.EmployeeDao;
import dev.alsabea.entities.Employee;
import dev.alsabea.entities.Manager;
import dev.alsabea.exceptions.DaoException;

public class EmployeeDaoImpl implements EmployeeDao {

	private static SessionFactory sf = HibernateConnectionEstablisher.getSessionFactory();

	private static EmployeeDaoImpl eDao;

	private EmployeeDaoImpl() {
		super();
	}

	public static EmployeeDaoImpl getInstance() {
		if (eDao == null)
			eDao = new EmployeeDaoImpl();
		return eDao;
	}

	@Override
	public long createInstance(Employee t) {

		long generatedId = 0;
		try (Session s = sf.openSession()) {

			s.beginTransaction();

			generatedId = ((Long) s.save(t)).longValue();

			s.getTransaction().commit();
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			// e.printStackTrace();
			return -1;
		}

		return generatedId;
	}

	@Override
	public Employee retrieveById(long key) {

		return sf.openSession().get(Employee.class, key);
	}

//	@Override
//	public Employee retrieveByUsernameAndPassword(String username, String password) {
//		
//		Employee e= null;
//		try (Session sess = sf.openSession()) {
//
//			@SuppressWarnings("deprecation")
//			Criteria crit  = sess.createCriteria(Employee.class);
//			crit.add(Restrictions.like("username", username));
//			crit.add(Restrictions.like("password", password));
//			e=(Employee) crit.uniqueResult();
//			e.getReqs().size();
//			return e;
//		} catch(HibernateException ex) {
//			ex.printStackTrace();
//		}
//
//		return null;
//	}

	/*
	 * Query query = session.createQuery("update Stock set stockName = :stockName" +
	 * " where stockCode = :stockCode");
	 */

	// (emp_id , first_name , last_name , username , password, mgr_id )

	/*
	 * used the long version and typed an hql because I was getting the optimistic
	 * lock exception and the stale object exception
	 * 
	 */
	@Override
	public boolean update(Employee t) {

		try (Session s = sf.openSession()) {
			s.beginTransaction();
			s.update(t);
			s.getTransaction().commit();
			return true;
		} catch (javax.persistence.OptimisticLockException e) {
			return false;
		}

	}

	@Override
	public Employee retrieveByUsernameAndPassword(String username, String password) {
		try (Session s = sf.openSession()) {
			s.beginTransaction();

			TypedQuery<Employee> q = s
					.createQuery(
							"SELECT e from Employee e JOIN FETCH e.reqs "
									+ " WHERE e.username = :user AND e.password = :pass",
							Employee.class)
					.setParameter("user", username).setParameter("pass", password);

			s.getTransaction().commit();
			Employee e = q.getSingleResult();
			return e;
		} catch(NoResultException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteById(long key) {
		int result = 0;
		try (Session s = sf.openSession()) {
			s.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query q = s.createQuery("DELETE FROM Employee WHERE empId = :eid").setParameter("eid", key);

			result = q.executeUpdate();
			if (result > 1)
				throw new DaoException("more than one record got deleted");
			s.getTransaction().commit();
		} catch (DaoException | javax.persistence.PersistenceException e) {
			e.printStackTrace();
		}

		if (result == 1)
			return true;
		else
			return false;

	}

}
