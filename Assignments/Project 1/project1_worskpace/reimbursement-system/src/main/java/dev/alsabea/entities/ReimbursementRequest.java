package dev.alsabea.entities;

public class ReimbursementRequest {


	private int 		rrId;  //reimbursement request id, this is the primary key of the table;
	private int 		empId;
	private int 		mgrId;
	private String		reimbursementRequest;
	private String		reimbursementStatus;
	private String		reason;
	
	
	
	public int getRrId() {
		return rrId;
	}
	public void setRrId(int rrId) {
		this.rrId = rrId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getMgrId() {
		return mgrId;
	}
	public void setMgrId(int mgrId) {
		this.mgrId = mgrId;
	}
	public String getReimbursementRequest() {
		return reimbursementRequest;
	}
	public void setReimbursementRequest(String reimbursementRequest) {
		this.reimbursementRequest = reimbursementRequest;
	}
	public String getReimbursementStatus() {
		return reimbursementStatus;
	}
	public void setReimbursementStatus(String reimbursementStatus) {
		this.reimbursementStatus = reimbursementStatus;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

}
