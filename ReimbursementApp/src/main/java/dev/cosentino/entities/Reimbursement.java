package dev.cosentino.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "reimbursement")
public class Reimbursement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "r_id")
	private int rId;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "manager_note")
	private String manager_note;
	
	@Column(name = "amount")
	private float amount;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "submitted_date")
	private String submitted_date;
	
	@ManyToOne
	@JoinColumn(name = "e_id")
	private Employee employee;
	
	public Reimbursement(){
		super();
	}

	public Reimbursement(int rId, String status, String manager_note, float amount, String description,
			String submitted_date, Employee employee) {
		super();
		this.rId = rId;
		this.status = status;
		this.manager_note = manager_note;
		this.amount = amount;
		this.description = description;
		this.submitted_date = submitted_date;
		this.employee = employee;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return manager_note;
	}

	public void setNote(String note) {
		this.manager_note = note;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

	public String getManager_note() {
		return manager_note;
	}



	public void setManager_note(String manager_note) {
		this.manager_note = manager_note;
	}



	public float getAmount() {
		return amount;
	}



	public void setAmount(float amount) {
		this.amount = amount;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubmitted_date() {
		return submitted_date;
	}

	public void setSubmitted_date(String submitted_date) {
		this.submitted_date = submitted_date;
	}

	@Override
	public String toString() {
		return "Reimbursement [rId=" + rId + ", status=" + status + ", manager_note=" + manager_note + ", amount="
				+ amount + ", description=" + description + ", submitted_date=" + submitted_date + ", employee="
				+ employee + "]";
	}
	
}
