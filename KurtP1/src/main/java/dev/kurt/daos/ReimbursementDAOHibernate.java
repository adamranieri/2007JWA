package dev.kurt.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;
import dev.kurt.entities.Reimbursement;
import dev.kurt.entities.Reimbursement;
import dev.kurt.utils.HibernateUtil;

public class ReimbursementDAOHibernate implements ReimbursementDAO {

	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	
	
	
	@Override
	public Reimbursement createReimbursement(Reimbursement reimbursement) {
		Session sess = sf.openSession();
		sess.beginTransaction();		
		
		sess.save(reimbursement);
		
		sess.getTransaction().commit();
		sess.close();
		
		return reimbursement;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		Session sess = sf.openSession();	
		Reimbursement reimbursement = sess.get(Reimbursement.class, id);
		
		sess.close();
		
		return reimbursement;
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		Session sess = sf.openSession();
		Criteria crit = sess.createCriteria(Reimbursement.class);
		List<Reimbursement> reimbursements = crit.list();
		
		sess.close();
		
		return reimbursements;
	}

	@Override
	public List<Reimbursement> getEmployeeReimbursements(Employee employee) {
		Session sess = sf.openSession();	
		Criteria crit = sess.createCriteria(Reimbursement.class);
		crit.add(Restrictions.like("employee",employee));
		List<Reimbursement> reimbursements = crit.list();
		
		sess.close();
		
		return reimbursements;
	}

	@Override
	public Reimbursement updateReimbursement(Reimbursement reimbursement) {
		Session sess = sf.openSession();
		sess.beginTransaction();		
		
		sess.update(reimbursement);
		
		sess.getTransaction().commit();
		sess.close();
		
		return reimbursement;
	}

	@Override
	public boolean deleteReimbursement(Reimbursement reimbursement) {
		Session sess = sf.openSession();
		sess.beginTransaction();		
		
		sess.delete(reimbursement);
		
		sess.getTransaction().commit();
		sess.close();
		
		return true;
	}

}
