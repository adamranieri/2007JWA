package dev.alsabea.services.impl;

import java.util.List;

import dev.alsabea.doas.ReimbursementRequestDao;
import dev.alsabea.doas.impl.ReimbursementRequestDaoImpl;
import dev.alsabea.entities.ReimbursementRequest;
import dev.alsabea.services.ReimbursementRequestServices;

public class ReimbursementRequestServicesImpl  implements ReimbursementRequestServices{
	
	private static ReimbursementRequestServicesImpl rrs;
	
	private static ReimbursementRequestDao rrd = ReimbursementRequestDaoImpl.getInstance();
	
	
	public static ReimbursementRequestServicesImpl getInstance() {
		if (rrs==null)
			rrs= new ReimbursementRequestServicesImpl();
		return rrs;
	}
	

	@Override
	public int createInstance(ReimbursementRequest t) {
		return rrd.createInstance(t);
	}

	@Override
	public ReimbursementRequest retrieveById(int key) {
		return rrd.retrieveById(key);
	}

	@Override
	public boolean update(ReimbursementRequest t) {
		return rrd.update(t);
	}

	@Override
	public boolean deleteById(int key) {
		return rrd.deleteById(key);
	}

	@Override
	public List<ReimbursementRequest> retrieveAllRequestsByEmpId(int key) {
		return rrd.retrieveAllRequestsByEmpId(key);
	}

	@Override
	public List<ReimbursementRequest> retrieveAllRequestsByMgrId(int key) {
		return rrd.retrieveAllRequestsByMgrId(key);
	}

}
