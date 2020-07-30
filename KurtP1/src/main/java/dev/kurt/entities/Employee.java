package dev.kurt.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="employee")

public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private int employeeId;
	
	@Column(name = "emp_username")
	private String empUsername;                                                    
	
	@Column(name = "emp_password")
	private String empPassword;
	
	@Column(name = "emp_first_name")
	private String empfName;
	
	@Column(name = "emp_last_name")
	private String emplName;
	
	@ManyToOne
	@JoinColumn(name = "man_id")
	private Manager manager;
	
	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Reimbursement> reimbursements = new ArrayList();

	public Employee() {
		super();
	}

	// Managers have employees, but employees can exist without one
	public Employee(int employeeId, String empUsername, String empPassword, String empfName, String emplName) {
		super();
		this.employeeId = employeeId;
		this.empUsername = empUsername;
		this.empPassword = empPassword;
		this.empfName = empfName;
		this.emplName = emplName;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmpUsername() {
		return empUsername;
	}

	public void setEmpUsername(String empUsername) {
		this.empUsername = empUsername;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public String getEmpfName() {
		return empfName;
	}

	public void setEmpfName(String empfName) {
		this.empfName = empfName;
	}

	public String getEmplName() {
		return emplName;
	}

	public void setEmplName(String emplName) {
		this.emplName = emplName;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public List<Reimbursement> getReimbursements() {
		return reimbursements;
	}

	public void setReimbursements(List<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", empUsername=" + empUsername + ", empPassword=" + empPassword
				+ ", empfName=" + empfName + ", emplName=" + emplName + ", manager=" + manager + "]";
	}
	
}
