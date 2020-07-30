package dev.alsabea.services;

import dev.alsabea.entities.ReimbursementRequest;

public interface ReimbursementRequestServices {

	
	long createInstance(ReimbursementRequest t);
	
	ReimbursementRequest retrieveById(long key);
	
	boolean update (ReimbursementRequest t);
	
	boolean deleteById (long key);
	
}
