package dev.nauman.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dev.nauman.entities.Reimbursement;
import dev.nauman.utils.HibernateUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO {

	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	private ReimbursementDAOImpl() {
	}
	private static ReimbursementDAOImpl rdao = null;
	public static ReimbursementDAOImpl getReimbursementDAOImpl() {
		if(rdao == null) {
			return new ReimbursementDAOImpl();
		}
		return rdao;
	}
	
	@Override
	public Reimbursement createReimbursement(Reimbursement reimbursement) {
		try(Session session = sf.openSession()) {
			session.beginTransaction();
			
			session.save(reimbursement);
			
			session.getTransaction().commit();
			
			return reimbursement;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Reimbursement getReimbursementById(int rId) {
		try(Session session = sf.openSession()) {
			
			return session.get(Reimbursement.class, rId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		try(Session session = sf.openSession()) {
			Criteria criteria = session.createCriteria(Reimbursement.class);
			
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Reimbursement updateReimbursement(Reimbursement reimbursement) {
		try(Session session = sf.openSession()) {
			session.beginTransaction();
			
			session.update(reimbursement);
			
			session.getTransaction().commit();
			
			return reimbursement;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
