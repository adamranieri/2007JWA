package dev.nauman.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dev.nauman.daos.EmployeeDAO;
import dev.nauman.daos.EmployeeDAOImpl;
import dev.nauman.daos.ReimbursementDAO;
import dev.nauman.daos.ReimbursementDAOImpl;
import dev.nauman.entities.Employee;
import dev.nauman.entities.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService{

	private ReimbursementDAO rdao = ReimbursementDAOImpl.getReimbursementDAOImpl();
	private static ReimbursementServiceImpl rserv = null;
	private ReimbursementServiceImpl() {
	}
	public static ReimbursementServiceImpl getEmployeeServiceImpl() {
		if(rserv ==null) {
			rserv = new ReimbursementServiceImpl();
		}
		return rserv;
	}
	
	@Override
	public Reimbursement submitReimbursement(Reimbursement reimbursement) {
		return rdao.createReimbursement(reimbursement);
	}

	@Override
	public Reimbursement getReimbursementById(int rId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		return rdao.getAllReimbursements();
	}

	@Override
	public Reimbursement updateReimbursement(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getAllAcendingByItemReimbursements(boolean ascending) {
		List<Reimbursement> reimbursements = this.getAllReimbursements();
		Comparator<Reimbursement> comparator = (reimbursement1, reimbursement2)-> {
			char[] letterR1 = reimbursement1.getItem().toLowerCase().toCharArray();
			char[] letterR2 = reimbursement2.getItem().toLowerCase().toCharArray();
			if(letterR1[0]<letterR2[0]) {
				return -1;
			}
			if (letterR1[0]>letterR2[0]){
				return 1;
			}
			return 0;
		};
		Collections.sort(reimbursements,comparator);
		
		if(ascending == true) {
			return reimbursements;
		}else {
			 Collections.reverse(reimbursements);
			 System.out.println(reimbursements);
			 return reimbursements;
		}
	}
	@Override
	public List<Reimbursement> getAllAcendingByStatusReimbursements(boolean ascending) {
		List<Reimbursement> reimbursements = this.getAllReimbursements();
		Comparator<Reimbursement> comparator = (reimbursement1, reimbursement2)-> {
			
			if((reimbursement1.getStatus()=="approved" && (reimbursement2.getStatus() == "submitted" || reimbursement2.getStatus() == "denied"))
					|| (reimbursement1.getStatus() == "submitted" && reimbursement2.getStatus() == "denied")){
				return 1;
			}
			if ((reimbursement2.getStatus()=="approved" && (reimbursement1.getStatus() == "submitted" || reimbursement1.getStatus() == "denied"))
					|| (reimbursement2.getStatus() =="submitted" && reimbursement1.getStatus() == "denied")){
				return -1;
			}
			return 0;
		};
		Collections.sort(reimbursements,comparator);
		
		if(ascending == true) {
			return reimbursements;
		}else {
			 Collections.reverse(reimbursements);
			 System.out.println(reimbursements);
			 return reimbursements;
		}
	}
	@Override
	public List<Reimbursement> getAllAcendingByCategoryReimbursements(boolean ascending) {
		List<Reimbursement> reimbursements = this.getAllReimbursements();
		Comparator<Reimbursement> comparator = (reimbursement1, reimbursement2)-> {
			char[] letterR1 = reimbursement1.getCategory().toLowerCase().toCharArray();
			char[] letterR2 = reimbursement2.getCategory().toLowerCase().toCharArray();
			if(letterR1[0]<letterR2[0]) {
				return -1;
			}
			if (letterR1[0]>letterR2[0]){
				return 1;
			}
			return 0;
		};
		Collections.sort(reimbursements,comparator);
		
		if(ascending == true) {
			return reimbursements;
		}else {
			 Collections.reverse(reimbursements);
			 System.out.println(reimbursements);
			 return reimbursements;
		}
	}
	@Override
	public List<Reimbursement> getAllAcendingByAmountReimbursements(boolean ascending) {
		List<Reimbursement> reimbursements = this.getAllReimbursements();
		Comparator<Reimbursement> comparator = (reimbursement1, reimbursement2)-> {
			if(reimbursement1.getAmount()<reimbursement2.getAmount()) {
				return -1;
			}
			if (reimbursement1.getAmount()>reimbursement2.getAmount()){
				return 1;
			}
			return 0;
		};
		Collections.sort(reimbursements,comparator);
		
		if(ascending == true) {
			return reimbursements;
		}else {
			 Collections.reverse(reimbursements);
			 System.out.println(reimbursements);
			 return reimbursements;
		}
	}
	@Override
	public List<Reimbursement> getAllAcendingByEmployeeReimbursements(boolean ascending) {
		List<Reimbursement> reimbursements = this.getAllReimbursements();
		Comparator<Reimbursement> comparator = (reimbursement1, reimbursement2)-> {
			char[] letterR1 = reimbursement1.getemployee().getFirstName().toLowerCase().toCharArray();
			char[] letterR2 = reimbursement2.getemployee().getFirstName().toLowerCase().toCharArray();
			if(letterR1[0]<letterR2[0]) {
				return 1;
			}
			if (letterR1[0]>letterR2[0]){
				return -1;
			}
			return 0;
		};
		Collections.sort(reimbursements,comparator);
		
		if(ascending == true) {
			return reimbursements;
		}else {
			 Collections.reverse(reimbursements);
			 System.out.println(reimbursements);
			 return reimbursements;
		}
	}
	@Override
	public List<Reimbursement> getAllAcendingByNoteReimbursements(boolean ascending) {
		List<Reimbursement> reimbursements = this.getAllReimbursements();
		Comparator<Reimbursement> comparator = (reimbursement1, reimbursement2)-> {
			char[] letterR1 = reimbursement1.getNote().toLowerCase().toCharArray();
			char[] letterR2 = reimbursement2.getNote().toLowerCase().toCharArray();
			if(letterR1[0]<letterR2[0]) {
				return -1;
			}
			if (letterR1[0]>letterR2[0]){
				return 1;
			}
			return 0;
		};
		Collections.sort(reimbursements,comparator);
		
		if(ascending == true) {
			return reimbursements;
		}else {
			 Collections.reverse(reimbursements);
			 System.out.println(reimbursements);
			 return reimbursements;
		}
	}
	@Override
	public List<Reimbursement> getAllAcendingByDateReimbursements(boolean ascending) {
		List<Reimbursement> reimbursements = this.getAllReimbursements();
		Comparator<Reimbursement> comparator = (reimbursement1, reimbursement2)-> {
			Timestamp r1 = reimbursement1.getDateSubmitted();
			Timestamp r2 = reimbursement2.getDateSubmitted();
			return r1.compareTo(r2);
		};
		Collections.sort(reimbursements,comparator);
		
		if(ascending == true) {
			return reimbursements;
		}else {
			 Collections.reverse(reimbursements);
			 System.out.println(reimbursements);
			 return reimbursements;
		}
	}
	@Override
	public List<Reimbursement> getAllEmployeeReimbursements(int eId) {
		List<Reimbursement> reimbursements = this.getAllReimbursements();
		List<Reimbursement> employeeReimbursements = new ArrayList<Reimbursement>();
		for(Reimbursement reimbursement : reimbursements) {
			if(reimbursement.getemployee().getid() == eId) {
				employeeReimbursements.add(reimbursement);
			}
		}
		
		return employeeReimbursements;
	}
	@Override
	public List<Reimbursement> getAllEmployeeReimbursements(Employee employee) {
		List<Reimbursement> reimbursements = this.getAllReimbursements();
		List<Reimbursement> employeeReimbursements = new ArrayList<Reimbursement>();
		for(Reimbursement reimbursement : reimbursements) {
			if(reimbursement.getemployee() == employee) {
				employeeReimbursements.add(reimbursement);
			}
		}
		
		return employeeReimbursements;
	}

	
}
