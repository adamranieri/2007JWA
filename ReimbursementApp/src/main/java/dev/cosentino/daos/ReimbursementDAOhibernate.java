package dev.cosentino.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import dev.cosentino.entities.Employee;
import dev.cosentino.entities.Reimbursement;
import dev.cosentino.utils.HibernateUtil;

public class ReimbursementDAOhibernate implements ReimbursementDAO{

	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	private static EmployeeDAO edao = null;
	private static ReimbursementDAO rdao = null;
	
	public ReimbursementDAOhibernate() {
		super();
	}
	
	public static ReimbursementDAO getReimbursementDAOhibernate() {
		if(rdao == null) {
			rdao = new ReimbursementDAOhibernate();
			edao = new EmployeeDAOhibernate();
		}
		return rdao;
	}
	
	
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
	public List<Reimbursement> getReimbursementsByEmployeeId(int id) {
		try(Session sess = sf.openSession()){
			Criteria crit = sess.createCriteria(Reimbursement.class);
			Employee emp = edao.getEmployeeById(id);
			crit.add(Restrictions.in("employee", emp));
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			List<Reimbursement> reimbursements = crit.list();
			return reimbursements;
		}
	}
 
	@Override
	public List<Reimbursement> getReimbursementByUsername(String username) {
		Employee employee = edao.getEmployeeByUsername(username); 
		
		List<Reimbursement> reimbursements = rdao.getReimbursementsByEmployeeId(employee.geteId());
		return reimbursements;
	}

	@Override
	public List<Reimbursement> getReimbursementByStatus(String status) {
		try(Session sess = sf.openSession()){
			Criteria crit = sess.createCriteria(Reimbursement.class);
			crit.add(Restrictions.like("status", status));
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<Reimbursement> reimbursements = crit.list();
			
			return reimbursements;
		}
	}
	
	@Override
	public List<Reimbursement> getAllReimbursements(){
		try(Session sess = sf.openSession()){
			Criteria crit = sess.createCriteria(Reimbursement.class);
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			List<Reimbursement> reimbursements = crit.list();
			return reimbursements;
		}
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

}
