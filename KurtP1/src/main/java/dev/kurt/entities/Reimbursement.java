package dev.kurt.entities;

import javax.persistence.*;

@Entity
@Table(name = "reimbursement")
public class Reimbursement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rei_id")
	private int reimbursementId;
	
	@Column(name = "rei_name")
	private String reimbursementName;
	
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
	
	
	


	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private Employee employee;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "man_id")
	private Manager manager;
	
	
	
	public Reimbursement() {
		super();
	}
	
	
	public Reimbursement(int reimbursementId, String reimbursementName, double amount, String submitDate, Employee employee) {
		super();
		this.reimbursementId = reimbursementId;
		this.reimbursementName = reimbursementName;
		this.amount = amount;
		this.submitDate = submitDate;
		this.employee = employee;
	}

	
	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public String getReimbursementName() {
		return reimbursementName;
	}

	public void setReimbursementName(String reimbursementName) {
		this.reimbursementName = reimbursementName;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Manager getManager() {
		return manager;
	}


	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", reimbursementName=" + reimbursementName
				+ ", amount=" + amount + ", status=" + status + ", notes=" + notes + ", submitDate=" + submitDate
				+ ", statusDate=" + statusDate + "]";
	}
	
	
	
	
	
	
	
}
