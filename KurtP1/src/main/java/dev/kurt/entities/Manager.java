package dev.kurt.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name ="manager")
public class Manager extends User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "man_id")
	private int managerId;
	
	@Column(name = "man_username")
	private String manUsername;                                                    
	
	@Column(name = "man_password")
	private String manPassword;
	
	@Column(name = "man_first_name")
	private String manfName;
	
	@Column(name = "man_last_name")
	private String manlName;
	
	
	@OneToMany (mappedBy= "manager", fetch= FetchType.EAGER) 
	@Cascade(CascadeType.SAVE_UPDATE)
	@LazyCollection(LazyCollectionOption.FALSE)
	transient List<Employee> employees = new ArrayList<Employee>();

	public Manager() {
		super();
	}

	public Manager(int managerId, String manUsername, String manPassword, String manfName, String manlName) {
		super();
		this.managerId = managerId;
		this.manUsername = manUsername;
		this.manPassword = manPassword;
		this.manfName = manfName;
		this.manlName = manlName;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getManUsername() {
		return manUsername;
	}

	public void setManUsername(String manUsername) {
		this.manUsername = manUsername;
	}

	public String getManPassword() {
		return manPassword;
	}

	public void setManPassword(String manPassword) {
		this.manPassword = manPassword;
	}

	public String getManfName() {
		return manfName;
	}

	public void setManfName(String manfName) {
		this.manfName = manfName;
	}

	public String getManlName() {
		return manlName;
	}

	public void setManlName(String manlName) {
		this.manlName = manlName;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Manager [managerId=" + managerId + ", manUsername=" + manUsername + ", manPassword=" + manPassword
				+ ", manfName=" + manfName + ", manlName=" + manlName + "]";
	}
	
	
	
	
}
