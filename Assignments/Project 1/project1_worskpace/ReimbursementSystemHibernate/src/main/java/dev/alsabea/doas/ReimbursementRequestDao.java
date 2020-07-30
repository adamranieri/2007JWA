package dev.alsabea.doas;

import java.util.List;

import dev.alsabea.entities.ReimbursementRequest;

public interface ReimbursementRequestDao extends CRUD<ReimbursementRequest> {
	
	
	List<ReimbursementRequest> retrieveAllRequestsByEmpId(long key);
	
	List<ReimbursementRequest> retrieveAllRequestsByMgrId(long key);

}
