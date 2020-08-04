package dev.alsabea.services.impl;

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
	public long createInstance(ReimbursementRequest t) {
		return rrd.createInstance(t);
	}

	@Override
	public ReimbursementRequest retrieveById(long key) {
		return rrd.retrieveById(key);
	}

	@Override
	public boolean update(ReimbursementRequest t) {
		return rrd.update(t);
	}

	@Override
	public boolean deleteById(long key) {
		return rrd.deleteById(key);
	}



}
