package dev.alsabea.controllers.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dev.alsabea.entities.Employee;
import dev.alsabea.entities.Manager;
import dev.alsabea.entities.ReimbursementRequest;

public class Cleaner {

	
	
	public static Employee cleanInstance(Employee e) {
		
		e.getMgr().setEmps(null);
		e.getMgr().setUsername(null);
		e.getMgr().setPassword(null);
		
		
		e.setReqs(cleanReqs(e.getReqs()));
		return e;
			
		
	}
	
	public static Manager cleanInstance(Manager m) {
		
		Set<Employee> emps= new HashSet<>();
		for (Employee e: m.getEmps()) {
			e.setUsername(null);
			e.setPassword(null);
			e.setMgr(null);
			e.setReqs(cleanReqs(e.getReqs()));
			emps.add(e);
		}
		m.setEmps(emps);
		return m;
		
	}
	
	public  static  Set<ReimbursementRequest> cleanReqs(Set<ReimbursementRequest> reqs){
		for (ReimbursementRequest req: reqs) {
			req.setEmp(null);
			req.setMgr(null);
		}
		return reqs;
	}
}
