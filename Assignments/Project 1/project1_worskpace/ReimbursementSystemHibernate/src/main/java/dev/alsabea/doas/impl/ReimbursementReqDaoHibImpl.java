package dev.alsabea.doas.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import dev.alsabea.connection.HibernateConnectionEstablisher;
import dev.alsabea.doas.ReimbursementRequestDao;
import dev.alsabea.entities.ReimbursementRequest;

public class ReimbursementReqDaoHibImpl implements ReimbursementRequestDao{

	private static SessionFactory sf = HibernateConnectionEstablisher.getSessionFactory();

	private static ReimbursementReqDaoHibImpl rrDao;
	
	private ReimbursementReqDaoHibImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static ReimbursementRequestDao getInstance() {
		if (rrDao==null)
			rrDao = new ReimbursementReqDaoHibImpl();
		return rrDao;
	}
	
	@Override
	public long createInstance(ReimbursementRequest t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ReimbursementRequest retrieveById(long key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(ReimbursementRequest t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(long key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ReimbursementRequest> retrieveAllRequestsByEmpId(long key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementRequest> retrieveAllRequestsByMgrId(long key) {
		// TODO Auto-generated method stub
		return null;
	}


	
	

}
