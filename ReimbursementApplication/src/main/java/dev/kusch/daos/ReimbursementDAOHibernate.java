package dev.kusch.daos;

import java.util.List;
import java.util.Set;

import javax.persistence.OptimisticLockException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import dev.kusch.entities.Employee;
import dev.kusch.entities.Reimbursement;
import dev.kusch.utils.HibernateUtil;

public class ReimbursementDAOHibernate implements ReimbursementDAO {
	
	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	private static ReimbursementDAOHibernate rdao = null;
	
	private ReimbursementDAOHibernate() {
		
	}
	
	public static ReimbursementDAOHibernate getReimbursementDAOHibernate() {
		if (rdao == null) {
			rdao = new ReimbursementDAOHibernate();
		}
		return rdao;
	}

	@Override
	public Reimbursement createReimbursement(Reimbursement reimbursement) {
		try(Session sess = sf.openSession()) {
			sess.beginTransaction();
			sess.save(reimbursement);
			sess.getTransaction().commit();
			return reimbursement;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		try (Session sess = sf.openSession()) {
			sess.beginTransaction();
			Criteria crit = sess.createCriteria(Reimbursement.class);
			List<Reimbursement> reimbursement = crit.list();
			sess.getTransaction().commit();
			return reimbursement;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		try (Session sess = sf.openSession()) {
			sess.beginTransaction();
			Criteria crit = sess.createCriteria(Reimbursement.class);
			crit.add(Restrictions.like("rid", id));
			List<Reimbursement> reimbursements = crit.list();
			sess.getTransaction().commit();
			if (reimbursements.size() != 0) {
				return reimbursements.get(0);
			} else {
				return null;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Reimbursement updateReimbursement(Reimbursement reimbursement) {
		try(Session sess = sf.openSession()) {
			sess.beginTransaction();
			sess.saveOrUpdate(reimbursement);
			sess.getTransaction().commit();
			sess.close();
			return reimbursement;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} catch (OptimisticLockException f) {
			f.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteReimbursement(Reimbursement reimbursement) {
		try(Session sess = sf.openSession()) {
			sess.beginTransaction();
			sess.delete(reimbursement);
			sess.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} catch (OptimisticLockException f) {
			f.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Reimbursement> getReimbursementsByEmployee(Employee employee) {
		//return employee.getReimbursements();
		
		try (Session sess = sf.openSession()) {
			sess.beginTransaction();
			Criteria crit = sess.createCriteria(Reimbursement.class);
			crit.add(Restrictions.like("employee", employee));
			List<Reimbursement> reimbursement = crit.list();
			sess.getTransaction().commit();
			return reimbursement;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
