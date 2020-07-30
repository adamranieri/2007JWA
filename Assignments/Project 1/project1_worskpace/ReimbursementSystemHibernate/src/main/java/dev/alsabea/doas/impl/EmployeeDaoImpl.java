package dev.alsabea.doas.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dev.alsabea.connection.HibernateConnectionEstablisher;
import dev.alsabea.doas.EmployeeDao;
import dev.alsabea.entities.Employee;
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
		}

		return generatedId;
	}

	@Override
	public Employee retrieveById(long key) {

		return sf.openSession().get(Employee.class, key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> retrieveAll() {

		try (Session sess = sf.openSession()) {

			@SuppressWarnings("deprecation")
			Criteria crit = sess.createCriteria(Employee.class);
			return crit.list();

		}

	}

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
		int result = 0;
		try (Session s = sf.openSession()) {
			s.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query q = s
					.createQuery("UPDATE Employee " + "SET firstName = :fn, lastName = :ln, password= :p "
							+ " , mgr= :m WHERE empId= :eid")
					.setParameter("fn", t.getFirstName()).setParameter("ln", t.getLastName())
					.setParameter("p", t.getPassword()).setParameter("m", t.getMgr()).setParameter("eid", t.getEmpId());

			result = q.executeUpdate();
			if (result > 1)
				throw new DaoException("more than one record got updated");
			s.getTransaction().commit();
		} catch (DaoException | javax.persistence.PersistenceException e) {
			e.printStackTrace();
		}

		if (result == 1)
			return true;
		else
			return false;

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
