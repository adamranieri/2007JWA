package dev.kurt.entities;

import javax.persistence.*;

@Entity
@Table(name = "reimbursement")
public class Reimbursements {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "r_id")
	private int reimbursementId;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "notes")
	private String notes;
	
	@Column(name = "submit_date")
	private String submitDate;
	
	@Column(name = "status_date")
	private String statusDate;
	
	@Column(name = "e_id")
	private int employeeId;
	
	public Reimbursements() {
		super();
	}
	
	public Reimbursements(int reimbursementId, double amount, String status, String notes, String submitDate,
			String statusDate, int employeeId) {
		super();
		this.reimbursementId = reimbursementId;
		this.amount = amount;
		this.status = status;
		this.notes = notes;
		this.submitDate = submitDate;
		this.statusDate = statusDate;
		this.employeeId = employeeId;
	}
	
	public int getReimbursementId() {
		return reimbursementId;
	}
	
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public String getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	@Override
	public String toString() {
		return "Reimbursements [reimbursementId=" + reimbursementId + ", amount=" + amount + ", status=" + status
				+ ", notes=" + notes + ", submitDate=" + submitDate + ", statusDate=" + statusDate + ", employeeId="
				+ employeeId + "]";
	}
	
	
	
	
	
	
}
