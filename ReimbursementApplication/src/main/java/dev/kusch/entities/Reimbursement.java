package dev.kusch.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "reimbursement")
public class Reimbursement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "r_id")
	private int rid;
	
	@Column(name="status")
	private String status;
	
	@Column(name="message")
	private String message;
	
	@Column(name="accepted")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="accept_message")
	private String acceptMessage;
	
	@ManyToOne
	@JoinColumn(name="e_id")
	private Employee employee;

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int rid, String status, String message, Date date, double amount, String acceptMessage,
			Employee employee) {
		super();
		this.rid = rid;
		this.status = status;
		this.message = message;
		this.date = date;
		this.amount = amount;
		this.acceptMessage = acceptMessage;
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Reimbursement [rid=" + rid + ", status=" + status + ", message=" + message + ", date=" + date
				+ ", amount=" + amount + ", accept_message=" + acceptMessage + ", employee=" + employee + "]";
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAcceptMessage() {
		return acceptMessage;
	}

	public void setAcceptMessage(String acceptMessage) {
		this.acceptMessage = acceptMessage;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
