package dev.kurt.entities;

import javax.persistence.*;

@Entity
@Table(name="employee")

public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "e_id")
	private int employeeId;
	
	@Column(name = "username")
	private String username;                                                    
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "first_name")
	private String fName;
	
	@Column(name = "last_name")
	private String lName;
	
	@Column(name = "permission_id")
	private int permissionId; // managers are just employees with different permissions
	
	public Employee() {
		super();
	}

	public Employee(int employeeId, String username, String password, String fName, String lName, int permissionId) {
		super();
		this.employeeId = employeeId;
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.permissionId = permissionId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", username=" + username + ", password=" + password + ", fName="
				+ fName + ", lName=" + lName + ", permissionId=" + permissionId + "]";
	}
	
	
	
	
	
	
}
