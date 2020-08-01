package dev.alsabea.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "reimbursement_request")
public class ReimbursementRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rr_id")
	private long 	rrId;  //reimbursement request id, this is the primary key of the table;
	
	@ManyToOne
	@JoinColumn(name = "emp_id", nullable = false) 
	private Employee emp;
	
	@ManyToOne
	@JoinColumn(name = "mgr_id", nullable = false) 
	private Manager mgr;
	
	@Column(name = "reimbursement_request", nullable = false)
	private String		reimbursementRequest;
	
	@Column(name = "reimbursement_status", nullable = false)
	private String		reimbursementStatus;
	
	@Column (name = "reason")
	private String		reason;

	public ReimbursementRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReimbursementRequest(long id) {
		super();
		this.rrId=id;
	}

	public long getRrId() {
		return rrId;
	}

	public void setRrId(long rrId) {
		this.rrId = rrId;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Manager getMgr() {
		return mgr;
	}

	public void setMgr(Manager mgr) {
		this.mgr = mgr;
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

	@Override
	public String toString() {
		return "ReimbursementRequest [rrId=" + rrId + ", emp=" + emp + ", mgr=" + mgr + ", reimbursementRequest="
				+ reimbursementRequest + ", reimbursementStatus=" + reimbursementStatus + ", reason=" + reason + "]";
	}
	

	
}
