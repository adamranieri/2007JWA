package dev.kurt.entities;

public class User {
	
	private int id;
	private String username;
	private String password;
	private String fName;
	private String lName;
	
	
	public User() {
		super();
	}

	public User(int id, String username, String password, String fName, String lName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
}
