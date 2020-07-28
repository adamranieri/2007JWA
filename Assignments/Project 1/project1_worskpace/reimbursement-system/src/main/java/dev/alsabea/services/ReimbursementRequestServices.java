package dev.alsabea.services;

import java.util.List;

import dev.alsabea.entities.ReimbursementRequest;

public interface ReimbursementRequestServices {

	
	int createInstance(ReimbursementRequest t);
	
	ReimbursementRequest retrieveById(int key);
	
	boolean update (ReimbursementRequest t);
	
	boolean deleteById (int key);
	
	List<ReimbursementRequest> retrieveAllRequestsByEmpId(int key);
	
	List<ReimbursementRequest> retrieveAllRequestsByMgrId(int key);
}
