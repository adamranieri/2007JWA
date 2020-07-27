package dev.alsabea.doas;

import java.util.List;

import dev.alsabea.entities.ReimbursementRequest;

public interface ReimbursementRequestDao extends CRUD<ReimbursementRequest> {
	
	
	List<ReimbursementRequest> retrieveAllRequestsByEmpId(int key);
	
	List<ReimbursementRequest> retrieveAllRequestsByMgrId(int key);

}
