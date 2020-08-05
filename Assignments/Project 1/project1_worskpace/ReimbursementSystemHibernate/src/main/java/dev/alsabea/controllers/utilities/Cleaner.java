package dev.alsabea.controllers.utilities;

import java.util.ArrayList;
import java.util.List;

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
		
		List<Employee> emps= new ArrayList<>();
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
	
	public  static  List<ReimbursementRequest> cleanReqs(List<ReimbursementRequest> reqs){
		for (ReimbursementRequest req: reqs) {
			req.setEmp(null);
			req.setMgr(null);
		}
		return reqs;
	}
}
