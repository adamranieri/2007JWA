package dev.nauman.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursement")
public class Reimbursement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "r_id")
	private int rId;
	@ManyToOne
	@JoinColumn(name = "e_id")
	private Employee employee;
	@Column(name = "status")
	private String status = "submitted";
	@Column(name = "category")
	private String category = "Miscellaneous";
	@Column(name = "date_submitted")
	private Timestamp dateSubmitted = new Timestamp(System.currentTimeMillis());
	@Column(name = "item")
	private String item;
	@Column(name = "note")
	private String note = "none";
	@Column(name = "amount")
	private double amount;



	public Reimbursement(int rId, Employee employee, String item, double amount) {
		this.rId = rId;
		this.employee = employee;
		this.item = item;
		this.amount = amount;
	}
	
	public Reimbursement(int rId, Employee employee, String category, String item, String note, double amount) {
		this.rId = rId;
		this.employee = employee;
		this.category = category;
		this.item = item;
		this.note = note;
		this.amount = amount;
	}

	public Reimbursement(int rId, Employee employee, String item, String note, double amount) {
		this.rId = rId;
		this.employee = employee;
		this.item = item;
		this.note = note;
		this.amount = amount;
	}

	public Reimbursement(int rId, Employee employee, String status, String category, Timestamp dateSubmitted, String item,
		String note, double amount) {
		this.rId = rId;
		this.employee = employee;
		this.status = status;
		this.category = category;
		this.dateSubmitted = dateSubmitted;
		this.item = item;
		this.note = note;
		this.amount = amount;
	}
	public Reimbursement() {
		super();
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
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
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public Employee getemployee() {
		return employee;
	}
	public void setemployee(Employee employee) {
		this.employee = employee;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Timestamp getDateSubmitted() {
		return dateSubmitted;
	}
	public void setDateSubmitted(Timestamp dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Reimbursement [rId=" + rId + ", employee=" + employee.getFirstName() +" " + employee.getLastName() + ", status=" + status + ", category=" + category
				+ ", dateSubmitted=" + dateSubmitted + ", item=" + item + ", note=" + note + ", amount=" + amount + "]";
	}





}
