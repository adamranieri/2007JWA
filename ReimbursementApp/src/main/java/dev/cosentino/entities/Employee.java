package dev.cosentino.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	
	@Id // this is the primary key of the entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) // we say that this column is auto incremented
	@Column(name = "e_id")
	private int eId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "username") 
	private String username;
	
	@Column(name = "password") 
	private String password;
	
	@Column(name = "title") 
	private String title;
	
	
	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL) 
	private transient List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
	

	public Employee() {
		super();
	}

	public Employee(int eId, String name, String username, String password, String title) {
		super();
		this.eId = eId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.title = title;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Reimbursement> getReimbursements() {
		return reimbursements;
	}

	public void setReimbursements(List<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}

	@Override
	public String toString() {
		return "Employee [eId=" + eId + ", name=" + name + ", username=" + username + ", title=" + title + "]";
	}
	
	
}
